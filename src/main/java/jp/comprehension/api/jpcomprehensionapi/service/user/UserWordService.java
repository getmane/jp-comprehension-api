package jp.comprehension.api.jpcomprehensionapi.service.user;

import jp.comprehension.api.jpcomprehensionapi.domain.JpUser;
import jp.comprehension.api.jpcomprehensionapi.domain.UserWord;
import jp.comprehension.api.jpcomprehensionapi.domain.Word;
import jp.comprehension.api.jpcomprehensionapi.dto.StandaloneWord;

import java.util.List;
import java.util.Optional;

public interface UserWordService {
    List<UserWord> saveWords(String username, List<StandaloneWord> wordsToSave);

    Optional<UserWord> saveWord(Word word, JpUser user);
}
