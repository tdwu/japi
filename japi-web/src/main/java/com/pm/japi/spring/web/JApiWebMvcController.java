package com.pm.japi.spring.web;

import com.pm.japi.sacnner.ApiDocumentationScanner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


@Controller
@RequestMapping("/japi")
public class JApiWebMvcController {
    @Resource
    private ApiDocumentationScanner documentationScanner;

    @RequestMapping("docs-json")
    @ResponseBody
    public Object getDocumentation() {
        return documentationScanner.scanDocument();
    }
}
