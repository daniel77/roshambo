package com.ciklum.roshambo.controller;

import com.ciklum.roshambo.model.GameApiError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * All exceptions will be handled by this class.
 */
@ControllerAdvice
public class GameExceptionHandler {

  private final Logger logger = LoggerFactory.getLogger(GameExceptionHandler.class);

  @ExceptionHandler(Exception.class)
  public ResponseEntity<GameApiError> handleException(Exception e) {
    logger.error(e.getMessage());
    return new ResponseEntity<>(GameApiError.builder().status(INTERNAL_SERVER_ERROR.value()).message(e.getMessage()).build(), INTERNAL_SERVER_ERROR);
  }

}
