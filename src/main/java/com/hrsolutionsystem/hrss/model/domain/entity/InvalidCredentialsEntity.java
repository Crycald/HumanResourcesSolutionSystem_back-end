package com.hrsolutionsystem.hrss.model.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "INVALID_CREDENTIALS_DETAILS")
public class InvalidCredentialsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String IP;

    @Column(name = "USED_LOGIN")
    private String login;

    @Column(name = "DATE_TIME")
    private LocalDateTime dateTime;

    @Column(name = "COUNTRY_CODE")
    private String countryCode;

    @Column(name = "COUNTRY_NAME")
    private String countryName;

    @Column(name = "REGION_NAME")
    private String regionName;

    @Column(name = "TIME_ZONE")
    private String timeZone;
}
