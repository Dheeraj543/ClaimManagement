package com.cts.mfpe;

import java.sql.SQLException;
import java.util.Collections;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@EnableFeignClients
public class PolicyApplication {
//	@Bean(initMethod = "start", destroyMethod = "stop")
//	public Server inMemoryH2DatabaseaServer() throws SQLException {
//	    return Server.createTcpServer(
//	      "-tcp", "-tcpAllowOthers", "-tcpPort", "9090");
//	}
	@Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2Server() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "8003");
    }
	public static void main(String[] args) {
		SpringApplication.run(PolicyApplication.class, args);
	}
	@Bean
	public Docket swaggerConfiguration() {
		String groupName="Swagger";
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.any())
				.apis(RequestHandlerSelectors.basePackage("com.cts.authorization.controller"))
				.build()
				.groupName(groupName)
				.apiInfo(apiInfo());
	}
	private ApiInfo apiInfo() {
	      return new ApiInfo(
	              "Authorization API",
	              "API for authorising Users",
	              "V1",
	              "OpenSource",
	              null,
	              "API License",
	              "NA",
	              Collections.emptyList()
	              );

}
}