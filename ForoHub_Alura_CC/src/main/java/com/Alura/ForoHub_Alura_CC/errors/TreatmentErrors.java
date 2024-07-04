package com.Alura.ForoHub_Alura_CC.errors;

import com.Alura.ForoHub_Alura_CC.DtoResponses.errors.DtoError;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TreatmentErrors {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity error404(){return ResponseEntity.notFound().build();}

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity errorHandlerValidacionesDeNegocio(Exception e){

        DtoError dtoError = new DtoError("404",
                e.getMessage());

        return  ResponseEntity.badRequest().body(dtoError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity error400(MethodArgumentNotValidException e){

        var errorBadRequest = e.getFieldErrors().stream()
                .map(DataValidationError::new).toList();

        return ResponseEntity.badRequest().body(errorBadRequest);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity retunrNullPointer(MethodArgumentNotValidException e){
        DtoError dtoError = new DtoError("404",
                e.getMessage());

        return ResponseEntity.badRequest().body(dtoError);
    }
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity returIllegalStateException(MethodArgumentNotValidException e){
        DtoError dtoError = new DtoError("404",
                e.getMessage());

        return ResponseEntity.badRequest().body(dtoError);
    }

    private record DataValidationError(String variable, String message){
        public DataValidationError(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
