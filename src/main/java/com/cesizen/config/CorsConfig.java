package com.cesizen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

	@Bean
	WebMvcConfigurer corsConfigurer() {
		System.out.println("CORS configuration loaded!");
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**") // Autorise tous les endpoints
						.allowedOrigins("http://localhost:4200") // Origines autorisées (Angular)
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Méthodes HTTP autorisées
						.allowedHeaders("*") // Autorise tous les headers
						.allowCredentials(true); // Autorise les cookies ou l'authentification
			}
		};
	}
}