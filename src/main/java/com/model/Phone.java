package com.model;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class Phone implements Validator {
    private String number;

    public String getNumber() {
        return number;
    }

    public Phone setNumber(String number) {
        this.number = number;
        return this;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Phone.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Phone phoneNumber = (Phone) target;
        String number = phoneNumber.getNumber();
        ValidationUtils.rejectIfEmpty(errors, "number", "number.empty");
        if (number.length()>11 || number.length()<10){
            errors.rejectValue("number", "number.length");
        }
        if (!number.startsWith("0")){
            errors.rejectValue("number", "number.startsWith");
        }
        if (!number.matches("(^$|[0-9]*$)")){
            errors.rejectValue("number", "number.matches");
        }
    }
}
