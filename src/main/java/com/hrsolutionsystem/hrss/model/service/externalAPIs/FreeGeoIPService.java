package com.hrsolutionsystem.hrss.model.service.externalAPIs;

import com.hrsolutionsystem.hrss.model.dao.externalAPIs.FreeGeoIPDao;
import com.hrsolutionsystem.hrss.model.domain.entity.InvalidCredentialsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FreeGeoIPService {
    final FreeGeoIPDao freeGeoIPDao;

    @Autowired
    public FreeGeoIPService(FreeGeoIPDao freeGeoIPDao) {
        this.freeGeoIPDao = freeGeoIPDao;
    }

    public void save(InvalidCredentialsEntity data) {
        freeGeoIPDao.save(data);
    }
}
