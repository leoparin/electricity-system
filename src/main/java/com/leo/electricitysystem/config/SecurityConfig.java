package com.leo.electricitysystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * ClassName:SecurityConfig
 * PackageName:com.leo.electricitysystem.config
 * Description:
 *
 * @Date 2023/1/10 18:02
 * @Author leo
 **/
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/ticket")
                .anonymous()
                .and()
                .antMatcher("/error")
                .anonymous();
    }
}
