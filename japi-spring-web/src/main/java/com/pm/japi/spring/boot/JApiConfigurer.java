package com.pm.japi.spring.boot;

import com.pm.japi.sacnner.ApiDocumentationScanner;
import com.pm.japi.spring.provider.WebRequestHandlerProvider;
import com.pm.japi.spring.web.JApiController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;

import java.util.List;

@Configuration
public class JApiConfigurer {

    @Bean("requestHandlerProvider")
    public WebRequestHandlerProvider requestHandlerProvider(List<RequestMappingInfoHandlerMapping> list){
        WebRequestHandlerProvider bean=new WebRequestHandlerProvider();
        bean.setHandlerMappings(list);
        return bean;
    }

    @Bean
    public ApiDocumentationScanner documentationScanner(){
        return new ApiDocumentationScanner();
    }


    @Bean
    public JApiController jApiController(){
        return new JApiController();
    }
}
