package uk.ac.ebi.test.rest;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<ErrorMessage> handleConstraintViolationException(ConstraintViolationException ex) {
        if (ex.getErrorCode() == 23505) { // 23505 is a constraint violation error Code for duplicate id or index
            return new ResponseEntity(new ErrorMessage(HttpStatus.CONFLICT, ex,
                    "Duplicate Index {first_name,last_name} provided"), HttpStatus.CONFLICT);
        }
        throw ex;
    }
}
