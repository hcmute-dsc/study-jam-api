package com.gdsc.studyjamapi.exception;

public class EntityNotExistedException extends  RuntimeException{
    public EntityNotExistedException(String e){
        super(e);
    }
}
