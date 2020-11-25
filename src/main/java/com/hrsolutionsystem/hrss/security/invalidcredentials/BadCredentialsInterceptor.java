package com.hrsolutionsystem.hrss.security.invalidcredentials;

import com.hrsolutionsystem.hrss.model.domain.dto.FreeGeoIPHolder;
import com.hrsolutionsystem.hrss.model.domain.entity.InvalidCredentialsEntity;
import com.hrsolutionsystem.hrss.model.service.externalAPIs.FreeGeoIP;
import com.hrsolutionsystem.hrss.model.service.externalAPIs.FreeGeoIPService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class BadCredentialsInterceptor extends FreeGeoIP {
    private String login;

    @Autowired
    private FreeGeoIPService service;

    private void persistAttempt() throws Exception {
            FreeGeoIPHolder dataFromAPI = super.getDataFromAPI();
            InvalidCredentialsEntity invalidCredentialsEntity = InvalidCredentialsEntity.builder()
                    .IP(dataFromAPI.getIp())
                    .login(login)
                    .dateTime(LocalDateTime.now())
                    .countryCode(dataFromAPI.getCountry_code())
                    .countryName(dataFromAPI.getCountry_name())
                    .regionName(dataFromAPI.getRegion_name())
                    .timeZone(dataFromAPI.getTime_zone())
                    .build();

            service.save(invalidCredentialsEntity);
    }

    protected void interceptInvalidCredentials(String login) throws Exception {
        this.setLogin(login);
        this.persistAttempt();
    }

    private void setLogin(String login) {
        this.login = login;
    }
}
