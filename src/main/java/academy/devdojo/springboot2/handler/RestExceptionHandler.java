package academy.devdojo.springboot2.handler;

import academy.devdojo.springboot2.exception.BadRequestException;
import academy.devdojo.springboot2.exception.BadRequestExceptionDetail;
import academy.devdojo.springboot2.exception.ExceptionDetail;
import academy.devdojo.springboot2.exception.ValidationExceptionDetail;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetail> handleBadRequestException(BadRequestException bre) {
        return new ResponseEntity<>(
                BadRequestExceptionDetail.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Exception, Check the Documentation")
                        .detail(bre.getMessage())
                        .developerMessage(bre.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));
        return new ResponseEntity<>(
                ValidationExceptionDetail.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Exception, Check the Documentation")
                        .detail("Check the field(s) error")
                        .developerMessage(exception.getClass().getName())
                        .fields(fields)
                        .fieldsMessage(fieldsMessage)
                        .build(), HttpStatus.BAD_REQUEST);

    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ExceptionDetail exceptionDetail = ExceptionDetail.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .title(ex.getCause().getMessage())
                .detail(ex.getMessage())
                .developerMessage(ex.getClass().getName())
                .build();

        return new ResponseEntity<>(exceptionDetail, headers, status);
    }
}
