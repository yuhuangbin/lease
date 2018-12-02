package com.lease;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lease.mapper")
public class LeaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeaseApplication.class, args);
	}
}
