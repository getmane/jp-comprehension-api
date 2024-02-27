package jp.comprehension.api.jpcomprehensionapi.exception;


import org.springframework.dao.DuplicateKeyException;

public class JpUserCreateException extends DuplicateKeyException {

    public JpUserCreateException(String msg) {
        super(msg);
    }
}
