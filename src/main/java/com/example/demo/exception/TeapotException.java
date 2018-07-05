package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
    Show usage of @ResponseStatus
 */
@ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT, reason = "I am a teapot!")
public class TeapotException extends RuntimeException {
}
