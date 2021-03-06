package com.hrsolutionsystem.hrss.exception.company;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CompanyNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(CompanyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String companyNotFoundHandler(CompanyNotFoundException exception) {
        return exception.getMessage();
    }
}
