package com.ericsson.sm.CarApp.validation;

import com.ericsson.sm.CarApp.exception.GenericValidationException;
import com.ericsson.sm.CarApp.model.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientValidation {
    public void validate(Client client){
        if(client.getFirstName().isBlank()){
            throw new GenericValidationException("First name is blank");
        }
        if(client.getLastName().isBlank()){
            throw new GenericValidationException("Last name is blank");
        }
        validateOib(client.getOib());


    }

    private void validateOib(String oib) {
        if(oib.isBlank()){
            throw new GenericValidationException("OIB is blank");
        }
        if(OibValidation.checkOIBState(oib) == 1){
            throw new GenericValidationException("length is incorrect");
        }
        if(OibValidation.checkOIBState(oib) == 2){
            throw new GenericValidationException("length is incorrect");
        }
        if(OibValidation.checkOIBState(oib) == 3){
            throw new GenericValidationException("length is incorrect");
        }
    }
}
