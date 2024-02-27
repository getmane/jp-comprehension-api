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

    public List<JpUserWord> getAllUserWords(String username) {
        JpUser user = userService.getUserByUsername(username);
        return this.jpUserVocabRepository.findAllByJpUserId(user.getId());
    }

    public List<JpUserWord> saveReviewWords(String username, List<StandaloneWord> knownWords) {
        List<Word> savedWords = wordService.saveWords(knownWords);
        JpUser user = userService.getUserByUsername(username);

        return savedWords.stream().map(word -> saveNewWord(word, user))
                .filter(Objects::nonNull)
                .toList();
    }

    public List<JpUserWord> saveImmersionWords(String username, List<StandaloneWord> words) {
        List<Word> savedWords = wordService.saveWords(words);
        JpUser user = userService.getUserByUsername(username);

        return savedWords.stream()
                .map(word -> saveImmersionWord(word, user))
                .toList();
    }

    private JpUserWord saveImmersionWord(Word word, JpUser user) {
        Optional<JpUserWord> existingUserWord
                = jpUserVocabRepository.findFirstByWordIdAndJpUserId(word.getId(), user.getId());
        return existingUserWord.map(this::advanceWordProgress)
                .orElse(this.saveNewWord(word, user));
    }

    private JpUserWord advanceWordProgress(JpUserWord existingWord) {
        existingWord.setTimesSeen(existingWord.getTimesSeen() + 1);
        return jpUserVocabRepository.save(existingWord);
    }

    private JpUserWord saveNewWord(Word word, JpUser user) {
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
}
