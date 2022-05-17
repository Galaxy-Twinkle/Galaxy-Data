package com.galaxy.data.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;

/**
 * @author yao.qian
 */
@TableName("test")
@Builder
public class TestEntity {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户密码加密秘钥
     */
    private String secretKey;

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
