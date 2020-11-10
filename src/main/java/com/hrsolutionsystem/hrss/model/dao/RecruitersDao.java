package com.hrsolutionsystem.hrss.model.dao;

import com.hrsolutionsystem.hrss.model.domain.entity.Recruiters;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecruitersDao extends CrudRepository<Recruiters, Long> {
    @Override
    Optional<Recruiters> findById(Long id);

    @Override
    List<Recruiters> findAll();

    @Override
    void deleteById(Long id);

    @Override
    Recruiters save(Recruiters recruiters);

    @Override
    long count();

    Optional<Recruiters> findByLogin(String login);
}
