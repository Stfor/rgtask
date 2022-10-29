package com.example.rgtask.shiro;

import org.apache.shiro.authc.AuthenticationToken;

public class JwtToken implements AuthenticationToken {
    private String username;
    private String userid;

    @Override
    public Object getPrincipal() {
        return userid;
    }

    @Override
    public Object getCredentials() {
        return username;
    }

    public JwtToken(String userid,String username) {
        this.username = username;
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "JwtToken{" +
                "username='" + username + '\'' +
                ", userid='" + userid + '\'' +
                '}';
    }
}
