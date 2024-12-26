package com.jpets.utils.exceptions;

public class IdNotFoundException extends RuntimeException{
    private static final String ERROR_MESSAGE = "No se encontró %s con el id: %s";
    
    public IdNotFoundException(String nameEntity, String id){
        super(String.format(ERROR_MESSAGE, nameEntity, id));
    }
}
