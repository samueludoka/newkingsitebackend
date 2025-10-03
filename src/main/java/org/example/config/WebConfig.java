package org.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Allow all endpoints (e.g., /api/v1/customer/*)
                .allowedOrigins("https://nextcents.com", "http://localhost:3000")  // Allow your frontend domains
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Allowed HTTP methods
                .allowedHeaders("*")  // Allow all headers (e.g., Content-Type, Authorization)
                .allowCredentials(true);  // Allow cookies/auth headers if needed
    }
}
