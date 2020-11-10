package com.hrsolutionsystem.hrss.model.service;

import com.hrsolutionsystem.hrss.exception.cvDetails.CvDetailsNotFoundException;
import com.hrsolutionsystem.hrss.exception.interview.InterviewNotFoundException;
import com.hrsolutionsystem.hrss.exception.recruiter.notFound.RecruitersNotFoundException;
import com.hrsolutionsystem.hrss.model.dao.CvDetailsDao;
import com.hrsolutionsystem.hrss.model.dao.InterviewDao;
import com.hrsolutionsystem.hrss.model.dao.RecruitersDao;
import com.hrsolutionsystem.hrss.model.domain.dto.InterviewDto;
import com.hrsolutionsystem.hrss.model.domain.entity.Interview;
import com.hrsolutionsystem.hrss.model.mapper.InterviewMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InterviewService {
    private InterviewMapper mapper;
    private InterviewDao repository;
    private RecruitersDao recruitersDao;
    private CvDetailsDao cvDetailsDao;
    private Logger logger = LoggerFactory.getLogger(InterviewService.class);

    @Autowired
    public InterviewService(InterviewMapper mapper, InterviewDao repository, RecruitersDao recruitersDao, CvDetailsDao cvDetailsDao) {
        this.mapper = mapper;
        this.repository = repository;
        this.recruitersDao = recruitersDao;
        this.cvDetailsDao = cvDetailsDao;
    }

    private InterviewNotFoundException notFound(Long id){
        return new InterviewNotFoundException(id);
    }

    private RecruitersNotFoundException recruitersNotFoundException(Long id) {
        return new RecruitersNotFoundException(id);
    }

    private CvDetailsNotFoundException cvDetailsNotFoundException(Long id) {
        return new CvDetailsNotFoundException(id);
    }

    public InterviewDto save(final InterviewDto interviewDto){
        Long cvDetailsId = interviewDto.getCvDetailsId();
        Long recruiterId = interviewDto.getRecruiterId();

        cvDetailsDao.findById(cvDetailsId).orElseThrow(() -> cvDetailsNotFoundException(cvDetailsId));
        recruitersDao.findById(recruiterId).orElseThrow(() -> recruitersNotFoundException(recruiterId));
        Interview interview = repository.save(mapper.toMap(interviewDto));
        logger.info("CREATED Interview with ID: " + interview.getId());

        return mapper.toDto(interview);
    }

    public InterviewDto findById(final Long id){
        Optional<Interview> interview = repository.findById(id);
        return mapper.toDto(interview.orElseThrow(()->notFound(id)));
    }

    public void deleteById(final Long id) {
        try {
            repository.deleteById(id);
            logger.info("DELETED Interview with ID: " + id);
        } catch (Exception e) {
            throw notFound(id);
        }
    }
}
