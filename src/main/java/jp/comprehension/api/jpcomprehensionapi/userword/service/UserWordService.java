package jp.comprehension.api.jpcomprehensionapi.userword.service;

import jp.comprehension.api.jpcomprehensionapi.user.domain.JpUser;
import jp.comprehension.api.jpcomprehensionapi.userword.domain.UserWord;
import jp.comprehension.api.jpcomprehensionapi.word.domain.Word;
import jp.comprehension.api.jpcomprehensionapi.word.dto.StandaloneWord;

import java.util.List;
import java.util.Optional;

public interface UserWordService {
    List<UserWord> saveWords(String username, List<StandaloneWord> wordsToSave);

    Optional<UserWord> saveWord(Word word, JpUser user);
}
