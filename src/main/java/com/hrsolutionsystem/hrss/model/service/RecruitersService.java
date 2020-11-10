package com.hrsolutionsystem.hrss.model.service;

import com.hrsolutionsystem.hrss.exception.recruiter.notFound.RecruitersNotFoundException;
import com.hrsolutionsystem.hrss.exception.recruiter.unauthorized.RecruiterNotAuthorizedException;
import com.hrsolutionsystem.hrss.exception.security.passwordHasher.CannotPerformOperationException;
import com.hrsolutionsystem.hrss.exception.security.passwordHasher.InvalidHashException;
import com.hrsolutionsystem.hrss.model.dao.RecruitersDao;
import com.hrsolutionsystem.hrss.model.domain.dto.RecruitersDto;
import com.hrsolutionsystem.hrss.model.domain.entity.Recruiters;
import com.hrsolutionsystem.hrss.model.mapper.RecruitersMapper;
import com.hrsolutionsystem.hrss.model.service.email.registration.EmailRegistrationService;
import com.hrsolutionsystem.hrss.security.passwordHasher.PasswordHasher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class RecruitersService {
    private RecruitersDao repository;
    private RecruitersMapper mapper;
    private EmailRegistrationService emailRegistrationService;
    private Logger logger = LoggerFactory.getLogger(RecruitersService.class);

    @Autowired
    public RecruitersService(RecruitersDao repository, RecruitersMapper mapper, EmailRegistrationService emailRegistrationService) {
        this.repository = repository;
        this.mapper = mapper;
        this.emailRegistrationService = emailRegistrationService;
    }

    private RecruitersNotFoundException notFoundException(Long id){
        return new RecruitersNotFoundException(id);
    }

    private RecruiterNotAuthorizedException notAuthorizedException() {
        return new RecruiterNotAuthorizedException();
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

    public RecruitersDto findByLoginAndPassword(String login, String password) throws InvalidHashException, CannotPerformOperationException {
        Optional<Recruiters> recruiters = repository.findByLogin(login);

        if (recruiters.isPresent() && PasswordHasher.verifyPassword(password, recruiters.get().getPassword())) {
            return mapper.toDto(recruiters.orElseThrow(() -> notAuthorizedException()));
        } else {
            throw notAuthorizedException();
        }
    }

    public void sendInvite(String recipient) throws MalformedURLException {
        emailRegistrationService.createRegistrationEmail(recipient);
    }
}
