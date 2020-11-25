package com.hrsolutionsystem.hrss.model.service.externalAPIs;

import com.hrsolutionsystem.hrss.model.domain.dto.FreeGeoIPHolder;
import org.springframework.web.client.RestTemplate;

public class FreeGeoIP {
    private final static String API_URL = "https://freegeoip.app/json/";
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
