package jp.comprehension.api.jpcomprehensionapi.repository;

import jp.comprehension.api.jpcomprehensionapi.domain.JpUserWord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpUserWordRepository extends MongoRepository<JpUserWord, String> {
    JpUserWord findFirstByWordId(String wordId);
}
