package jp.comprehension.api.jpcomprehensionapi.user.repository;

import jp.comprehension.api.jpcomprehensionapi.user.domain.JpUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<JpUser, String> {
    Optional<JpUser> findFirstByUsername(String email);
}
