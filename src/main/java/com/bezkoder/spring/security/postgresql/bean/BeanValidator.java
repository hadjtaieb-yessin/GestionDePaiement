package com.bezkoder.spring.security.postgresql.bean;
import java.util.ArrayList;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.bezkoder.spring.security.postgresql.models.Entreprise;
import org.springframework.stereotype.Component;

@Component

public class BeanValidator {
    public Validator getValidator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }
    public ArrayList<String> entrepriseValidate(Entreprise entreprise) {
        ArrayList<String> arrayList = new ArrayList<>();
        Set<ConstraintViolation<Entreprise>> constraintViolations = getValidator().validate(entreprise);
        for (ConstraintViolation<Entreprise> constraintViolation : constraintViolations) {
            if (constraintViolation.getPropertyPath().toString().equals("Name")) {
                arrayList.add(constraintViolation.getMessage());
            }
            if (constraintViolation.getPropertyPath().toString().equals("addresse")) {
                arrayList.add(constraintViolation.getMessage());
            }
            if (constraintViolation.getPropertyPath().toString().equals("phone_number")) {
                arrayList.add(constraintViolation.getMessage());
            }
            if (constraintViolation.getPropertyPath().toString().equals("email")) {
                arrayList.add(constraintViolation.getMessage());
            }
        }
        return arrayList;
    }
}
