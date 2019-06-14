package com.pm.japi.spring.provider;


import com.pm.japi.spring.handler.WebFluxRequestHandler;
import org.springframework.web.reactive.result.method.RequestMappingInfoHandlerMapping;

import java.util.ArrayList;
import java.util.List;

public class WebFluxRequestHandlerProvider {

    private List<RequestMappingInfoHandlerMapping> handlerMappings;
    private List<WebFluxRequestHandler> requestHandlers;

    public void setHandlerMappings(List<RequestMappingInfoHandlerMapping> handlerMappings) {
        this.handlerMappings = handlerMappings;
        requestHandlers=new ArrayList<WebFluxRequestHandler>();
        handlerMappings.forEach(p->fillHandler(p));
    }


    public void fillHandler(RequestMappingInfoHandlerMapping mappingInfoHandlerMapping){
        mappingInfoHandlerMapping.getHandlerMethods().entrySet()
                .forEach(p->requestHandlers.add(new WebFluxRequestHandler(p.getKey(),p.getValue())));
    }

    public List<RequestMappingInfoHandlerMapping> getHandlerMappings() {
        return handlerMappings;
    }
    public List<WebFluxRequestHandler> getRequestHandlers() {
        return requestHandlers;
    }

}
