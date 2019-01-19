package com.edu.tuiguang.handler;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 转发客户消息给客服Handler
 */
@Component
public class MsgHandler extends AbstractHandler {
	@Override
	public WxMpXmlOutMessage handle(
			WxMpXmlMessage wxMessage,
			Map<String, Object> map,
			WxMpService wxMpService,
			WxSessionManager wxSessionManager
	) throws WxErrorException {
		return WxMpXmlOutMessage
				.TRANSFER_CUSTOMER_SERVICE().fromUser(wxMessage.getToUser())
				.toUser(wxMessage.getFromUser()).build();
	}
}
