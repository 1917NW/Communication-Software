package com.lxy.imapi.controller;

import com.lxy.imapi.po.UserLoginDto;
import com.lxy.imapi.po.UserLoginResult;
import com.lxy.imapi.po.UserRegisterDto;
import com.lxy.imapi.po.UserRegisterResult;
import com.lxy.imapi.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/imSystem")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
    public UserLoginResult Login(@RequestBody UserLoginDto userLoginDto){
        return loginService.login(userLoginDto);
    }
}
