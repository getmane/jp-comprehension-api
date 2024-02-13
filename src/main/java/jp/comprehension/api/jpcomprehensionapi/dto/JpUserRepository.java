package jp.comprehension.api.jpcomprehensionapi.dto;

import jp.comprehension.api.jpcomprehensionapi.domain.JpUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JpUserRepository extends MongoRepository<JpUser, String> {
    JpUser findFirstByUsername(String email);
}
