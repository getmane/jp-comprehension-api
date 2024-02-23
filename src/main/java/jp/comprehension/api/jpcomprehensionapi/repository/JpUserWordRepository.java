package jp.comprehension.api.jpcomprehensionapi.repository;

import jp.comprehension.api.jpcomprehensionapi.domain.JpUserWord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpUserWordRepository extends MongoRepository<JpUserWord, String> {
    Optional<JpUserWord> findFirstByWordIdAndJpUserId(String wordId, String jpUserId);

    List<JpUserWord> findAllByJpUserId(String jpUserId);
}
