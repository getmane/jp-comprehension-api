package jp.comprehension.api.jpcomprehensionapi.controller;

import jp.comprehension.api.jpcomprehensionapi.domain.JpUserWord;
import jp.comprehension.api.jpcomprehensionapi.dto.StandaloneWord;
import jp.comprehension.api.jpcomprehensionapi.dto.user.jpdb.JpdbImportStat;
import jp.comprehension.api.jpcomprehensionapi.service.user.JpUserWordService;
import jp.comprehension.api.jpcomprehensionapi.service.user.jpdb.JpdbImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class JpUserController {
    private final JpUserWordService userWordService;
    private final JpdbImportService jpdbImportService;

    @GetMapping("/{id}/words")
    public List<JpUserWord> getUserWords(@PathVariable("id") String userId) {
        return this.userWordService.getUserWords(userId);
    }

    @PostMapping("/{id}/immersion-words")
    public List<JpUserWord> saveImmersionWordsProgress(
            @AuthenticationPrincipal String username,
            @RequestBody List<StandaloneWord> words
    ) {
        return this.userWordService.saveImmersionWordsProgress(username, words);
    }

    @PostMapping("/{id}/jpdb-import")
    public JpdbImportStat importJpdbReviews(
            @AuthenticationPrincipal String username,
            MultipartFile jpdbExportFile
    ) {
        return this.jpdbImportService.importJpdbReviews(username, jpdbExportFile);
    }
}
