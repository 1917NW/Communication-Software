package com.lxy.protocolpackage.dto;

import java.io.Serializable;
import java.util.Objects;

public class UserDto implements Serializable {

    private String userId;       // 用户ID
    private String userNickName; // 用户昵称
    private String userHead;     // 用户头像
    private Integer status;      // 状态；0添加、1[保留]、2已添加

    public UserDto() {
    }

    public UserDto(String userId, String userNickName, String userHead) {
        this.userId = userId;
        this.userNickName = userNickName;
        this.userHead = userHead;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(userId, userDto.userId) && Objects.equals(userNickName, userDto.userNickName) && Objects.equals(userHead, userDto.userHead) && Objects.equals(status, userDto.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userNickName, userHead, status);
    }
}