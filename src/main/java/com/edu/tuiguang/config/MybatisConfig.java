package com.edu.tuiguang.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = {"com.edu.tuiguang.repostory"})
public class MybatisConfig {

}
