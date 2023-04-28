package com.back.endTest.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(assignableTypes = RestScheduleController.class)
public class RestExHandler {

//  @ResponseStatus(HttpStatus.BAD_REQUEST)
//  @ExceptionHandler(Re)
}
