package com.lxy.imapi.controller;


import com.lxy.imapi.constants.ProjectProperties;
import com.lxy.imapi.entity.ImUser;
import com.lxy.imapi.mapper.ImUserDao;
import com.lxy.imapi.po.UserRegisterDto;
import com.lxy.imapi.po.UserRegisterResult;
import com.lxy.imapi.service.RegisterService;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/imSystem")
public class RegisterController {

   @Autowired
   private RegisterService registerService;


    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
    public UserRegisterResult register(@RequestBody UserRegisterDto userRegisterDto){
        return registerService.register(userRegisterDto);
    }

    @GetMapping("/getCode")
    public void getCode(String userPhone){
        registerService.getCode(userPhone);
    }


}
