package com.edu.tuiguang.config;

import com.edu.tuiguang.controller.interceptors.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 注册自定义拦截器，添加拦截路径和排除拦截路径
		registry.addInterceptor(new LoginInterceptor())
				.addPathPatterns("/**")
				.excludePathPatterns(
						"/app/user/login",
						"/activity/findDetail"
				);
		registry.addInterceptor(new LoginInterceptor())
				.addPathPatterns("/driver**")
				.excludePathPatterns(
						"/user/login",
						"/activity/findDetail"
				);
	}


}
