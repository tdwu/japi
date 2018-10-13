package com.pm.japi.spring.provider;

import com.pm.japi.spring.handler.WebRequestHandler;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;

import java.util.ArrayList;
import java.util.List;

public class WebRequestHandlerProvider {
    private List<RequestMappingInfoHandlerMapping> handlerMappings;
    private List<WebRequestHandler> requestHandlers;

    public void setHandlerMappings(List<RequestMappingInfoHandlerMapping> handlerMappings) {
        this.handlerMappings = handlerMappings;
        requestHandlers=new ArrayList<WebRequestHandler>();
        handlerMappings.forEach(p->fillHandler(p));
    }

    public void fillHandler(RequestMappingInfoHandlerMapping mappingInfoHandlerMapping){
        mappingInfoHandlerMapping.getHandlerMethods().entrySet()
                .forEach(p->requestHandlers.add(new WebRequestHandler(p.getKey(),p.getValue())));
    }

    public List<RequestMappingInfoHandlerMapping> getHandlerMappings() {
        return handlerMappings;
    }
    public List<WebRequestHandler> getRequestHandlers() {
        return requestHandlers;
    }

}
