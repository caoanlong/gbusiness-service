package com.edu.tuiguang.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 微信公众号主配置
 */
@Configuration
public class WeixinConfig {
	@Value("#{wxProperties.appId}")
	private String appId;

	@Value("#{wxProperties.secret}")
	private String secret;

	@Value("#{wxProperties.token}")
	private String token;

	@Value("#{wxProperties.aesKey}")
	private String aesKey;

	@Bean
	public WxMpConfigStorage wxMpConfigStorage() {
		WxMpInMemoryConfigStorage configStorage = new WxMpInMemoryConfigStorage();
		configStorage.setAppId(appId);
		configStorage.setSecret(secret);
		configStorage.setToken(token);
		configStorage.setAesKey(aesKey);
		return configStorage;
	}

	@Bean
	public WxMpService wxMpService() {
		WxMpServiceImpl wxMpService = new WxMpServiceImpl();
		wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
		return wxMpService;
	}
}
