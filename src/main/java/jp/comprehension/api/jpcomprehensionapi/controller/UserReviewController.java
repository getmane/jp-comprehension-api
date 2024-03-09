package jp.comprehension.api.jpcomprehensionapi.controller;

import jp.comprehension.api.jpcomprehensionapi.dto.reviewimport.jpdb.JpdbImportStat;
import jp.comprehension.api.jpcomprehensionapi.service.reviewimport.jpdb.JpdbImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/users/{username}/review", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserReviewController {
    private final JpdbImportService jpdbImportService;

    @PostMapping("/jpdb-import")
    public JpdbImportStat importJpdbReviews(
            @AuthenticationPrincipal String username,
            MultipartFile jpdbExportFile
    ) {
        return this.jpdbImportService.importJpdbReviews(username, jpdbExportFile);
    }
}
