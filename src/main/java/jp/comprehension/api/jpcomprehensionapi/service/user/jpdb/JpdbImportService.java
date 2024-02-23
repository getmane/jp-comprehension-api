package jp.comprehension.api.jpcomprehensionapi.service.user.jpdb;

import com.google.gson.Gson;
import jp.comprehension.api.jpcomprehensionapi.dto.user.jpdb.JpdbImportStat;
import jp.comprehension.api.jpcomprehensionapi.map.WordMapper;
import jp.comprehension.api.jpcomprehensionapi.service.user.JpUserWordService;
import jp.comprehension.api.jpcomprehensionapi.vocab.jpdb.Jpdb;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class JpdbImportService {

    private static final Gson GSON = new Gson();

    private final JpUserWordService userWordService;
    private final WordMapper wordMapper;

    public JpdbImportStat importJpdbReviews(String username, MultipartFile file) {
        try {
            Jpdb jpdb = GSON.fromJson(new String(file.getBytes()), Jpdb.class);
            return new JpdbImportStat(
                    userWordService.saveKnownWords(
                            username,
                            wordMapper.jpdbToStandalone(jpdb.getCardsVocabularyJpEn())
                    ).size()
            );
        } catch (IOException e) {
            throw new IllegalArgumentException("Jpdb review file couldn't be saved");
        }
    }
}
