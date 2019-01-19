package com.edu.tuiguang.controller;

import com.edu.tuiguang.entity.ResultBean;
import com.edu.tuiguang.service.WxCoreService;
import com.edu.tuiguang.utils.ResultUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/weixin")
public class WeixinController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	protected WxMpConfigStorage configStorage;
	@Autowired
	private WxMpService wxMpService;
	@Autowired
	protected WxCoreService coreService;

	/**
	 * 微信公众号webservice主服务接口，提供与微信服务器的信息交互
	 */
	@RequestMapping("/wechatCore")
	public String wechatCore(
			HttpServletRequest request,
			@RequestParam(value = "signature") String signature,
			@RequestParam(value = "timestamp") String timestamp,
			@RequestParam(value = "nonce") String nonce,
			@RequestParam(value = "echostr") String echostr,
			@RequestParam(value = "encrypt_type") String encrypt_type
	) throws Exception {
		if (!this.wxMpService.checkSignature(timestamp, nonce, signature)) {
			// 消息签名不正确，说明不是公众平台发过来的消息
			return "非法请求！";
		}
		if (StringUtils.isNotBlank(echostr)) {
			// 说明是一个仅仅用来验证的请求，回显echostr
			String echoStrOut = String.copyValueOf(echostr.toCharArray());
			return echoStrOut;
		}

		String encryptType = StringUtils.isBlank(encrypt_type) ? "raw" : encrypt_type;

		if ("raw".equals(encryptType)) {
			// 明文传输的消息
			WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(request.getInputStream());
			WxMpXmlOutMessage outMessage = this.coreService.route(inMessage);
			if (outMessage == null) {
				return "";
			} else {
				return outMessage.toXml();
			}
		}

		if ("aes".equals(encryptType)) {
			// 是aes加密的消息
			String msgSignature = request.getParameter("msg_signature");
			WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(
					request.getInputStream(), this.configStorage, timestamp, nonce,
					msgSignature);
			this.logger.debug("\n消息解密后内容为：\n{} ", inMessage.toString());
			WxMpXmlOutMessage outMessage = this.coreService.route(inMessage);
			if (outMessage == null) {
				return "";
			} else {
				return outMessage.toEncryptedXml(this.configStorage);
			}
		}

		return "不可识别的加密类型";
	}

	/**
	 * 通过openid获得基本用户信息
	 * 详情请见: http://mp.weixin.qq.com/wiki/14/bb5031008f1494a59c6f71fa0f319c66.html
	 *
	 * @param openid openid
	 * @param lang   zh_CN, zh_TW, en
	 */
	@RequestMapping(value = "/getUserInfo")
	public ResultBean getUserInfo(
			@RequestParam(value = "openid") String openid,
			@RequestParam(value = "lang") String lang
	) {
		WxMpUser wxMpUser = null;
		try {
			wxMpUser = this.wxMpService.getUserService().userInfo(openid, lang);
			return ResultUtils.success(wxMpUser);
		} catch (WxErrorException e) {
			this.logger.error(e.getError().toString());
			return ResultUtils.error(2000, e.getError().toString());
		}
	}

	/**
	 * 通过code获得基本用户信息
	 * 详情请见: http://mp.weixin.qq.com/wiki/14/bb5031008f1494a59c6f71fa0f319c66.html
	 *
	 * @param code code
	 * @param lang zh_CN, zh_TW, en
	 */
	@RequestMapping(value = "/getOAuth2UserInfo")
	public ResultBean getOAuth2UserInfo(
			@RequestParam(value = "code") String code,
			@RequestParam(value = "lang") String lang
	) {
		WxMpOAuth2AccessToken accessToken;
		WxMpUser wxMpUser;
		try {
			accessToken = this.wxMpService.oauth2getAccessToken(code);
			wxMpUser = this.wxMpService.getUserService()
					.userInfo(accessToken.getOpenId(), lang);
			return ResultUtils.success(wxMpUser);
		} catch (WxErrorException e) {
			this.logger.error(e.getError().toString());
			return ResultUtils.error(2000, e.getError().toString());
		}
	}

	/**
	 * 用code换取oauth2的openid
	 * 详情请见: http://mp.weixin.qq.com/wiki/1/8a5ce6257f1d3b2afb20f83e72b72ce9.html
	 *
	 * @param code code
	 */
	@RequestMapping(value = "/getOpenid")
	public ResultBean getOpenid(@RequestParam(value = "code") String code) {
		WxMpOAuth2AccessToken accessToken;
		try {
			accessToken = this.wxMpService.oauth2getAccessToken(code);
			return ResultUtils.success(accessToken.getOpenId());
		} catch (WxErrorException e) {
			this.logger.error(e.getError().toString());
			return ResultUtils.error(2000, e.getError().toString());
		}
	}
}
