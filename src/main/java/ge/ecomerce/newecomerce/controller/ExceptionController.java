package ge.ecomerce.newecomerce.controller;

import ge.ecomerce.newecomerce.exception.DataNotFoundException;
import ge.ecomerce.newecomerce.exception.UpdatePasswordException;
import ge.ecomerce.newecomerce.exception.UserAlreadyExistException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
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

    @ExceptionHandler(UserAlreadyExistException.class)
    ResponseEntity<String> serAlreadyExistException(UserAlreadyExistException e) {
        log.error("User already exist exception: {}", e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler(BadCredentialsException.class)
    ResponseEntity<String> badCredentialsException(BadCredentialsException e) {
        log.error("badCredentialsException: {}", e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UpdatePasswordException.class)
    ResponseEntity<String> updatePasswordException(UpdatePasswordException e) {
        log.error("Update Passwor Exception: {}", e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
