package com.hrsolutionsystem.hrss.security.provider;

import com.hrsolutionsystem.hrss.exception.recruiter.unauthorized.RecruiterNotAuthorizedException;
import com.hrsolutionsystem.hrss.model.dao.RecruitersDao;
import com.hrsolutionsystem.hrss.security.interceptor.BadCredentialsInterceptor;
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
public class UserAuthProvider extends BadCredentialsInterceptor implements AuthenticationProvider {
    private final static String PREFIX = "ROLE_";
    private RecruitersDao repository;

    @Autowired
    public UserAuthProvider(RecruitersDao repository) {
        this.repository = repository;
    }

    private RecruiterNotAuthorizedException notAuthorizedException() {
        return new RecruiterNotAuthorizedException();
    }

    private BadCredentialsException badCredentialsException() {
        return new BadCredentialsException(notAuthorizedException().getMessage());
    }

    private List<SimpleGrantedAuthority> getAdminAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(PREFIX + "ADMIN");
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(authority);

        return authorityList;
    }

    public static List<SimpleGrantedAuthority> getUserAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(PREFIX + "USER");
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
            System.out.println("ADMIN");
            return new UsernamePasswordAuthenticationToken(givenLogin, givenLogin, getAdminAuthorities());
        } else if (repository.findByLogin(givenLogin).isPresent() && isValid) {
            System.out.println("USER");
            return new UsernamePasswordAuthenticationToken(givenLogin, givenLogin, getUserAuthorities());
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
