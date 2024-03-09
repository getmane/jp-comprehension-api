package jp.comprehension.api.jpcomprehensionapi.service.reviewimport.jpdb;

import jp.comprehension.api.jpcomprehensionapi.domain.UserWord;
import jp.comprehension.api.jpcomprehensionapi.dto.reviewimport.jpdb.JpdbImportStat;
import jp.comprehension.api.jpcomprehensionapi.map.WordMapper;
import jp.comprehension.api.jpcomprehensionapi.service.user.UserReviewService;
import jp.comprehension.api.jpcomprehensionapi.vocab.jpdb.JpdbWord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class JpdbImportServiceTest {

    private static final String USERNAME = "user";

    @Mock
    private UserReviewService jpUserWordService;

    @Spy
    private WordMapper wordMapper;

    @InjectMocks
    private JpdbImportService importService;

    @Test
    void testImportJpdbReviews() {
        // Given
        JpdbWord reviewWord = new JpdbWord("気分", "きぶん");
        MockMultipartFile jpdb = new MockMultipartFile("test", "", "application/json",
                """
                  {
                    "cards_vocabulary_jp_en": [
                      {
                         "spelling": "%s",
                         "reading": "%s"
                      }
                    ]
                  }
                """.formatted(reviewWord.reading(), reviewWord.spelling()).getBytes()
        );

        when(jpUserWordService.saveWords(USERNAME, wordMapper.jpdbToStandalone(List.of(reviewWord))))
                .thenReturn(List.of(new UserWord()));

        // When
        JpdbImportStat resultStat = importService.importJpdbReviews(USERNAME, jpdb);

        // Then
        assertThat(resultStat.getFilename()).isEqualTo(jpdb.getName());
        assertThat(resultStat.getWordsAdded()).isEqualTo(1);
    }
}