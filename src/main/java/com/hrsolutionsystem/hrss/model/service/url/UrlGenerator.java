package com.hrsolutionsystem.hrss.model.service.url;

import java.util.UUID;

public class UrlGenerator {
    private String finalURL;

    public String createUrl() {
        UUID uuid = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();

        String url1 = uuid.toString().replaceAll("-", "").substring(0, 32);
        String url2 = uuid2.toString().replaceAll("-", "").substring(0, 32);
        finalURL = url1 + url2;

        return finalURL;
    }
}
