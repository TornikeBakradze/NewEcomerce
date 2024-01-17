package ge.ecomerce.newecomerce.controller;

import ge.ecomerce.newecomerce.exception.DataNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<String> handleDataNotFoundException(DataNotFoundException ex) {
        log.error("Data not fount exception: {}", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        log.error("Data integrity exception: {}", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<List<List<String>>> handleBindErrors(MethodArgumentNotValidException exception) {

        List<List<String>> errorList = exception.getFieldErrors().stream()
                .map(fieldError -> {
                    List<String> errorMap = new ArrayList<>();
                    errorMap.add(fieldError.getDefaultMessage());
                    return errorMap;
                }).collect(Collectors.toList());

        log.error(errorList.toString());

        return ResponseEntity.badRequest().body(errorList);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<String> handleJpaException(ConstraintViolationException e) {
        String errorMessage = null;
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            errorMessage = constraintViolation.getMessage();
        }
        log.error(errorMessage);
        return ResponseEntity.badRequest().body(errorMessage);
    }
}
