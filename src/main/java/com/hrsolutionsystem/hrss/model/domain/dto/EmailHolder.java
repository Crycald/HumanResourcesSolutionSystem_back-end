package com.hrsolutionsystem.hrss.model.domain.dto;

import lombok.Data;

@Data
public class EmailHolder {
    private String recipient;
    private String subject;
    private String content;
}
