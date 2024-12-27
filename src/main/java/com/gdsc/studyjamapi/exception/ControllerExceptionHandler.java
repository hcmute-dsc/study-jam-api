package com.gdsc.studyjamapi.exception;

import com.gdsc.studyjamapi.dto.response.ErrorResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      @NonNull MethodArgumentNotValidException ex,
      @NonNull HttpHeaders headers,
      @NonNull HttpStatusCode status,
      @NonNull WebRequest request) {
    Map<String, List<String>> body = new HashMap<>();
    List<String> errors =
        ex.getBindingResult().getFieldErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .toList();
    body.put("errors", errors);

    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({
    EmailAlreadyExistsException.class,
          RemoteEntityDoesNotExist.class
  })
  public ResponseEntity<ErrorResponse> badRequestException(Exception exception) {
    return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.BAD_REQUEST);
  }

  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ExceptionHandler({
    BadCredentialsException.class,
  })
  public ResponseEntity<ErrorResponse> unauthorizedException(Exception exception) {
    return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.UNAUTHORIZED);
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler({NotFoundException.class})
  public ResponseEntity<ErrorResponse> notFoundException(Exception exception) {
    return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.NOT_FOUND);
  }
}
