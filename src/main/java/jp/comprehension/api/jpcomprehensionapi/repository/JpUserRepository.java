package jp.comprehension.api.jpcomprehensionapi.repository;

import jp.comprehension.api.jpcomprehensionapi.domain.JpUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpUserRepository extends MongoRepository<JpUser, String> {
    Optional<JpUser> findFirstByUsername(String email);
}
