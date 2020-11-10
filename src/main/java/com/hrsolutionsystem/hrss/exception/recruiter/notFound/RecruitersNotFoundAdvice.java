package com.hrsolutionsystem.hrss.exception.recruiter.notFound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RecruitersNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(RecruitersNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String recruitersNotFoundHandler(RecruitersNotFoundException exception){
        return exception.getMessage();
    }
}
