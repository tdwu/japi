package com.pm.japi.sacnner;

import com.pm.japi.annotations.Api;
import com.pm.japi.annotations.ApiMethod;
import com.pm.japi.annotations.ApiNote;
import com.pm.japi.annotations.ApiParam;
import com.pm.japi.model.*;
import com.pm.japi.spring.handler.WebRequestHandler;
import com.pm.japi.spring.provider.WebRequestHandlerProvider;
import com.pm.japi.utils.PathUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.*;

public class ApiDocumentationScanner {

    @Resource
    private WebRequestHandlerProvider requestHandlerProvider;

    public ApiDocument scanDocument() {
        ApiDocument apiDocument = new ApiDocument();
        List<WebRequestHandler> list = requestHandlerProvider.getRequestHandlers();

        //处理method
        Map<String, ApiInfo> apiMap = new HashMap<String, ApiInfo>();
        ModelProvider modelProvider = new ModelProvider();
        list.stream().filter(p -> p.isAnnotatedWith(ApiMethod.class)).forEach(p -> {
            /*ApiMethod apiMethod = AnnotatedElementUtils.findMergedAnnotation(p.getHandlerMethod().getMethod(), ApiMethod.class);
            RequestMapping mapping = AnnotatedElementUtils.findMergedAnnotation(p.getHandlerMethod().getMethod(), RequestMapping.class);
            PostMapping post = AnnotatedElementUtils.findMergedAnnotation(p.getHandlerMethod().getMethod(), PostMapping.class);*/

            //方法上的注解
            ApiMethod apiMethod = p.getHandlerMethod().getMethod().getAnnotation(ApiMethod.class);
            //由于spring注解中，path和value是别名关系，防止开发人员2种方式都在写，所以需要utils帮忙2边都有值
            RequestMapping mapping = AnnotatedElementUtils.findMergedAnnotation(p.getHandlerMethod().getMethod(), RequestMapping.class);
            PostMapping post = AnnotatedElementUtils.findMergedAnnotation(p.getHandlerMethod().getMethod(), PostMapping.class);

            //获取方法对应的url
            Method method = null;
            if (mapping != null) {
                method = new Method();
                method.setPath(mapping.path()[0]);//暂时用第一个
            } else if (post != null) {
                method = new Method();
                method.setPath(post.path()[0]);//暂时用第一个
            }

            if (method != null) {
                method.setName(apiMethod.value());
                method.setNote(apiMethod.note());

                //请求的参数
                List<ApiParam> apiParamList = Arrays.asList(apiMethod.params());
                List<Param> paramList = parse(modelProvider, apiParamList);
                method.setParamList(paramList);

                //返回的参数
                List<ApiParam> apiResultList = Arrays.asList(apiMethod.result());
                List<Param> resultList = parse(modelProvider, apiResultList);
                method.setResultList(resultList);

                //Controller上的api描述
                Api api = AnnotatedElementUtils.findMergedAnnotation(p.getHandlerMethod().getBeanType(), Api.class);
                String apiClassName = p.getHandlerMethod().getBeanType().getName();
                ApiInfo apiInfo = apiMap.get(apiClassName);
                if (apiInfo == null) {
                    apiInfo = new ApiInfo();
                    apiInfo.setModule(api.module());
                    apiInfo.setName(api.value());
                    apiInfo.setHidden(api.hidden());
                    apiInfo.setTags(api.tags());

                    // Controller上的path
                    RequestMapping cr = AnnotatedElementUtils.findMergedAnnotation(p.getHandlerMethod().getBeanType(), RequestMapping.class);
                    apiInfo.setBasePath(cr.path()[0]);

                    apiMap.put(apiClassName, apiInfo);
                } else {
                    apiMap.get(apiClassName);
                }

                //方法接口地址叠加
                method.setPath(apiInfo.getBasePath() + method.getPath());
                apiInfo.getMethodList().add(method);

                //返回参数类型处理
                Type returnType = p.getHandlerMethod().getMethod().getGenericReturnType();
                modelProvider.addType(returnType, null);
                method.setReturnType(returnType.getTypeName());

                //处理请求的参数类型
                if (!ApiMethod.class.equals(apiMethod.paramType())) {
                    //不是默认的，说明是设置过的，则需要处理
                    modelProvider.addType(apiMethod.paramType(), null);
                    method.setParamType(apiMethod.paramType().getTypeName());
                }

            }
        });

        // 在document中，添加接口参数的数据模型
        Map<String, Module> moduleMap = new HashMap<String, Module>();
        apiMap.entrySet().forEach(p -> {
            ApiInfo apiInfo = p.getValue();
            Module module = moduleMap.get(apiInfo.getModule());
            if (module == null) {
                module = new Module();
                module.setName(apiInfo.getModule());
                moduleMap.put(module.getName(), module);

                apiDocument.getModuleList().add(module);
            }
            module.getApiList().add(apiInfo);
        });
        apiDocument.setDefines(modelProvider.getTypeMap());
        return apiDocument;
    }

    private List<Param> parse(ModelProvider modelProvider, List<ApiParam> apiParamList) {

        apiParamList.sort((p1, p2) -> {
            return p1.value().compareTo(p2.value());
        });

        Map<String, Param> paramMap = new HashMap<String, Param>();
        List<Param> paramList = new ArrayList<Param>();

        for (ApiParam bean : apiParamList) {
            //剔除注解的默认对象值，value必须有值
            if (StringUtils.isBlank(bean.value())) {
                continue;
            }

            Param param = new Param();
            param.setTitle(bean.title());
            param.setFrom(bean.from());
            param.setNote(bean.note());
            param.setName(bean.value());

            String field = null;
            // 判断是否有引用的字段
            int filedIndex = param.getName().indexOf("$");
            if (filedIndex >= 0) {
                field = param.getName().substring(filedIndex+1);
                param.setName(param.getName().replace("$", ""));
            }

            //处理名称
            if (param.getName().indexOf(".") > 0) {
                //说明是user.id方式;
                String parentName = PathUtils.parentName(param.getName(), ".");
                param.setName(PathUtils.getName(param.getName(), "."));
                Param pt = checkParent(paramMap, paramList, parentName, param);
                pt.getProperties().add(param);
            } else {
                //普通模式
                paramList.add(param);//放入结果中
            }


            // 处理type和引用的字段
            param.setType("$" + bean.type().getTypeName());
            if (StringUtils.isNotBlank(field)) {
                try {
                    Field f = bean.type().getDeclaredField(field);
                    if (StringUtils.isBlank(param.getNote())) {
                        ApiNote apiNote = f.getAnnotation(ApiNote.class);
                        if (apiNote != null) {
                            param.setNote(apiNote.value());
                        }
                    }
                    //设置真正的数据类型,如果是其他bean，不会递归处理，由界面处理
                    param.setType("$" + f.getGenericType().getTypeName());
                    modelProvider.addType(f.getGenericType(), null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            paramMap.put(param.getName(), param);
        }

        return paramList;
    }

    public Param checkParent(Map<String, Param> paramMap, List<Param> paramList, String path, Param child) {
        //是否有节点
        if (paramMap.get(path) == null) {
            //没有
            if (path.indexOf(".") > 0) {
                //如果还有父节点，继续处理
                String parentName = PathUtils.parentName(path, ".");
                Param pt = checkParent(paramMap, paramList, parentName, child);

                String name = PathUtils.getName(path, ".");
                Param parent = new Param();
                parent.setName(name);
                parent.setFrom(child.getFrom());
                parent.setType("$"+Object.class.getTypeName());

                pt.getProperties().add(parent);

                paramMap.put(path, parent);
                return parent;

            } else {
                //如果是定点
                Param parent = new Param();
                parent.setName(path);
                parent.setFrom(child.getFrom());
                parent.setType("$"+Object.class.getTypeName());

                paramMap.put(path, parent);
                paramList.add(parent);
                return parent;
            }
        } else {
            return paramMap.get(path);
        }
    }

}
