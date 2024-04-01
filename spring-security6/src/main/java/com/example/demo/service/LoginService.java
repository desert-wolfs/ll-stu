package com.example.demo.service;

import com.example.demo.entity.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * 这里用一句话描述这个类的作用
 *
 * @author linwl
 * @date 2024/3/29 14:38
 */
@Service
public class LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        SystemUser systemUser = (SystemUser) authenticate.getPrincipal();
        return "login success, " + systemUser.getUsername();
    }
}
