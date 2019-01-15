package com.edu.tuiguang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.edu.tuiguang.repository"})
public class EduTuiguangServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EduTuiguangServiceApplication.class, args);
	}

}

