package jp.comprehension.api.jpcomprehensionapi.service.user;

import jp.comprehension.api.jpcomprehensionapi.domain.JpUser;
import jp.comprehension.api.jpcomprehensionapi.domain.JpUserWord;
import jp.comprehension.api.jpcomprehensionapi.domain.Word;
import jp.comprehension.api.jpcomprehensionapi.dto.StandaloneWord;
import jp.comprehension.api.jpcomprehensionapi.map.WordMapper;
import jp.comprehension.api.jpcomprehensionapi.repository.JpUserWordRepository;
import jp.comprehension.api.jpcomprehensionapi.service.WordService;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class JpUserWordServiceTest {

    private static final JpUser TEST_USER = new JpUser("1", "test", "test", "test");
    private static final Random RANDOM = new Random();

    @Mock
    private JpUserWordRepository jpUserWordRepository;

    @Mock
    private WordService wordService;

    @Mock
    private JpUserService jpUserService;

    @Spy
    private WordMapper wordMapper;

    @InjectMocks
    private JpUserWordService jpUserWordService;

    @Test
    void testGetAllUserWords() {
        // Given
        when(jpUserService.getUserByUsername(TEST_USER.getUsername())).thenReturn(TEST_USER);

        List<JpUserWord> expectedWords = List.of(new JpUserWord("1", "1", "1", 1L));
        when(jpUserWordRepository.findAllByJpUserId(TEST_USER.getId())).thenReturn(expectedWords);

        // When
        List<JpUserWord> resultWords = jpUserWordService.getAllUserWords(TEST_USER.getUsername());

        // Then
        assertThat(resultWords).containsExactlyInAnyOrderElementsOf(expectedWords);
    }

    @Test
    void testSaveReviewWord_SameWordExists_ReviewWordAbsent() {
        // Given
        when(jpUserService.getUserByUsername(TEST_USER.getUsername())).thenReturn(TEST_USER);

        StandaloneWord newReviewWordToSave = new StandaloneWord("test_spel", "test_read");

        Word existingWord = new Word(
                "1", newReviewWordToSave.getSpelling(), newReviewWordToSave.getReading(), List.of()
        );
        JpUserWord expectedNewSavedReviewWord = new JpUserWord("1", TEST_USER.getId(), existingWord.getId(), 0L);

        when(wordService.saveWords(List.of(newReviewWordToSave))).thenReturn(List.of(existingWord));

        when(jpUserWordRepository.findFirstByWordIdAndJpUserId(existingWord.getId(), TEST_USER.getId()))
                .thenReturn(Optional.empty());

        when(jpUserWordRepository.save(JpUserWord.builder()
                .wordId(existingWord.getId())
                .jpUserId(TEST_USER.getId())
                .timesSeen(0L)
                .build())
        ).thenReturn(expectedNewSavedReviewWord);

        // When
        List<JpUserWord> resultWords = jpUserWordService.saveReviewWords(
                TEST_USER.getUsername(), List.of(newReviewWordToSave)
        );

        // Then
        assertThat(resultWords).containsExactlyInAnyOrderElementsOf(List.of(expectedNewSavedReviewWord));
    }
}
