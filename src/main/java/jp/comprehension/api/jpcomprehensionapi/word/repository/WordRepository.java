package jp.comprehension.api.jpcomprehensionapi.word.repository;

import jp.comprehension.api.jpcomprehensionapi.word.domain.Word;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WordRepository extends MongoRepository<Word, String> {
    Optional<Word> findFirstBySpellingAndReading(String spelling, String reading);
}
