package com.pm.japi.spring.handler;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.NameValueExpression;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import java.lang.annotation.Annotation;
import java.util.Optional;
import java.util.Set;

public class WebRequestHandler {

    private final RequestMappingInfo requestMapping;
    private final HandlerMethod handlerMethod;

    public WebRequestHandler(RequestMappingInfo requestMapping, HandlerMethod handlerMethod) {
        this.requestMapping = requestMapping;
        this.handlerMethod = handlerMethod;
    }

    public HandlerMethod getHandlerMethod() {
        return handlerMethod;
    }

    public Class<?> declaringClass() {
        return handlerMethod.getBeanType();
    }

    public boolean isAnnotatedWith(Class<? extends Annotation> annotation) {
        return null != AnnotationUtils.findAnnotation(handlerMethod.getMethod(), annotation);
    }

    public PatternsRequestCondition getPatternsCondition() {
        return requestMapping.getPatternsCondition();
    }


    public String getName() {
        return handlerMethod.getMethod().getName();
    }

    public Set<RequestMethod> supportedMethods() {
        return requestMapping.getMethodsCondition().getMethods();
    }

    public Set<? extends MediaType> produces() {
        return requestMapping.getProducesCondition().getProducibleMediaTypes();
    }

    public Set<? extends MediaType> consumes() {
        return requestMapping.getConsumesCondition().getConsumableMediaTypes();
    }

    public Set<NameValueExpression<String>> headers() {
        return requestMapping.getHeadersCondition().getExpressions();
    }

    public Set<NameValueExpression<String>> params() {
        return requestMapping.getParamsCondition().getExpressions();
    }

    public <T extends Annotation> Optional<T> findAnnotation(Class<T> annotation) {
        return Optional.ofNullable(AnnotationUtils.findAnnotation(handlerMethod.getMethod(), annotation));
    }


    public <T extends Annotation> Optional<T> findControllerAnnotation(Class<T> annotation) {
        return Optional.ofNullable(AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), annotation));
    }

    public RequestMappingInfo getRequestMapping() {
        return requestMapping;
    }

}
