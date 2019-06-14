package com.pm.japi;

import com.pm.japi.model.ApiConfigInfo;
import com.pm.japi.sacnner.ApiDocumentationScanner;
import com.pm.japi.spring.provider.WebFluxRequestHandlerProvider;
import com.pm.japi.spring.provider.WebMvcRequestHandlerProvider;
import com.pm.japi.spring.web.JApiWebFluxController;
import com.pm.japi.spring.web.JApiWebMvcController;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;



@Configuration
//@ConditionalOnClass(StarterService.class)//当classpath下发现该类的情况下进行自动配置。
@ConditionalOnProperty(prefix = "japi", value = "enabled", havingValue = "true")
public class StarterAutoConfigure {


    @Bean
    public ApiConfigInfo apiConfigInfo() {
        return new ApiConfigInfo();
    }


    @Bean
    public ApiDocumentationScanner documentationScanner() {
        return new ApiDocumentationScanner(this.apiConfigInfo());
    }


    @Bean("webMvcRequestHandlerProvider")
    @ConditionalOnBean(org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping.class)
    public WebMvcRequestHandlerProvider webMvcRequestHandlerProvider(List<org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping> list) {
        WebMvcRequestHandlerProvider bean = new WebMvcRequestHandlerProvider();
        bean.setHandlerMappings(list);
        return bean;
    }




    @Bean("webFluxRequestHandlerProvider")
    @ConditionalOnBean(org.springframework.web.reactive.result.method.RequestMappingInfoHandlerMapping.class)
    public WebFluxRequestHandlerProvider webFluxRequestHandlerProvider(List<org.springframework.web.reactive.result.method.RequestMappingInfoHandlerMapping> list) {
        WebFluxRequestHandlerProvider bean = new WebFluxRequestHandlerProvider();
        bean.setHandlerMappings(list);
        return bean;
    }

    @Bean
    public JApiWebMvcController jApiWebMvcController() {
        return new JApiWebMvcController();
    }
    /*
    @Bean
    @ConditionalOnBean(org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping.class)
    public JApiWebMvcController jApiWebMvcController() {
        return new JApiWebMvcController();
    }

    @Bean
    @ConditionalOnBean(org.springframework.web.reactive.result.method.RequestMappingInfoHandlerMapping.class)
    public JApiWebFluxController jApiWebFluxController() {
        return new JApiWebFluxController();
    }*/


}