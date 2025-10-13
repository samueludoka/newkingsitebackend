package org.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // ✅ Allowed domains: production + local dev
                .allowedOrigins(
                        "https://nextcents.com",
                        "https://www.nextcents.com",
                        "http://localhost:3000"
                )
                // ✅ Allowed HTTP methods
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                // ✅ Allow all headers (Authorization, Content-Type, etc.)
                .allowedHeaders("*")
                // ✅ Allow cookies/auth headers
                .allowCredentials(true)
                // ✅ Optional: Cache preflight response for 1 hour
                .maxAge(3600);
    }
}
