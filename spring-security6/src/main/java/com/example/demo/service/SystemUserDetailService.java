package com.example.demo.service;

import com.example.demo.entity.SystemUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 这里用一句话描述这个类的作用
 *
 * @author linwl
 * @date 2024/3/29 14:05
 */
@Service
public class SystemUserDetailService implements UserDetailsService {

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询数据库
        SystemUser systemUser = new SystemUser();
        systemUser.setUser("user");
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        systemUser.setPassword(passwordEncoder.encode("password"));
        return systemUser;

    }
}
