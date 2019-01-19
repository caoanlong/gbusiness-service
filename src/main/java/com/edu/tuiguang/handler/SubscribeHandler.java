package com.edu.tuiguang.handler;

import com.edu.tuiguang.service.WxCoreService;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SubscribeHandler extends AbstractHandler {

	@Autowired
	protected WxMpConfigStorage configStorage;
	@Autowired
	protected WxMpService wxMpService;
	@Autowired
	protected WxCoreService coreService;

	@Override
	public WxMpXmlOutMessage handle(
			WxMpXmlMessage wxMessage,
			Map<String, Object> map,
			WxMpService wxMpService,
			WxSessionManager wxSessionManager
	) throws WxErrorException {
		WxMpUser wxMpUser = coreService.getUserInfo(wxMessage.getFromUser(), "zh_CN");
        /*List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("openId", wxMpUser.getOpenId()));
        params.add(new BasicNameValuePair("nickname", wxMpUser.getNickname()));
        params.add(new BasicNameValuePair("headImgUrl", wxMpUser.getHeadImgUrl()));*/

		//TODO(user) 在这里可以进行用户关注时对业务系统的相关操作（比如新增用户）

		WxMpXmlOutTextMessage m
				= WxMpXmlOutMessage.TEXT()
				.content("尊敬的" + wxMpUser.getNickname() + "，您好！")
				.fromUser(wxMessage.getToUser())
				.toUser(wxMessage.getFromUser())
				.build();
		logger.info("subscribeMessageHandler" + m.getContent());
		return m;
	}
}
