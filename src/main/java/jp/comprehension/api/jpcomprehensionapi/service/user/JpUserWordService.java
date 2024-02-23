package jp.comprehension.api.jpcomprehensionapi.service.user;

import jp.comprehension.api.jpcomprehensionapi.domain.JpUser;
import jp.comprehension.api.jpcomprehensionapi.domain.JpUserWord;
import jp.comprehension.api.jpcomprehensionapi.domain.Word;
import jp.comprehension.api.jpcomprehensionapi.dto.StandaloneWord;
import jp.comprehension.api.jpcomprehensionapi.repository.JpUserWordRepository;
import jp.comprehension.api.jpcomprehensionapi.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JpUserWordService {

    private final WordService wordService;
    private final JpUserWordRepository jpUserVocabRepository;
    private final JpUserService userService;

    public List<JpUserWord> getUserWords(String userId) {
        return this.jpUserVocabRepository.findAllByJpUserId(userId);
    }

    public List<JpUserWord> saveKnownWords(String username, List<StandaloneWord> knownWords) {
        List<Word> savedWords = wordService.saveWords(knownWords);
        JpUser user = userService.getUserByUsername(username);

        return savedWords.stream().map(word -> saveKnownWord(word, user))
                .filter(Objects::nonNull)
                .toList();
    }

    public List<JpUserWord> saveImmersionWordsProgress(String username, List<StandaloneWord> newWords) {
        List<Word> savedWords = wordService.saveWords(newWords);
        JpUser user = userService.getUserByUsername(username);

        return savedWords.stream().map(word -> saveImmersionWordProgress(word, user))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    private JpUserWord saveKnownWord(Word word, JpUser user) {
        Optional<JpUserWord> existingUserWord
                = jpUserVocabRepository.findFirstByWordIdAndJpUserId(word.getId(), user.getId());
        return existingUserWord.isEmpty()
                ? jpUserVocabRepository.save(
                JpUserWord.builder()
                        .wordId(word.getId())
                        .jpUserId(user.getId()).timesSeen(0L)
                        .build())
                : null;
    }

    private Optional<JpUserWord> saveImmersionWordProgress(Word word, JpUser user) {
        Optional<JpUserWord> existingUserWord
                = jpUserVocabRepository.findFirstByWordIdAndJpUserId(word.getId(), user.getId());
        return existingUserWord.map(existingWord -> {
            existingWord.setTimesSeen(existingWord.getTimesSeen() + 1);
            jpUserVocabRepository.save(existingWord);
            return existingWord;
        });
    }
}
