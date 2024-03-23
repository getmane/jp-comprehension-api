package jp.comprehension.api.jpcomprehensionapi.service.reviewimport;

import jp.comprehension.api.jpcomprehensionapi.domain.UserWord;
import jp.comprehension.api.jpcomprehensionapi.dto.reviewimport.jpdb.ImportStat;
import jp.comprehension.api.jpcomprehensionapi.map.WordMapper;
import jp.comprehension.api.jpcomprehensionapi.service.user.UserReviewService;
import jp.comprehension.api.jpcomprehensionapi.vocab.jpdb.JpdbWord;
import org.junit.jupiter.api.BeforeEach;
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

    private JpdbImportService importService;

    @BeforeEach
    void setUp() {
        importService = new JpdbImportService(jpUserWordService, wordMapper);
    }

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
        ImportStat resultStat = importService.importReviews(USERNAME, jpdb);

        // Then
        assertThat(resultStat.getDescription()).isEqualTo(jpdb.getName());
        assertThat(resultStat.getWordsAdded()).isEqualTo(1);
    }
}
