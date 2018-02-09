package com.nivsoft.dvrmoney.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@SpringBootApplication
@Configuration
public class DvrmoneyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DvrmoneyApiApplication.class, args);
	}

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:/messages");
		messageSource.setCacheSeconds(10);
		messageSource.setDefaultEncoding("iso-8859-1");

		return messageSource;
	}
}
