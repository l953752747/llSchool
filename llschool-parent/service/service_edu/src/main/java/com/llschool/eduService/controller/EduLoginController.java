package com.llschool.eduService.controller;

import com.llschool.commonutils.RR;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(description = "登录")
@RequestMapping("/eduservice/userlogin")
@RestController
public class EduLoginController {

    @PostMapping("login")
    public RR login(){
        return RR.ok().data("token","admin");
    }

    @GetMapping("info")
    public RR info(){
        return RR.ok().data("roles","[admin]").data("name","[admin]").data("avatar","https://pixabay.com/zh/photos/woman-model-portrait-pose-style-6537397/");
    }
}
