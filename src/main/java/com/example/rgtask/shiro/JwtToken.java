package com.example.rgtask.shiro;

import org.apache.shiro.authc.AuthenticationToken;

public class JwtToken implements AuthenticationToken {
    private String userId;
    private String userName;

    @Override
    public Object getPrincipal() {
        return userId;
    }

    @Override
    public Object getCredentials() {
        return userName;
    }

    public JwtToken(String username,String password) {
        this.userId = username;
        this.userName = password;
    }

    @Override
    public String toString() {
        return "JwtToken{" +
                "username='" + userId + '\'' +
                ", password='" + userName + '\'' +
                '}';
    }
}
