package com.dh.bootio.controller;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;

@RestController
@RequestMapping("/tool")
public class ToolController implements ApplicationContextAware {


    private ApplicationContext applicationContext;

    @GetMapping(value = "/v1/getAllUrl")
    public List<Map<String, String>> getAllUrl() {
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class); // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
        List<String> urlList = new ArrayList<>(); //
        for (RequestMappingInfo info : map.keySet()) { //
            // 获取url的Set集合，一个方法可能对应多个url//
            Set<String> patterns = info.getPatternsCondition().getPatterns(); // //
            for (String url : patterns) {
                urlList.add(url);
            }
        }
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
            Map<String, String> map1 = new HashMap<String, String>();
            RequestMappingInfo info = m.getKey();
            HandlerMethod method = m.getValue();
            PatternsRequestCondition p = info.getPatternsCondition();
            for (String url : p.getPatterns()) {
                map1.put("url", url);
            }
            map1.put("className", method.getMethod().getDeclaringClass().getName()); // 类名
            map1.put("method", method.getMethod().getName()); // 方法名
            RequestMethodsRequestCondition methodsCondition = info.getMethodsCondition();
            for (RequestMethod requestMethod : methodsCondition.getMethods()) {
                map1.put("type", requestMethod.toString());
            }

            list.add(map1);
        }

        return list;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
