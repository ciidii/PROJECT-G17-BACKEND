package com.projet17backend.backend.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;

@Configuration
@EnableWebMvc
public class WebConfig {
    @Bean
    public FilterRegistrationBean corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.addAllowedOrigin("http://localhost:4200");
        configuration.setAllowedHeaders(Arrays.asList(
                HttpHeaders.AUTHORIZATION,
                HttpHeaders.CONTENT_TYPE,
                HttpHeaders.ACCEPT
        ));
        configuration.setAllowedMethods(
                Arrays.asList(
                        HttpMethod.POST.name(),
                        HttpMethod.GET.name(),
                        HttpMethod.PUT.name(),
                        HttpMethod.DELETE.name()
                )

        );
        configuration.setMaxAge(3600L);
        source.registerCorsConfiguration("/**",configuration);
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new CorsFilter(source));
        filterRegistrationBean.setOrder(-104);
        return filterRegistrationBean;
    }
}
