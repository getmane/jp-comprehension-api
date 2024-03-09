package jp.comprehension.api.jpcomprehensionapi.controller;

import jp.comprehension.api.jpcomprehensionapi.domain.UserWord;
import jp.comprehension.api.jpcomprehensionapi.dto.StandaloneWord;
import jp.comprehension.api.jpcomprehensionapi.service.user.UserImmersionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/users/{username}/immersion", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserImmersionController {
    private final UserImmersionService userWordService;

    @GetMapping("/words")
    public List<UserWord> getUserWords(@AuthenticationPrincipal String username) {
        return this.userWordService.getAllUserWords(username);
    }

    @PostMapping("/save")
    public List<UserWord> saveImmersionWordsProgress(
            @AuthenticationPrincipal String username,
            @RequestBody List<StandaloneWord> words
    ) {
        return this.userWordService.saveWords(username, words);
    }
}
