package br.ufg.es.iapl.feriados.exception

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false))

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    final ResponseEntity<ExceptionResponse> handleResourceNotFoundException(ResourceNotFoundException ex,
                                                                               WebRequest request) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false))

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    final ResponseEntity<ExceptionResponse> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        String type = ex.getRequiredType().getSimpleName()
        String param = ex.getParameter().getParameterName()

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
                "Wrong type for parameter: " + param + ". Expected type: " + type,
                request.getDescription(false))

        return ResponseEntity.badRequest().body(exceptionResponse)
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false))

        return super.handleExceptionInternal(ex, exceptionResponse, headers, status, request)
    }

}

