package jp.comprehension.api.jpcomprehensionapi.service;

import jp.comprehension.api.jpcomprehensionapi.domain.JpUser;
import jp.comprehension.api.jpcomprehensionapi.domain.JpUserWord;
import jp.comprehension.api.jpcomprehensionapi.domain.Word;
import jp.comprehension.api.jpcomprehensionapi.repository.JpUserWordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JpUserWordService {

    private final WordService wordService;
    private final JpUserWordRepository jpUserVocabRepository;

    public List<JpUserWord> getUserWords(String userId) {
        return this.jpUserVocabRepository.findAllByJpUserId(userId);
    }

    public List<JpUserWord> saveUserWords(JpUser user, List<Word> newWords) {
        List<Word> savedWords = wordService.saveWords(newWords);

        return savedWords.stream().map(
                word -> saveUserWord(word, user)
        ).collect(Collectors.toList());
    }

    private JpUserWord saveUserWord(Word word, JpUser user) {
        Optional<JpUserWord> existingUserWord
                = jpUserVocabRepository.findFirstByWordId(word.getId());
        if (existingUserWord.isEmpty()) {
            JpUserWord newJpUserWord = JpUserWord.builder()
                    .jpUserId(user.getId())
                    .wordId(word.getId())
                    .timesSeen(1L)
                    .build();
            jpUserVocabRepository.save(newJpUserWord);
            return newJpUserWord;
        } else {
            JpUserWord existingWord = existingUserWord.get();
            existingWord.setTimesSeen(existingWord.getTimesSeen() + 1);
            jpUserVocabRepository.save(existingWord);
            return existingWord;
        }
    }
}
