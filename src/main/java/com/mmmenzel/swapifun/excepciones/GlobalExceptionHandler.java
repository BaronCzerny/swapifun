package com.mmmenzel.swapifun.excepciones;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import jakarta.validation.ConstraintViolationException;


@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler  extends ResponseEntityExceptionHandler {
    
	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
    		NoHandlerFoundException exception, HttpHeaders headers,
    		HttpStatusCode status, WebRequest request) {
    	SwapiError swapiError = 
    			new SwapiError(exception.getStatusCode(), "Resource not found", exception);
    	log.info(swapiError.toString());
    	return new ResponseEntity<>(swapiError, exception.getStatusCode());
    } 

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
    		MissingServletRequestParameterException exception,
    		HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    	SwapiError swapiError = 
    			new SwapiError(HttpStatus.BAD_REQUEST, "Bad/Malformed request", exception);
    	log.info(swapiError.toString());
    	return new ResponseEntity<>(swapiError, HttpStatus.BAD_REQUEST);
    } 

	@ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<SwapiError> handleResponseStatusException(ResponseStatusException exception) {
        SwapiError swapiError = new SwapiError(exception.getStatusCode(), exception.getReason(), exception);
    	log.info(swapiError.toString());
        return new ResponseEntity<>(swapiError, exception.getStatusCode());
    }

	@ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<SwapiError> handleIllegalArgumentException(IllegalArgumentException exception) {
        SwapiError swapiError = new SwapiError(HttpStatus.CONFLICT, "The parameter cannot be blank", exception);
    	log.info(swapiError.toString());
        return new ResponseEntity<>(swapiError, HttpStatus.CONFLICT);
    }
	
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<SwapiError> handleConstraintValidationException(ConstraintViolationException exception) {
        SwapiError swapiError = new SwapiError(HttpStatus.CONFLICT, "The parameter cannot be blank", exception);
    	log.info(swapiError.toString());
        return new ResponseEntity<>(swapiError, HttpStatus.CONFLICT);
    }

} 