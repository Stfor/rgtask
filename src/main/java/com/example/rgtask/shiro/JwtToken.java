package com.example.rgtask.shiro;

import org.apache.shiro.authc.AuthenticationToken;

public class JwtToken implements AuthenticationToken {
    private String username;
    private String password;

    @Override
    public Object getPrincipal() {
        return username;
    }

    @Override
    public Object getCredentials() {
        return password;
    }

    public JwtToken(String username,String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "JwtToken{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
