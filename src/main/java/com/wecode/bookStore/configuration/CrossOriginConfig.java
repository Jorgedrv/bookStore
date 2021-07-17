package com.wecode.bookStore.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CrossOriginConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
            .addMapping("/**")
            .allowedMethods(HttpMethod.POST.name(), HttpMethod.GET.name(), HttpMethod.PUT.name(), HttpMethod.DELETE.name())
            .allowedOrigins("*")
            .allowCredentials(false)
            .allowedHeaders("Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization")
            .exposedHeaders("Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization")
            .maxAge(3600);
    }
}
