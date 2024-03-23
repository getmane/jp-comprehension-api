package jp.comprehension.api.jpcomprehensionapi.word.service;

import jp.comprehension.api.jpcomprehensionapi.word.domain.Word;
import jp.comprehension.api.jpcomprehensionapi.word.dto.StandaloneWord;
import jp.comprehension.api.jpcomprehensionapi.word.mapper.WordMapper;
import jp.comprehension.api.jpcomprehensionapi.word.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WordService {

    private final WordRepository repository;
    private final WordMapper mapper;

    public List<Word> saveWords(List<StandaloneWord> words) {
        return words.stream().map(this::saveWord).toList();
    }

    public Word saveWord(StandaloneWord word) {
        Optional<Word> existingWord = repository.findFirstBySpellingAndReading(
                word.getSpelling(), word.getReading()
        );

        return existingWord.orElseGet(() -> repository.save(mapper.toWord(word)));
    }
}
