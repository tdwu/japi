package com.pm.biz.login.controller;

import com.alibaba.fastjson.JSONObject;
import com.pm.biz.login.model.UserInfo;
import com.pm.biz.login.model.UserToken;
import com.pm.biz.login.model.base.IPageBean;
import com.pm.biz.login.model.base.ResultEntity;
import com.pm.biz.login.model.enums.UserStatus;
import com.pm.japi.annotations.Api;
import com.pm.japi.annotations.ApiMethod;
import com.pm.japi.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/user/info")
@Api(value = "登录", tags = "演示", module = "1 测试-用户模块")
public class LoginController {


    @ApiMethod(value = "账号密码登录", note = "演示内容： 单个参数，不需要创建VO/DTO 类", params = {@ApiParam(value = "account", note = "账号")
            , @ApiParam("password")})
    @PostMapping(value = "/loginByPassword")
    public ResultEntity<UserToken> loginByPassword(@RequestBody JSONObject jsonObject) throws Exception {
        return ResultEntity.success();
    }

    @ApiMethod(value = "历史登录TOKEN查看-账号", note = "演示内容：1 参数对象级联关系 2 参数类型 3 分页类型递归处理，即多级泛型 4 返回为数组",
            params = {@ApiParam(value = "user.name"), @ApiParam("user.account")
                    , @ApiParam(value = "startTime", type = Date.class)})
    @PostMapping(value = "/selectPageBean")
    public ResultEntity<IPageBean<UserToken>> selectPageBean(@RequestBody JSONObject jsonObject) throws Exception {
        return ResultEntity.success();
    }


    @ApiMethod(value = "历史登录TOKEN查看--全查询条件", note = "演示内容：1 可以手动指定DTO/VO 类（paramType），2 进行参数的补充 3 参数为泛型时的支持 4 返回类型的补充"
            , paramType = UserInfo.class
            ,params = {@ApiParam(value = "team.id", note = "所属团队id，这是补充的一个参数"), @ApiParam(value = "team.status", type = UserStatus.class, note = "团队状态")}
            ,result = {@ApiParam(value = "data.sysCode", note = "当XXX情况下，存在（补充说明）")})
    @RequestMapping(value = {"/selectPageBeanAllPost","/selectPageBeanAllGet"})
    public ResultEntity<IPageBean<UserToken>> selectPageBeanAll(@RequestBody JSONObject jsonObject) throws Exception {
        return ResultEntity.success();
    }


}
