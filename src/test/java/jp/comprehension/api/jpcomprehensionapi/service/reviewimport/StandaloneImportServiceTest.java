package jp.comprehension.api.jpcomprehensionapi.service.reviewimport;

import jp.comprehension.api.jpcomprehensionapi.domain.UserWord;
import jp.comprehension.api.jpcomprehensionapi.dto.StandaloneWord;
import jp.comprehension.api.jpcomprehensionapi.dto.reviewimport.jpdb.ImportStat;
import jp.comprehension.api.jpcomprehensionapi.service.user.UserReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class StandaloneImportServiceTest {

    private static final String USERNAME = "user";

    @Mock
    private UserReviewService jpUserWordService;

    private StandaloneImportService importService;

    @BeforeEach
    void setUp() {
        importService = new StandaloneImportService(jpUserWordService);
    }

    @Test
    void testStandaloneImportReviews() {
        // Given
        StandaloneWord reviewWord = new StandaloneWord("気分", "きぶん");

        when(jpUserWordService.saveWords(USERNAME, List.of(reviewWord)))
                .thenReturn(List.of(new UserWord()));

        // When
        ImportStat resultStat = importService.importReviews(USERNAME, List.of(reviewWord));

        // Then
        assertThat(resultStat.getDescription()).isEqualTo("STANDALONE");
        assertThat(resultStat.getWordsAdded()).isEqualTo(1);
    }
}
