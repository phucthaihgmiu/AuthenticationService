package com.example.authenticationservice.util.http;

import com.example.authenticationservice.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(MyException.class)
    public @ResponseBody HttpErrorInfo handleNotFoundExceptions(HttpServletRequest request, MyException ex) {
        return createHttpErrorInfo(HttpStatus.NOT_FOUND, request, ex);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public HttpErrorInfo handleMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException ex) {
        final String path = request.getRequestURI();

        // Get all validation errors
        final String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));
        return new HttpErrorInfo(HttpStatus.BAD_REQUEST, path, message);
    }

    private HttpErrorInfo createHttpErrorInfo(HttpStatus httpStatus, HttpServletRequest request, Exception ex) {

        final String path = request.getRequestURI();
        final String message = ex.getMessage();

        log.debug("Returning HTTP status: {} for path: {}, message: {}", httpStatus, path, message);
        return new HttpErrorInfo(httpStatus, path, message);
    }
}