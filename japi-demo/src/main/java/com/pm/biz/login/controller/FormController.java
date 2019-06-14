package com.pm.biz.login.controller;

import com.alibaba.fastjson.JSONObject;
import com.pm.biz.login.model.DataVo;
import com.pm.biz.login.model.base.ResultEntity;
import com.pm.japi.annotations.Api;
import com.pm.japi.annotations.ApiMethod;
import com.pm.japi.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@Api
public class FormController {

    //name=abc&tags=1&tags=3
    @PostMapping(value = "/saveForm")
    @ApiMethod
    public Mono<ResultEntity> saveForm(String name, String[] tags) {
        return Mono.just(ResultEntity.success());
    }



}
