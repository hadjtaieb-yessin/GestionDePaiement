package com.bezkoder.spring.security.postgresql.bean;
import java.util.ArrayList;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.bezkoder.spring.security.postgresql.models.*;
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

    public ArrayList<String> departementValidate(Departement departement) {
        ArrayList<String> arrayList = new ArrayList<>();
        Set<ConstraintViolation<Departement>> constraintViolations = getValidator().validate(departement);
        for (ConstraintViolation<Departement> constraintViolation : constraintViolations) {
            if (constraintViolation.getPropertyPath().toString().equals("name")) {
                arrayList.add(constraintViolation.getMessage());
            }
            if (constraintViolation.getPropertyPath().toString().equals("nomDepartement")) {
                arrayList.add(constraintViolation.getMessage());
            }

            if (constraintViolation.getPropertyPath().toString().equals("budget")) {
                arrayList.add(constraintViolation.getMessage());
            }

        }
        return arrayList;
    }

    public ArrayList<String> depenseValidate(Depense depense) {
        ArrayList<String> arrayList = new ArrayList<>();
        Set<ConstraintViolation<Depense>> constraintViolations = getValidator().validate(depense);
        for (ConstraintViolation<Depense> constraintViolation : constraintViolations) {
            if (constraintViolation.getPropertyPath().toString().equals("description")) {
                arrayList.add(constraintViolation.getMessage());
            }
            if (constraintViolation.getPropertyPath().toString().equals("somme")) {
                arrayList.add(constraintViolation.getMessage());
            }

            if (constraintViolation.getPropertyPath().toString().equals("dateDepense")) {
                arrayList.add(constraintViolation.getMessage());
            }

        }
        return arrayList;
    }

        public ArrayList<String> congeValidate(Conge conge) {
            ArrayList<String> arrayList = new ArrayList<>();
            Set<ConstraintViolation<Conge>> constraintViolations = getValidator().validate(conge);
            for (ConstraintViolation<Conge> constraintViolation : constraintViolations) {
                if (constraintViolation.getPropertyPath().toString().equals("descriptionConge")) {
                    arrayList.add(constraintViolation.getMessage());
                }
                if (constraintViolation.getPropertyPath().toString().equals("dateDebutConge")) {
                    arrayList.add(constraintViolation.getMessage());
                }

                if (constraintViolation.getPropertyPath().toString().equals("dateFinConge")) {
                    arrayList.add(constraintViolation.getMessage());
                }

            }
            return arrayList;
    }


    public ArrayList<String> teletravailValidate(Teletravail teletravail) {
        ArrayList<String> arrayList = new ArrayList<>();
        Set<ConstraintViolation<Teletravail>> constraintViolations = getValidator().validate(teletravail);
        for (ConstraintViolation<Teletravail> constraintViolation : constraintViolations) {
            if (constraintViolation.getPropertyPath().toString().equals("dateDebutTeletravail")) {
                arrayList.add(constraintViolation.getMessage());
            }
            if (constraintViolation.getPropertyPath().toString().equals("dateFinTeletravail")) {
                arrayList.add(constraintViolation.getMessage());
            }

        }
        return arrayList;
    }


}
