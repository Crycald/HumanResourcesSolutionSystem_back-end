package com.hrsolutionsystem.hrss.model.service;

import com.hrsolutionsystem.hrss.exception.coverLetter.notfound.CoverLetterNotFoundException;
import com.hrsolutionsystem.hrss.exception.cvDetails.CvDetailsNotFoundException;
import com.hrsolutionsystem.hrss.exception.cvFile.notfound.CvFileNotFoundException;
import com.hrsolutionsystem.hrss.exception.recruiter.notFound.RecruitersNotFoundException;
import com.hrsolutionsystem.hrss.model.dao.CoverLetterDao;
import com.hrsolutionsystem.hrss.model.dao.CvDetailsDao;
import com.hrsolutionsystem.hrss.model.dao.CvFileDao;
import com.hrsolutionsystem.hrss.model.dao.RecruitersDao;
import com.hrsolutionsystem.hrss.model.domain.dto.CvDetailsDto;
import com.hrsolutionsystem.hrss.model.domain.entity.CvDetails;
import com.hrsolutionsystem.hrss.model.mapper.CvDetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CvDetailsService {
    private CvDetailsMapper mapper;
    private CvDetailsDao repository;
    private CvFileDao cvFileDao;
    private CoverLetterDao coverLetterDao;
    private RecruitersDao recruitersDao;
    private Logger logger = LoggerFactory.getLogger(CvDetailsService.class);

    @Autowired
    public CvDetailsService(CvDetailsMapper mapper, CvDetailsDao repository, CvFileDao cvFileDao, CoverLetterDao coverLetterDao, RecruitersDao recruitersDao) {
        this.mapper = mapper;
        this.repository = repository;
        this.cvFileDao = cvFileDao;
        this.coverLetterDao = coverLetterDao;
        this.recruitersDao = recruitersDao;
    }

    private CvDetailsNotFoundException notFound(Long id) {
        return new CvDetailsNotFoundException(id);
    }

    private CvFileNotFoundException cvFileNotFoundException(String id) {
        return new CvFileNotFoundException(id);
    }

    private CoverLetterNotFoundException coverLetterNotFoundException(String id) {
        return new CoverLetterNotFoundException(id);
    }

    private RecruitersNotFoundException recruitersNotFoundException(Long id) {
        return new RecruitersNotFoundException(id);
    }

    public CvDetailsDto save(final CvDetailsDto cvDetailsDto) {
        String cvFileId = cvDetailsDto.getCvFileId();
        String coverLetterId = cvDetailsDto.getCoverLetterId();
        Long recruiterId = cvDetailsDto.getRecruiterId();

        recruitersDao.findById(recruiterId).orElseThrow(() -> recruitersNotFoundException(recruiterId));
        cvFileDao.findById(cvFileId).orElseThrow(() -> cvFileNotFoundException(cvFileId));
        coverLetterDao.findById(coverLetterId).orElseThrow(() -> coverLetterNotFoundException(coverLetterId));
        CvDetails cvDetails = repository.save(mapper.toMap(cvDetailsDto));
        logger.info("CREATED CvDetails with ID: " + cvDetails.getId());

        return mapper.toDto(cvDetails);
    }

    public CvDetailsDto findById(final Long id) {
        Optional<CvDetails> cvDetails =  repository.findById(id);
        return mapper.toDto(cvDetails.orElseThrow(() -> notFound(id)));
    }

    public void deleteById(final Long id) {
        try {
            repository.deleteById(id);
            logger.info("DELETED CvDetails with ID: " + id);
        } catch (Exception e) {
            throw notFound(id);
        }
    }
}
