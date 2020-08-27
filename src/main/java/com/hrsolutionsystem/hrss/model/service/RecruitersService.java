package com.hrsolutionsystem.hrss.model.service;

import com.hrsolutionsystem.hrss.exception.recruiter.RecruitersNotFoundException;
import com.hrsolutionsystem.hrss.exception.security.passwordHasher.CannotPerformOperationException;
import com.hrsolutionsystem.hrss.model.dao.RecruitersDao;
import com.hrsolutionsystem.hrss.model.domain.dto.RecruitersDto;
import com.hrsolutionsystem.hrss.model.domain.entity.Recruiters;
import com.hrsolutionsystem.hrss.model.mapper.RecruitersMapper;
import com.hrsolutionsystem.hrss.security.passwordHasher.PasswordHasher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class RecruitersService {
    private RecruitersDao repository;
    private RecruitersMapper mapper;
    private Logger logger = LoggerFactory.getLogger(RecruitersService.class);

    @Autowired
    public RecruitersService(RecruitersDao repository, RecruitersMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    private RecruitersNotFoundException notFoundException(Long id){
        return new RecruitersNotFoundException(id);
    }

    private String hashPassword (String password) throws CannotPerformOperationException {
        return PasswordHasher.createHash(password);
    }

    public RecruitersDto findById(final Long id){
        Optional<Recruiters> recruiter = repository.findById(id);
        return mapper.toDto(recruiter.orElseThrow(()-> notFoundException(id)));
    }

    public List<RecruitersDto> recruitersGetList(){
        return mapper.toList(repository.findAll());
    }

    public void deleteByID(final Long id){
        try {
            repository.deleteById(id);
            logger.info("DELETED Recruiter with ID: " + id);
        } catch (Exception e) {
            throw notFoundException(id);
        }
    }

    public RecruitersDto save(RecruitersDto recruitersDto) throws CannotPerformOperationException {
        String hashedPassword = hashPassword(recruitersDto.getPassword());
        recruitersDto.setPassword(hashedPassword);

        Recruiters recruiter = repository.save(mapper.toMap(recruitersDto));
        logger.info("CREATED Recruiter with ID: " + recruiter.getId());

        return mapper.toDto(recruiter);
    }

    public RecruitersDto update(final RecruitersDto recruitersDto){
        repository.findById(recruitersDto.getId()).orElseThrow(()->notFoundException(recruitersDto.getId()));
        Recruiters recruiter = repository.save(mapper.toMap(recruitersDto));
        logger.info("UPDATED Recruiter with ID: " + recruiter.getId());

        return mapper.toDto(recruiter);
    }
}
