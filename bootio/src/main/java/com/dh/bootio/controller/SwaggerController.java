package com.dh.bootio.controller;

import com.dh.bootio.bean.Book;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/swagger")
@Api(tags = "swaggerController1" ,description = "this is desc" ,value = "this is value")
public class SwaggerController {

    @PostMapping("/book2")
    @ApiOperation(value = "chaungjiang",notes = "创建图书")
    @ApiImplicitParam(name = "book1" ,value = "书",required = false,dataType = "Book1")
    public String test(@RequestBody Book book){
        System.out.println(book);
        return "hello test1";
    }

    @GetMapping("/test")
    @ApiOperation(value = "test",notes = "测试")
    public String test(){
        return "hello test";
    }

}
