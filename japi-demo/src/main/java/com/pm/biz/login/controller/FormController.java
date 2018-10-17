package com.pm.biz.login.controller;

import com.alibaba.fastjson.JSONObject;
import com.pm.biz.login.model.DataVo;
import com.pm.biz.login.model.UserVo;
import com.pm.biz.login.model.base.ResultEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
public class FormController {

    //name=abc&tags=1&tags=3
    @PostMapping(value = "/saveForm")
    public ResultEntity saveForm(String name, String[] tags)  {
        return ResultEntity.success();
    }

    //name=abc&tags=1&tags=3
    //注意，如果是list（非基本类型），则必须加@RequestParam。如果是数组则不用加
    @PostMapping(value = "/ajaxSer")
    public ResultEntity ajaxSer(String name, @RequestParam(value="tags", required = false) List<String> tags)  {
        return ResultEntity.success();
    }

    //name=abc&tags[]=1&tags[]=2&user[id]=123&user[name]=wtd&user[score][]=100&user[score][]=80&user[score][]=90
    // name变了，所以需要@RequestParam
    @RequestMapping(value = {"/ajaxDefault"})
    public ResultEntity ajaxDefault(String name, @RequestParam("tags[]")  String[] tags) {
        return ResultEntity.success();
    }

    //name=abc&tags[]=1&tags[]=2&user[id]=123&user[name]=wtd&user[score][]=100&user[score][]=80&user[score][]=90
    // name变了，所以需要@RequestParam
    @RequestMapping(value = {"/ajaxJson"})
    public ResultEntity ajaxJson(String name, @RequestParam("tags[]") List<String> tags) {
        return ResultEntity.success();
    }

    @RequestMapping(value = {"/ajaxListUser"})
    public ResultEntity ajaxListUser(DataVo vo) {
        return ResultEntity.success();
    }


    @RequestMapping(value = {"/ajaxJsonStr"})
    public ResultEntity ajaxJsonStr(@RequestBody DataVo vo) {
        return ResultEntity.success();
    }


    @RequestMapping(value = {"/ajaxJsonInBody"})
    public ResultEntity ajaxJsonInBody(@RequestBody JSONObject jsonObject) throws Exception {
        return ResultEntity.success();
    }


}
