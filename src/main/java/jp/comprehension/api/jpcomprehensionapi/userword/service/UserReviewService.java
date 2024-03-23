package jp.comprehension.api.jpcomprehensionapi.userword.service;

import jp.comprehension.api.jpcomprehensionapi.user.domain.JpUser;
import jp.comprehension.api.jpcomprehensionapi.userword.domain.UserWord;
import jp.comprehension.api.jpcomprehensionapi.word.domain.Word;
import jp.comprehension.api.jpcomprehensionapi.word.dto.StandaloneWord;
import jp.comprehension.api.jpcomprehensionapi.userword.repository.UserWordRepository;
import jp.comprehension.api.jpcomprehensionapi.word.service.WordService;
import jp.comprehension.api.jpcomprehensionapi.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserReviewService implements UserWordService {

    private final WordService wordService;
    private final UserWordRepository jpUserVocabRepository;
    private final UserService userService;

    @Override
    public List<UserWord> saveWords(String username, List<StandaloneWord> wordsToSave) {
        List<Word> savedWords = wordService.saveWords(wordsToSave);
        JpUser user = userService.getUserByUsername(username);

        return savedWords.stream().map(word -> saveWord(word, user))
                .flatMap(Optional::stream)
                .toList();
    }

    @Override
    public Optional<UserWord> saveWord(Word word, JpUser user) {
        Optional<UserWord> existingUserWord
                = jpUserVocabRepository.findFirstByWordIdAndJpUserId(word.getId(), user.getId());
        return existingUserWord.isEmpty()
                ? Optional.of(jpUserVocabRepository.save(
                UserWord.builder()
                        .wordId(word.getId())
                        .jpUserId(user.getId())
                        .timesSeen(0L)
                        .build()))
                : Optional.empty();
    }
}
