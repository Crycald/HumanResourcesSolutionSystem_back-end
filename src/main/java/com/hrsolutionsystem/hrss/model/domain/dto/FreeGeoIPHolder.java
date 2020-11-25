package com.hrsolutionsystem.hrss.model.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class FreeGeoIPHolder {
    private String ip;
    private String country_code;
    private String country_name;
    private String region_name;
    private String time_zone;
}
