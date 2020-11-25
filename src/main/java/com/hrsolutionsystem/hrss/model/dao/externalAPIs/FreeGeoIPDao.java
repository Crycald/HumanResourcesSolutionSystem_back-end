package com.hrsolutionsystem.hrss.model.dao.externalAPIs;

import com.hrsolutionsystem.hrss.model.domain.entity.InvalidCredentialsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreeGeoIPDao extends CrudRepository<InvalidCredentialsEntity, Long> {

    @Override
    InvalidCredentialsEntity save(InvalidCredentialsEntity invalidCredentialsEntity);
}
