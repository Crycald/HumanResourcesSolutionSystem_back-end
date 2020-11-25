package com.hrsolutionsystem.hrss.model.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvalidCredentialsIPs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String IP;

    @Column(name = "COUNTRY_CODE")
    private String countryCode;

    @Column(name = "COUNTRY_NAME")
    private String countryName;

    @Column(name = "REGION_NAME")
    private String regionName;

    @Column(name = "TIME_ZONE")
    private String timeZone;
}
