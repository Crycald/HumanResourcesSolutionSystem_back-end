package com.hrsolutionsystem.hrss.security.config;

import com.hrsolutionsystem.hrss.security.provider.UserAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final static String DEFAULT_URL = "/v1";
    private final static String ADMIN_API = "/v1/admin";
    private final static String COMPANY_API = "/v1/company";
    private final static String RECRUITER_API = "/v1/recruiter";
    private final static String COVER_LETTER_API = "/v1/coverLetter";
    private final static String CV_DETAILS_API = "/v1/cvDetails";
    private final static String CV_FILE_API = "/v1/cvFile";
    private final static String INTERVIEW_API = "/v1/interviews";
    private final static String REGISTRATION_API = "/v1/register";
    private final static String WANTED_EMPLOYEE_API = "/v1/wantedEmployee";

    private final UserAuthProvider provider;


    @Autowired
    public SecurityConfiguration(UserAuthProvider provider) {
        this.provider = provider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/dupa").hasRole("USER")
                .and()
                .authorizeRequests()
                .anyRequest().hasRole("ADMIN")
                .and()
                .formLogin()
                .defaultSuccessUrl(DEFAULT_URL + "/default", true)
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(provider);
    }
}
