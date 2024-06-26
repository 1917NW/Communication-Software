package com.lxy.imapi.service.impl;

import com.lxy.imapi.constants.ProjectProperties;
import com.lxy.imapi.entity.ImUser;
import com.lxy.imapi.mapper.ImUserDao;
import com.lxy.imapi.po.UserRegisterDto;
import com.lxy.imapi.po.UserRegisterResult;
import com.lxy.imapi.service.ImServerURLService;
import com.lxy.imapi.service.RegisterService;
import com.lxy.protocolpackage.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;


@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ImUserDao imUserDao;

    @Autowired
    private ProjectProperties projectProperties;
    
    @Autowired
    private ImServerURLService imServerURLService;

    private void sendCodeToSMS(String code) {
        System.out.println("您收到的验证码为: "+ code + ", 5min内有效");
    }

    @Override
    public UserRegisterResult register(UserRegisterDto userRegisterDto) {
        System.out.println(userRegisterDto);
        UserRegisterResult userRegisterResult = new UserRegisterResult();

        // 从Redis中读取并验证code
        String code = stringRedisTemplate.opsForValue().get(projectProperties.getPrefix() + userRegisterDto.getUserPhone());

        if(code == null){
            userRegisterResult.setSuccess(false);
            userRegisterResult.setRemindMsg(UserRegisterResult.VERIFICATION_CODE_EXPIRED);
            return userRegisterResult;
        }

        if(!code.equals(userRegisterDto.getVerifyCode())){
            userRegisterResult.setSuccess(false);
            userRegisterResult.setRemindMsg(UserRegisterResult.VERIFICATION_CODE_NOT_MATCH);
            return userRegisterResult;
        }

        // 验证成功
        userRegisterResult.setSuccess(true);
        userRegisterResult.setRemindMsg(UserRegisterResult.VERIFICATION_CODE_MATCH);
        // 落库
        ImUser imUser = new ImUser();
        imUser.setUserId(userRegisterDto.getUserPhone());
        imUser.setUserHead("xxxx");
        imUser.setUserNickname(userRegisterDto.getUserNickname());
        imUser.setUserPassword(SecurityUtil.encrypt(userRegisterDto.getUserPassword()));
        Date date = new Date();
        imUser.setCreateTime(date);
        imUser.setUpdateTime(date);

        imUserDao.insert(imUser);

        String imServerURL = imServerURLService.getImServerURL();
        String inetHost = "";
        Integer inetPort = 0;

        if(imServerURL != ""){
            String[] ipAndPort = imServerURL.split("-");
            inetHost = ipAndPort[0];
            inetPort = Integer.valueOf(ipAndPort[1]);
            System.out.println("inetHost:"+inetHost + ", inetPort:"+inetPort);
        }

        userRegisterResult.setImServerIp(inetHost);
        userRegisterResult.setImServerPort(inetPort);

        return userRegisterResult;
    }

    @Override
    public void getCode(String userPhone) {
        Random random = new Random();
        String code = String.valueOf(random.nextInt(999999) + 100000);

        // 调用第三方SMS服务
        sendCodeToSMS(code);

        // 保存到Redis中
        stringRedisTemplate.opsForValue().set(projectProperties.getPrefix() + userPhone, code, 5, TimeUnit.MINUTES);
    }
}
