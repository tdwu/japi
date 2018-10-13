package com.pm.biz.login.controller;

import com.alibaba.fastjson.JSONObject;
import com.pm.biz.login.model.UserInfo;
import com.pm.biz.login.model.UserToken;
import com.pm.biz.login.model.base.ResultEntity;
import com.pm.biz.login.model.enums.UserSex;
import com.pm.japi.annotations.Api;
import com.pm.japi.annotations.ApiMethod;
import com.pm.japi.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/info")
@Api(value = "个人信息", tags = "演示", module = "测试-用户模块")
public class UserController {

    @ApiMethod(value = "获取用户信息1", note = "演示内容：1 参数数组 2 级联数组 3 属性对象引用描述（如leader字段）4 返回数组", params = {
            @ApiParam(value = "money", type = double.class)
            , @ApiParam("user.name"), @ApiParam("user.score[].name"), @ApiParam("user.score[].value")
            , @ApiParam(value = "user.imgs[]", note = "图片地址")
            , @ApiParam(value = "user.leader", type = UserInfo.class)})
    @PostMapping(value = "/getInfo1")
    public ResultEntity<Long[]> getInfo1(@RequestBody JSONObject jsonObject) throws Exception {
        return ResultEntity.success();
    }

    @ApiMethod(value = "获取用户信息2", note = "演示内容：1 参数 引用某个java Bean中的个字段 2 级联参数 引用javaBean中的字段 3 级联参数，直接引用javaBean"
            , params = {@ApiParam(value = "$sex", type = UserInfo.class, note = "引用bean里面的一个字段")
            , @ApiParam(value = "sex1 ", type = UserSex.class)
            , @ApiParam(value = "user.$sex", type = UserInfo.class, note = "引用bean里面的一个字段")
            , @ApiParam(value = "user.token", type = UserToken.class, note = "引用bean里面的一个字段")})
    @PostMapping(value = "/getInfo2")
    public ResultEntity<Long[]> getInfo2(@RequestBody JSONObject jsonObject) throws Exception {
        return ResultEntity.success();
    }

}
