package jp.comprehension.api.jpcomprehensionapi.controller;

import jp.comprehension.api.jpcomprehensionapi.domain.JpUserWord;
import jp.comprehension.api.jpcomprehensionapi.service.JpUserWordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class JpUserController {
    private final JpUserWordService service;

    @RequestMapping("/{id}/words")
    public List<JpUserWord> getUserWords(@PathVariable("id") String userId) {
        return this.service.getUserWords(userId);
    }
}
