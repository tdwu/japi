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


/**
 * @ConditionalOnBean:当容器中有指定的Bean的条件下
 * @ConditionalOnClass：当类路径下有指定的类的条件下
 * @ConditionalOnExpression:基于SpEL表达式作为判断条件
 * @ConditionalOnJava:基于JVM版本作为判断条件
 * @ConditionalOnJndi:在JNDI存在的条件下查找指定的位置
 * @ConditionalOnMissingBean:当容器中没有指定Bean的情况下
 * @ConditionalOnMissingClass:当类路径下没有指定的类的条件下
 * @ConditionalOnNotWebApplication:当前项目不是Web项目的条件下
 * @ConditionalOnProperty:指定的属性是否有指定的值
 * @ConditionalOnResource:类路径下是否有指定的资源
 * @ConditionalOnSingleCandidate:当指定的Bean在容器中只有一个，或者在有多个Bean的情况下，用来指定首选的Bean @ConditionalOnWebApplication:当前项目是Web项目的条件下
 */

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