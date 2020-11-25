package com.hrsolutionsystem.hrss.model.service.externalAPIs;

import com.hrsolutionsystem.hrss.model.domain.dto.FreeGeoIPHolder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

public class FreeGeoIP {
    @Value("${free.geo.ip.endpoint}")
    private String API_URL;

    private RestTemplate restTemplate = new RestTemplate();
    private FreeGeoIPHolder holder = new FreeGeoIPHolder();

    protected FreeGeoIPHolder getDataFromAPI() throws Exception {
        try {
            holder = restTemplate.getForObject(API_URL, FreeGeoIPHolder.class);
            return holder;
        } catch (Exception e) {
            throw new Exception("There was an error with external API");
        }
    }
}
