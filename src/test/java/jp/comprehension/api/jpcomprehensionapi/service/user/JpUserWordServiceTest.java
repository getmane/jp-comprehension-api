package jp.comprehension.api.jpcomprehensionapi.service.user;

import jp.comprehension.api.jpcomprehensionapi.domain.JpUser;
import jp.comprehension.api.jpcomprehensionapi.domain.JpUserWord;
import jp.comprehension.api.jpcomprehensionapi.repository.JpUserWordRepository;
import jp.comprehension.api.jpcomprehensionapi.service.WordService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class JpUserWordServiceTest {

    private static final JpUser TEST_USER = new JpUser("1", "test", "test", "test");

    @Mock
    private JpUserWordRepository jpUserWordRepository;

    @Mock
    private WordService wordService;

    @Mock
    private JpUserService jpUserService;

    @InjectMocks
    private JpUserWordService jpUserWordService;

    @Test
    void testGetAllUserWords() {
        // Given
        when(jpUserService.getUserByUsername(TEST_USER.getUsername())).thenReturn(TEST_USER);

        List<JpUserWord> expectedWords = List.of(new JpUserWord("1", "1", "1", 1L));
        when(jpUserWordRepository.findAllByJpUserId(TEST_USER.getId())).thenReturn(expectedWords);

        // When
        List<JpUserWord> actualWords = jpUserWordService.getAllUserWords(TEST_USER.getUsername());

        // Then
        assertThat(actualWords).containsExactlyInAnyOrderElementsOf(expectedWords);
    }
}
