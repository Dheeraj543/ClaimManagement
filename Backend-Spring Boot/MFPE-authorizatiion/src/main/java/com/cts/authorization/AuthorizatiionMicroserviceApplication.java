package com.cts.authorization;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class AuthorizatiionMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorizatiionMicroserviceApplication.class, args);
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
//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/**").allowedHeaders("*").allowedOrigins("*").allowedMethods("*")
//						.allowCredentials(true);
//			}
//		};
//	}
//	@Bean
//	public CorsFilter corsFilter() {
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		CorsConfiguration config = new CorsConfiguration();
//		config.setAllowCredentials(true);
//		config.addAllowedOrigin("*");
//		config.addAllowedHeader("*");
//		config.addAllowedMethod("OPTIONS");
//		config.addAllowedMethod("GET");
//		config.addAllowedMethod("POST");
//		config.addAllowedMethod("PUT");
//		config.addAllowedMethod("DELETE");
//		source.registerCorsConfiguration("/**", config);
//		return new CorsFilter(source);
//	}
}
