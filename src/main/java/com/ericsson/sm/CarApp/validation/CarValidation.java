package com.ericsson.sm.CarApp.validation;

import com.ericsson.sm.CarApp.exception.GenericValidationException;
import com.ericsson.sm.CarApp.model.Car;
import org.springframework.stereotype.Component;

@Component
public class CarValidation {

    public void validate(Car car){
        if(car.getCarType()==null){
            throw new GenericValidationException("Car type is null");
        }
        if(car.getManufactureYear()==null){
            throw new GenericValidationException("Manufacture year is null");
        }
        if(car.getCarType()==null){
            throw new GenericValidationException("Car type is null");
        }
        if(car.getColor().isBlank()){
            throw new GenericValidationException("Color is blank");
        }
        RegistrationMarkValidation.validate(car.getRegistrationMark());
    }
}
