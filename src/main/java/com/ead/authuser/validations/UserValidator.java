package com.ead.authuser.validations;

import com.ead.authuser.dtos.UserRecordDto;
import com.ead.authuser.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    Logger logger = LogManager.getLogger(UserValidator.class);

    private final Validator validator;
    final UserService userService;

    public UserValidator(@Qualifier("defaultValidator") Validator validator, UserService userService) {
        this.validator = validator;
        this.userService = userService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserRecordDto userRecordDto = (UserRecordDto) o;
        validator.validate(userRecordDto, errors);
        if (!errors.hasErrors()) {
            validateUserName(userRecordDto,errors);
            validateEmail(userRecordDto,errors);
        }
    }

    private void validateUserName(UserRecordDto userRecordDto, Errors errors) {
        if (userService.existsByUsername(userRecordDto.username())){
            errors.rejectValue("username", "usernameConflict","Error: Username is Already Taken!");
            logger.error("Error: validation Username:{} ", userRecordDto.username());
        }
    }

    private void validateEmail(UserRecordDto userRecordDto, Errors errors) {
        if (userService.existsByEmail(userRecordDto.email())){
            errors.rejectValue("email", "emailConflict","Error: Email is Already taken!");
            logger.error("Error: validation Email:{} ", userRecordDto.email());
        }
    }
}
