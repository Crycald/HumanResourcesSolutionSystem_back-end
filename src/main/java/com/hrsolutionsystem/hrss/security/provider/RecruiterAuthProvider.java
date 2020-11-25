package com.hrsolutionsystem.hrss.security.provider;

import com.hrsolutionsystem.hrss.exception.recruiter.unauthorized.RecruiterNotAuthorizedException;
import com.hrsolutionsystem.hrss.model.dao.RecruitersDao;
import com.hrsolutionsystem.hrss.security.invalidcredentials.BadCredentialsInterceptor;
import com.hrsolutionsystem.hrss.security.passwordHasher.PasswordHasher;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RecruiterAuthProvider extends BadCredentialsInterceptor implements AuthenticationProvider {
    private RecruitersDao repository;
    private final static String PREFIX = "ROLE_";

    @Autowired
    public RecruiterAuthProvider(RecruitersDao repository) {
        this.repository = repository;
    }

    private RecruiterNotAuthorizedException notAuthorizedException() {
        return new RecruiterNotAuthorizedException();
    }

    private BadCredentialsException badCredentialsException() {
        return new BadCredentialsException(notAuthorizedException().getMessage());
    }

    private List<SimpleGrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(PREFIX + "USER");
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(authority);

        return authorityList;
    }

    private List<SimpleGrantedAuthority> getAdminAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(PREFIX + "ADMIN");
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(authority);

        return authorityList;
    }

    @SneakyThrows
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String givenLogin = authentication.getName();
        String givenPassword = authentication.getCredentials().toString();
        boolean isValid = PasswordHasher.verifyPassword(givenPassword, repository.findByLogin(givenLogin).get().getPassword());

        if (givenLogin.equals("admin") && isValid) {
            return new UsernamePasswordAuthenticationToken(givenLogin, givenLogin, getAdminAuthorities());
        } else if (repository.findByLogin(givenLogin).isPresent()) {
            if (isValid) {
                return new UsernamePasswordAuthenticationToken(givenLogin, givenPassword, getAuthorities());
            } else {
                super.interceptInvalidCredentials(givenLogin);
                throw badCredentialsException();
                }
            } else {
                super.interceptInvalidCredentials(givenLogin);
                throw badCredentialsException();
            }
        }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
