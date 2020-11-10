package com.hrsolutionsystem.hrss.exception.recruiter.unauthorized;

public class RecruiterNotAuthorizedException extends RuntimeException {
    public RecruiterNotAuthorizedException() {
        super("Incorrect login or password");
    }
}
