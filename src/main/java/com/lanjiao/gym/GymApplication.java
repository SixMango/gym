package com.lanjiao.gym;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;



@SpringBootApplication
@EnableScheduling
@Configuration
public class GymApplication{

	public static void main(String[] args) {

		SpringApplication.run(GymApplication.class, args);
	}

//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//
//		registry.addMapping("/**")
//				.allowCredentials(true)
//				.allowedHeaders("*")
//				.allowedOrigins("*")
//				.allowedMethods("*");
//	}
//	@Override
//	public  void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(new TokenHandleInterceptor())
//				.addPathPatterns("/order/**")
//				.addPathPatterns("/wxuser/userInfo/**");
//		super.addInterceptors(registry);
//	}

}
