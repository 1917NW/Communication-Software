package com.lxy.imapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lxy.imapi.entity.ImUser;
import com.lxy.imapi.mapper.ImUserDao;
import com.lxy.imapi.po.UserLoginDto;
import com.lxy.imapi.po.UserLoginResult;
import com.lxy.imapi.service.ImServerURLService;
import com.lxy.imapi.service.LoginService;
import com.lxy.protocolpackage.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private ImUserDao imUserDao;

    @Autowired
    ImServerURLService imServerURLService;

    @Override
    public UserLoginResult login(UserLoginDto userLoginDto) {
        UserLoginResult userLoginResult = new UserLoginResult();
        String userId = userLoginDto.getUserPhone();
        if(!StringUtils.hasLength(userId)){
            userLoginResult.setSuccess(false);
            userLoginResult.setRemindMsg(UserLoginResult.USER_ACCOUNT_EMPTY);
            return userLoginResult;
        }

        String userPassword = userLoginDto.getUserPassword();

        if(!StringUtils.hasLength(userPassword)){
            userLoginResult.setSuccess(false);
            userLoginResult.setRemindMsg(UserLoginResult.USER_PASSWORD_EMPTY);
            return userLoginResult;
        }


        LambdaQueryWrapper<ImUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ImUser::getUserId, userId);
        lambdaQueryWrapper.select(ImUser::getUserPassword);
        ImUser imUser = imUserDao.selectOne(lambdaQueryWrapper);

        if(imUser == null){
            userLoginResult.setSuccess(false);
            userLoginResult.setRemindMsg(UserLoginResult.USER_NOT_EXIST);
            return userLoginResult;
        }

        boolean result = SecurityUtil.check(userPassword, imUser.getUserPassword());
        userLoginResult.setSuccess(result);
        if(result){
            String imServerURL = imServerURLService.getImServerURL();
            String inetHost = "";
            Integer inetPort = 0;

            if(imServerURL != ""){
                String[] ipAndPort = imServerURL.split("-");
                inetHost = ipAndPort[0];
                inetPort = Integer.valueOf(ipAndPort[1]);
                System.out.println("inetHost:"+inetHost + ", inetPort:"+inetPort);
            }

            userLoginResult.setImServerIp(inetHost);
            userLoginResult.setImServerPort(inetPort);
        }else {
            userLoginResult.setRemindMsg(UserLoginResult.USER_ACCOUNT_DO_NOT_MATCH_PASSWORD);
            return userLoginResult;
        }

        return userLoginResult;
    }

    public boolean checkAuth(String userId, String userPassword) {
        if(!StringUtils.hasLength(userId)){
            return false;
        }

        if(!StringUtils.hasLength(userPassword))
            return false;

        LambdaQueryWrapper<ImUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ImUser::getUserId, userId);
        lambdaQueryWrapper.select(ImUser::getUserPassword);
        ImUser imUser = imUserDao.selectOne(lambdaQueryWrapper);

        return SecurityUtil.check(userPassword, imUser.getUserPassword());


    }

}
