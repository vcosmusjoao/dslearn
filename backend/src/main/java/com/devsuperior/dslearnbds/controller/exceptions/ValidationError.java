package com.devsuperior.dslearnbds.controller.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

    private List<FieldMessage> errors = new ArrayList<>();

    public List<FieldMessage> getErrorsList() {
        return errors;
    }

    public void addError( String fieldName, String message){
        errors.add(new FieldMessage(fieldName,message));
    }

}
