package jp.comprehension.api.jpcomprehensionapi.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class UserExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String USER_NOT_CREATED = "Couldn't create user. The username already exists";

    @ExceptionHandler(value = {UserCreateException.class})
    protected ResponseEntity<Object> handleUserCreateException(
            UserCreateException ex, WebRequest request
    ) {
        return handleExceptionInternal(ex, USER_NOT_CREATED, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
