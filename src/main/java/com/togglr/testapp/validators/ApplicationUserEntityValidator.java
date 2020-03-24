package com.togglr.testapp.validators;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.togglr.testapp.entities.ApplicationUserEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("beforeCreateApplicationUserEntityValidator")
public class ApplicationUserEntityValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ApplicationUserEntity.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ApplicationUserEntity applicationUserEntity = (ApplicationUserEntity) o;

        if (checkInputString(applicationUserEntity.getEmail())) {
            errors.rejectValue("email", "email.required");
        }

        if (checkInputString(applicationUserEntity.getName())) {
            errors.rejectValue("name", "name.emptyvalue");
        }

    }

    private boolean checkInputString(String input) {
        String pattern = "^[-a-zA-Z0-9]+$"; //alphanumeric and hyphens
        System.out.println(input);
        System.out.println(input.matches(pattern));
        return (input == null || input.trim().length() == 0 || !input.matches(pattern));
    }
}
