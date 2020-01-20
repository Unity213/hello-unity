package com.dh.bootio.controller;

import com.dh.bootio.bean.Book;
import com.google.common.collect.HashMultimap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@Api(tags = "testController")
public class TestController {


    @PostMapping("/book3")
    @ApiOperation(value = "chaungjiang",notes = "创建图书")
    public String test(@RequestBody Book book){
        System.out.println(book);
        return "hello test1";
    }

    public String hello(){

        return "";
    }

    public void rtPostObject(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://47.xxx.xxx.96/register/checkEmail";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("email", "844072586@qq.com");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity( url, request , String.class );
        System.out.println(response.getBody());
    }

    public static void main(String[] args) {
        MultiValueMap<String,String> map = new LinkedMultiValueMap<>();

        HttpEntity httpEntity = new HttpEntity(map);

    }


}
