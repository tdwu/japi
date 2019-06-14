package com.pm.japi.spring.provider;

import com.pm.japi.spring.handler.WebMvcRequestHandler;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;

import java.util.ArrayList;
import java.util.List;

public class WebMvcRequestHandlerProvider {

    private List<RequestMappingInfoHandlerMapping> handlerMappings;
    private List<WebMvcRequestHandler> requestHandlers;

    public void setHandlerMappings(List<RequestMappingInfoHandlerMapping> handlerMappings) {
        this.handlerMappings = handlerMappings;
        requestHandlers = new ArrayList<WebMvcRequestHandler>();
        handlerMappings.forEach(p -> fillHandler(p));
    }


    public void fillHandler(RequestMappingInfoHandlerMapping mappingInfoHandlerMapping) {
        mappingInfoHandlerMapping.getHandlerMethods().entrySet()
                .forEach(p -> requestHandlers.add(new WebMvcRequestHandler(p.getKey(), p.getValue())));
    }

    public List<RequestMappingInfoHandlerMapping> getHandlerMappings() {
        return handlerMappings;
    }

    public List<WebMvcRequestHandler> getRequestHandlers() {
        return requestHandlers;
    }

}
