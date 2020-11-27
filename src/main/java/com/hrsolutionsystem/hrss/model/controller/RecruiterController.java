package com.hrsolutionsystem.hrss.model.controller;

import com.hrsolutionsystem.hrss.exception.security.passwordHasher.CannotPerformOperationException;
import com.hrsolutionsystem.hrss.exception.security.passwordHasher.InvalidHashException;
import com.hrsolutionsystem.hrss.model.domain.dto.RecruiterHolder;
import com.hrsolutionsystem.hrss.model.domain.dto.RecruitersDto;
import com.hrsolutionsystem.hrss.model.service.RecruitersService;
import com.hrsolutionsystem.hrss.model.service.email.registration.EmailRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/recruiter")
@CrossOrigin("*")
public class RecruiterController {
    private RecruitersService service;
    private EmailRegistrationService emailRegistrationService;

    @Autowired
    public RecruiterController(RecruitersService service, EmailRegistrationService emailRegistrationService) {
        this.service = service;
        this.emailRegistrationService = emailRegistrationService;
    }

    @PostMapping(value = "/create")
    public RecruitersDto saveRecruiter(@RequestBody RecruitersDto recruitersDto) throws CannotPerformOperationException {
        return service.save(recruitersDto);
    }

    @PutMapping(value = "/update")
    public RecruitersDto updateRecruiter(@RequestBody RecruitersDto recruitersDto){
        return service.update(recruitersDto);
    }

    @GetMapping(value = "/{id}")
    public RecruitersDto findRecruiterById(@PathVariable Long id){
        return service.findById(id);
    }

    @GetMapping(value = "/list")
    public List<RecruitersDto> getRecruiterList(){
        return service.recruitersGetList();
    }

    @DeleteMapping(value = "/{id}")
    public void deleteRecruiterById(@PathVariable Long id){
        service.deleteByID(id);
    }

    @GetMapping(value = "/verifyUser")
    public RecruitersDto verifyUser(@RequestBody RecruiterHolder recruiterHolder) throws CannotPerformOperationException, InvalidHashException {
        return service.findByLoginAndPassword(recruiterHolder.getLogin(), recruiterHolder.getPassword());
    }
}
