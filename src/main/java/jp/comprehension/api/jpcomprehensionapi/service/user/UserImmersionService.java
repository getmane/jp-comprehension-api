package jp.comprehension.api.jpcomprehensionapi.service.user;

import jp.comprehension.api.jpcomprehensionapi.domain.JpUser;
import jp.comprehension.api.jpcomprehensionapi.domain.UserWord;
import jp.comprehension.api.jpcomprehensionapi.domain.Word;
import jp.comprehension.api.jpcomprehensionapi.dto.StandaloneWord;
import jp.comprehension.api.jpcomprehensionapi.repository.UserWordRepository;
import jp.comprehension.api.jpcomprehensionapi.service.WordService;
import jp.comprehension.api.jpcomprehensionapi.service.login.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserImmersionService implements UserWordService {

    private final WordService wordService;
    private final UserWordRepository jpUserVocabRepository;
    private final UserService userService;

    public List<UserWord> getAllUserWords(String username) {
        JpUser user = userService.getUserByUsername(username);
        return this.jpUserVocabRepository.findAllByJpUserId(user.getId());
    }

    @Override
    public List<UserWord> saveWords(String username, List<StandaloneWord> wordsToSave) {
        List<Word> savedWords = wordService.saveWords(wordsToSave);
        JpUser user = userService.getUserByUsername(username);

        return savedWords.stream()
                .map(word -> saveWord(word, user))
                .flatMap(Optional::stream)
                .toList();
    }

    @Override
    public Optional<UserWord> saveWord(Word word, JpUser user) {
        Optional<UserWord> existingUserWord
                = jpUserVocabRepository.findFirstByWordIdAndJpUserId(word.getId(), user.getId());
        return existingUserWord.map(this::advanceWordProgress);
    }

    private UserWord advanceWordProgress(UserWord existingWord) {
        existingWord.setTimesSeen(existingWord.getTimesSeen() + 1);
        return jpUserVocabRepository.save(existingWord);
    }
}
