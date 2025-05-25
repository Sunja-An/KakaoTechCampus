package com.management.todoapp.shared.config;

import com.management.todoapp.shared.filter.PasswordArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration("WebMvcConfig")
public class WebMvcConfig implements WebMvcConfigurer {
    private final PasswordArgumentResolver passwordArgumentResolver;

    public WebMvcConfig(PasswordArgumentResolver passwordArgumentResolver) {
        this.passwordArgumentResolver = passwordArgumentResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(passwordArgumentResolver);
    }
}
