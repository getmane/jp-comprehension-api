package jp.comprehension.api.jpcomprehensionapi.controller;

import jp.comprehension.api.jpcomprehensionapi.dto.StandaloneWord;
import jp.comprehension.api.jpcomprehensionapi.dto.reviewimport.jpdb.ImportStat;
import jp.comprehension.api.jpcomprehensionapi.service.reviewimport.JpdbImportService;
import jp.comprehension.api.jpcomprehensionapi.service.reviewimport.StandaloneImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/review", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserReviewController {

    private final JpdbImportService jpdbImportService;
    private final StandaloneImportService standaloneImportService;

    @PostMapping("/jpdb-import")
    public ImportStat importJpdbReviews(
            @AuthenticationPrincipal String username,
            MultipartFile jpdbExportFile
    ) {
        return this.jpdbImportService.importReviews(username, jpdbExportFile);
    }

    @PostMapping("/standalone-import")
    public ImportStat importStandaloneReviews(
            @AuthenticationPrincipal String username,
            List<StandaloneWord> reviewWords
    ) {
        return this.standaloneImportService.importReviews(username, reviewWords);
    }
}
