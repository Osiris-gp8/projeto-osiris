package br.com.bandtec.osirisapi.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandle {

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiResquestException(ApiRequestException e){
        ApiException apiException = new ApiException(e.getMessage(), e.getStatus(), ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, apiException.getStatus());
    }
}
