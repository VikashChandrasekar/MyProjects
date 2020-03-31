package com.example.restservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * This program will handle the runtime exceptions
 *
 * @author  Vikash Chandrasekar
 * @version 1.0
 * @since   2020-03-31
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends RuntimeException{

    /**
     * This method throws the 404 not found exception
     *
     * @param exception - exception
     */
    public RecordNotFoundException(String exception){
        super(exception);
    }
}
