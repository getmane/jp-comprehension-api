package jp.comprehension.api.jpcomprehensionapi.exception;

import com.mongodb.DuplicateKeyException;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcernResult;
import org.bson.BsonDocument;

public class JpUserCreateException extends DuplicateKeyException {

    public JpUserCreateException(BsonDocument response, ServerAddress address, WriteConcernResult writeConcernResult) {
        super(response, address, writeConcernResult);
    }
}
