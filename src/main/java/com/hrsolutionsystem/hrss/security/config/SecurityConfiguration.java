package com.hrsolutionsystem.hrss.security.config;

import com.hrsolutionsystem.hrss.security.provider.RecruiterAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final RecruiterAuthProvider provider;
    private final static String REGISTRATION_ENDPOINT = "/v1/register/";

    @Autowired
    public SecurityConfiguration(RecruiterAuthProvider provider) {
        this.provider = provider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin().permitAll().defaultSuccessUrl( "/v1/admin/adminPage", true)
                .and()
                .logout().logoutSuccessUrl("/login").permitAll().deleteCookies("JSESSIONID").invalidateHttpSession(true)
                .and()
                .authorizeRequests().antMatchers(REGISTRATION_ENDPOINT + "*").permitAll()
                .and()
                .authorizeRequests().antMatchers("**").hasRole("ADMIN")
                .and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(provider);
    }

}
