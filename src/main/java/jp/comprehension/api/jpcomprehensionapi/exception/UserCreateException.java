package jp.comprehension.api.jpcomprehensionapi.exception;


import org.springframework.dao.DuplicateKeyException;

public class UserCreateException extends DuplicateKeyException {

    private static final String USER_NOT_CREATED_MESSAGE = "Could not create user. ";

    public UserCreateException(String msg) {
        super(USER_NOT_CREATED_MESSAGE + msg);
    }
}
