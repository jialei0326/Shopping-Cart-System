package com.sam.mum.configuration;

import com.sam.mum.model.User;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;



public class PasswordValidator implements Validator {

    public boolean supports(Class<?> paramClass) {
        return User.class.equals(paramClass);
    }

    public void validate(Object obj, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "valid.password");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConf", "valid.passwordConf");
        User user = (User) obj;
        if (!user.getPassword().equals(user.getConfirmedPassword())) {
            errors.rejectValue("passwordConf", "valid.passwordConfDiff");
        }
    }
}