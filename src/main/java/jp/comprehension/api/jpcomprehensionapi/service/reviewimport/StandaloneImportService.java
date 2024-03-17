package jp.comprehension.api.jpcomprehensionapi.service.reviewimport;

import jp.comprehension.api.jpcomprehensionapi.dto.StandaloneWord;
import jp.comprehension.api.jpcomprehensionapi.dto.reviewimport.jpdb.ImportStat;
import jp.comprehension.api.jpcomprehensionapi.service.user.UserReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StandaloneImportService {

    private static final String STANDALONE_IMPORT_DESCRIPTION = "STANDALONE";

    private final UserReviewService userReviewService;

    public ImportStat importReviews(String username, List<StandaloneWord> reviewWords) {
        return new ImportStat(
                STANDALONE_IMPORT_DESCRIPTION,
                userReviewService.saveWords(username, reviewWords).size()
        );
    }
}