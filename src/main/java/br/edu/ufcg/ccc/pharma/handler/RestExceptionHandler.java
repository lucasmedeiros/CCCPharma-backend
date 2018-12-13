package br.edu.ufcg.ccc.pharma.handler;

import br.edu.ufcg.ccc.pharma.exceptions.ExceptionDetails;
import br.edu.ufcg.ccc.pharma.exceptions.ResourceNotFoundDetails;
import br.edu.ufcg.ccc.pharma.exceptions.ResourceNotFoundException;
import br.edu.ufcg.ccc.pharma.exceptions.ValidationExceptionDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfException) {
        ResourceNotFoundDetails details = ResourceNotFoundDetails.Builder
                .newBuilder()
                .title("Resource not found")
                .details(rnfException.getMessage())
                .message(rnfException.getClass().getName())
                .timestamp(new Date().getTime())
                .status(HttpStatus.NOT_FOUND.value())
                .build();

        return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException manvException, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        List<FieldError> fieldErrors = manvException.getBindingResult().getFieldErrors();

        String fields = fieldErrors.stream().map(FieldError::getField)
                .collect(Collectors.joining(", "));

        String fieldMessages = fieldErrors.stream().map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));

        ValidationExceptionDetails details = ValidationExceptionDetails.Builder
                .newBuilder()
                .title("Field validation error")
                .details("There are invalid fields")
                .message(manvException.getClass().getName())
                .timestamp(new Date().getTime())
                .status(HttpStatus.BAD_REQUEST.value())
                .field(fields)
                .fieldMessage(fieldMessages)
                .build();

        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ExceptionDetails exceptionDetails = ExceptionDetails.Builder
                .newBuilder()
                .timestamp(new Date().getTime())
                .status(status.value())
                .title("Internal exception!")
                .details(ex.getMessage())
                .devMessage(ex.getClass().getName())
                .build();

        return new ResponseEntity<>(exceptionDetails, headers, status);
    }
}
