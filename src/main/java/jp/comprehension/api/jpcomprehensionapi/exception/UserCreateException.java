package jp.comprehension.api.jpcomprehensionapi.exception;


import org.springframework.dao.DuplicateKeyException;

public class UserCreateException extends DuplicateKeyException {

    public UserCreateException(String msg) {
        super(msg);
    }
}
