package org.example.config;

public class WhiteList {
    public static final String[] AUTH_WHITELIST = {
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/webjars/**",
            "/error",

            "/api/v1/customer/signup",
            "/api/v1/customer/login",
            "/api/v1/customer/verify",
            "/api/v1/customer/reset-password",
            "/api/v1/customer/forgot-password"
    };
}
