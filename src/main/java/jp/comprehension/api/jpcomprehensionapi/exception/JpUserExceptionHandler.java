package jp.comprehension.api.jpcomprehensionapi.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class JpUserExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String USER_NOT_CREATED = "Couldn't create user. The username already exists";

    @ExceptionHandler(value = {JpUserCreateException.class})
    protected ResponseEntity<Object> handleUserCreateException(
            JpUserCreateException ex, WebRequest request
    ) {
        return handleExceptionInternal(ex, USER_NOT_CREATED, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
