package com.gfilipeprojects.logisticapi.exceptionhandler;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@ControllerAdvice
public class LogisticExceptionHandler  extends ResponseEntityExceptionHandler {

    private MessageSource messageSource;
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<ErrorModel.Field> fields = new ArrayList<>();

        for (ObjectError error : ex.getAllErrors()) {
            String field = ((FieldError) error).getField();
            //String message = error.getDefaultMessage();
            String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());

            fields.add(new ErrorModel.Field(field, message));
        }
        ErrorModel errorModel = new ErrorModel();
        errorModel.setStatus(status.value());
        errorModel.setDateTime(LocalDateTime.now());
        errorModel.setTitle("Error: One or more fields are not valid. Please check it out and try again!");
        errorModel.setFields(fields);

        return handleExceptionInternal(ex, errorModel, headers, status, request);
    }
}
