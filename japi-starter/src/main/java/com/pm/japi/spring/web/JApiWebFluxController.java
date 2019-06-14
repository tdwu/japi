package com.pm.japi.spring.web;

import com.pm.japi.sacnner.ApiDocumentationScanner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;


@Controller
@RequestMapping("/japi")
public class JApiWebFluxController {
    @Resource
    private ApiDocumentationScanner documentationScanner;

    @RequestMapping("docs-json")
    @ResponseBody
    public Mono<Object> getDocumentation() {
        return Mono.just(documentationScanner.scanDocument());
    }
}
