package com.redick.starter.configure;

import com.redick.AroundLogHandler;
import com.redick.starter.filter.LogHelperWebFluxFilter;
import com.redick.starter.interceptor.SpringWebMvcInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Redick01
 *  2022/3/25 14:42
 */
@Configuration
public class LogHelperAutoConfiguration {

    @Bean
    public AroundLogHandler aroundLogHandler() {
        return new AroundLogHandler();
    }

    @Bean
    @ConditionalOnClass(DispatcherServlet.class)
    public WebMvcInterceptor webMvcInterceptor() {
        return new WebMvcInterceptor();
    }

    @Bean
    @ConditionalOnClass(WebFluxProperties.class)
    public LogHelperWebFluxFilter logHelperWebFluxFilter() {
        return new LogHelperWebFluxFilter();
    }
}

class WebMvcInterceptor implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SpringWebMvcInterceptor());
    }
}