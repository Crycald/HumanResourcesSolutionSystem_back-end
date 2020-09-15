package com.hrsolutionsystem.hrss.security.config;

import com.hrsolutionsystem.hrss.security.provider.RecruiterAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final RecruiterAuthProvider provider;

    @Autowired
    public SecurityConfiguration(RecruiterAuthProvider provider) {
        this.provider = provider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().hasRole("USER")
                .and().formLogin().permitAll().defaultSuccessUrl("/v1/recruiter/homePage", true)
                .and().logout().logoutSuccessUrl("/login").permitAll().deleteCookies("JSESSIONID").invalidateHttpSession(true)
                .and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(provider);
    }

}
