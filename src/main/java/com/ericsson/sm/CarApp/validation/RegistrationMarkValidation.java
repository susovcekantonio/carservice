package com.ericsson.sm.CarApp.validation;

import com.ericsson.sm.CarApp.exception.GenericValidationException;
import org.springframework.stereotype.Component;

@Component
public class RegistrationMarkValidation {
    public static void validate(String registrationMark){
        if(registrationMark.isBlank()){
            throw new GenericValidationException("Registration mark is blank");
        }
        if(registrationMark.length()>11 || registrationMark.length()<9){
            throw new GenericValidationException("Registration mark length not correct");
        }
        if(!Character.isLetter(registrationMark.charAt(0)) ||
           !Character.isLetter(registrationMark.charAt(1))||
            !Character.isWhitespace(registrationMark.charAt(2))||
            !Character.isWhitespace(registrationMark.charAt(registrationMark.length()-3))||
            !Character.isLetter(registrationMark.charAt(registrationMark.length()-1))||
            !Character.isLetter(registrationMark.charAt(registrationMark.length()-2)) )
        {
            throw new GenericValidationException("Registration format bad");
        }
        for(int i=3;i<registrationMark.length()-3;i++){
            if(!Character.isDigit(registrationMark.charAt(i))){
                throw new GenericValidationException(("Registration format bad"));
            }
        }
    }
}
