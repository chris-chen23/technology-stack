package com.rzcoding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableEurekaClient
@MapperScan(basePackages = "com.rzcoding.dao")
@EnableFeignClients(basePackages = {"com.rzcoding.client"})
public class BankAApplication {
	public static void main(String[] args) {
		SpringApplication.run(BankAApplication.class, args);
	}
}
