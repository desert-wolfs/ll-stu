/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.demo.config;

import com.example.demo.service.SystemUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Security configuration for OAuth WebClient.
 *
 * @author Joe Grandja
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	// http请求 过滤器
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// @formatter:off
		http
				// 配置授权
				.authorizeHttpRequests((authorize) -> authorize
						.requestMatchers( "/auth/**").permitAll()
						.anyRequest().authenticated()
				)
				.formLogin(withDefaults());
//				.oauth2Login(withDefaults())
//				.oauth2Client(withDefaults());
		// @formatter:on
		return http.build();
	}

	// v1.可以在这里定义UserDetailsService，指定用户名
//	@Bean
//	public UserDetailsService userDetailsService() {
//		// @formatter:off
//		UserDetails userDetails = User.withUsername("user")
//				// 密码password
//			.password("$2a$10$.rk/2d3h/gECY1QcKqa4buZS9w4XGCYrpggxgE251zFSbdTB9LY.y")
//			.roles("USER")
//			.build();
//		// @formatter:on
//		return new InMemoryUserDetailsManager(userDetails);
//	}

	@Autowired
	private SystemUserDetailService systemUserDetailService;

	// v2.定义鉴权管理
	@Bean
	public AuthenticationManager authenticationManager(PasswordEncoder passwordEncoder) throws Exception {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(systemUserDetailService);
		provider.setPasswordEncoder(passwordEncoder);
		return new ProviderManager(provider);
	}

	// 密码加密bean
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
