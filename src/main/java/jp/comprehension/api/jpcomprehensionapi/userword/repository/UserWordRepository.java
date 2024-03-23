package jp.comprehension.api.jpcomprehensionapi.userword.repository;

import jp.comprehension.api.jpcomprehensionapi.userword.domain.UserWord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserWordRepository extends MongoRepository<UserWord, String> {
    Optional<UserWord> findFirstByWordIdAndJpUserId(String wordId, String jpUserId);

    List<UserWord> findAllByJpUserId(String jpUserId);
}
