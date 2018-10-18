package com.pm.biz.config;

import com.pm.japi.sacnner.ApiDocumentationScanner;
import com.pm.japi.spring.boot.JApiConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfigurer extends JApiConfigurer {

    @Bean
    public ApiDocumentationScanner documentationScanner() {
        // this.apiConfigInfo().setReadMe("readme.md");
        // this.apiConfigInfo().setMarkDown(2);
        return new ApiDocumentationScanner(this.apiConfigInfo());
    }
}
