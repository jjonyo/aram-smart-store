package com.aram.smartstore.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

  @ExceptionHandler({IllegalArgumentException.class})
  public ResponseEntity<?> illegalArgumentException(final IllegalArgumentException ex) {
    ex.printStackTrace();
    return new ResponseEntity<>("잘못된 요청입니다.", HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({RuntimeException.class})
  public ResponseEntity<?> runTimeException(final RuntimeException ex) {
    ex.printStackTrace();
    return new ResponseEntity<>("서버 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
