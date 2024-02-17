package jp.comprehension.api.jpcomprehensionapi.repository;

import jp.comprehension.api.jpcomprehensionapi.domain.Word;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WordRepository extends MongoRepository<Word, String> {
    Optional<Word> findFirstBySpellingAndReadingAndMeaning(String spelling, String reading, String meaning);
}
