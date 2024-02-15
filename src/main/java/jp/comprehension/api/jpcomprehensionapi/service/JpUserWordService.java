package jp.comprehension.api.jpcomprehensionapi.service;

import jp.comprehension.api.jpcomprehensionapi.domain.JpUser;
import jp.comprehension.api.jpcomprehensionapi.domain.JpUserWord;
import jp.comprehension.api.jpcomprehensionapi.domain.Word;
import jp.comprehension.api.jpcomprehensionapi.repository.JpUserWordRepository;
import jp.comprehension.api.jpcomprehensionapi.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JpUserWordService {

    private final WordRepository wordRepository;
    private final JpUserWordRepository jpUserVocabRepository;

    public List<JpUserWord> saveUserVocab(JpUser user, List<Word> words) {
        // wordRepository TODO: save missing words across corpus -> save jp user words only after that
        return words.stream().map(
                word -> saveUserWord(word, user)
        ).collect(Collectors.toList());
    }

    private JpUserWord saveUserWord(Word word, JpUser user) {
        JpUserWord existingUserWord
                = jpUserVocabRepository.findFirstByWordId(word.getId());
        if (existingUserWord == null) {
            JpUserWord newJpUserWord = JpUserWord.builder()
                    .jpUserId(user.getId())
                    .wordId(word.getId())
                    .timesSeen(1L)
                    .build();
            jpUserVocabRepository.save(newJpUserWord);
            return newJpUserWord;
        } else {
            existingUserWord.setTimesSeen(existingUserWord.getTimesSeen() + 1);
            jpUserVocabRepository.save(existingUserWord);
            return existingUserWord;
        }
    }
}