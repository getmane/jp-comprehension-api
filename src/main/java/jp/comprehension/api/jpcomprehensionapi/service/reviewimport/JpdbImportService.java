package jp.comprehension.api.jpcomprehensionapi.service.reviewimport;

import com.google.gson.Gson;
import jp.comprehension.api.jpcomprehensionapi.dto.reviewimport.jpdb.ImportStat;
import jp.comprehension.api.jpcomprehensionapi.map.WordMapper;
import jp.comprehension.api.jpcomprehensionapi.service.user.UserReviewService;
import jp.comprehension.api.jpcomprehensionapi.vocab.jpdb.Jpdb;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class JpdbImportService implements ImportService {

    private static final Gson GSON = new Gson();

    private final UserReviewService userReviewService;
    private final WordMapper wordMapper;

    @Override
    public ImportStat importReviews(String username, MultipartFile file) {
        try {
            Jpdb jpdb = GSON.fromJson(new String(file.getBytes()), Jpdb.class);
            return new ImportStat(
                    file.getName(),
                    userReviewService.saveWords(
                            username,
                            wordMapper.jpdbToStandalone(jpdb.getCardsVocabularyJpEn())
                    ).size()
            );
        } catch (IOException e) {
            throw new IllegalArgumentException("Jpdb review file couldn't be saved");
        }
    }
}
