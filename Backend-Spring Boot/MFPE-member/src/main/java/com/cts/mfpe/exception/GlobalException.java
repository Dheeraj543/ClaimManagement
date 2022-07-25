package com.cts.mfpe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalException {
	@ExceptionHandler(value = Unauthorized.class)
	public ResponseEntity<Object> exception(Unauthorized exception) {
		return new ResponseEntity<>("You are notauthorized", HttpStatus.UNAUTHORIZED);
	}
	@ExceptionHandler(MissingRequestHeaderException.class)
	public ResponseEntity<Object> handleException(MissingRequestHeaderException ex) {
	    log.error("Error due to: " + ex.getMessage());
	    log.error(ex.getHeaderName());
	    log.error(ex.getLocalizedMessage());

	  //  ErrorResponse errorResponse = new ErrorResponse();

	    return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
	}
//	@ExceptionHandler(value = UnAuthorisedException.class)
//	public ResponseEntity<Object> exception(UnAuthorisedException exception) {
//		return new ResponseEntity<>("You are Not Authorised to perform this operation", HttpStatus.UNAUTHORIZED);
//	}
//	@ExceptionHandler(value = MissingRequestHeaderException.class)
//	public ResponseEntity<Object> exception(MissingRequestHeaderException exception) {
//		return new ResponseEntity<>("Provide Authorisation Token", HttpStatus.BAD_REQUEST);
//	}
}
