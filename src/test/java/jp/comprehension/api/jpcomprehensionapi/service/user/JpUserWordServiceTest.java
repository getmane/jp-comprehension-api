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
import org.junit.jupiter.params.provider.Arguments;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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
    void testSaveReviewWord_SameWordExists_ReviewWordExist() {
        // Given
        when(jpUserService.getUserByUsername(TEST_USER.getUsername())).thenReturn(TEST_USER);

        StandaloneWord newReviewWordToSave = new StandaloneWord("test_spel", "test_read");

        Word existingWord = new Word(
                "1", newReviewWordToSave.getSpelling(), newReviewWordToSave.getReading(), List.of()
        );
        JpUserWord existingReviewWord = new JpUserWord("1", TEST_USER.getId(), existingWord.getId(), 0L);

        when(wordService.saveWords(List.of(newReviewWordToSave))).thenReturn(List.of(existingWord));

        when(jpUserWordRepository.findFirstByWordIdAndJpUserId(existingWord.getId(), TEST_USER.getId()))
                .thenReturn(Optional.of(existingReviewWord));

        // When
        List<JpUserWord> resultWords = jpUserWordService.saveReviewWords(
                TEST_USER.getUsername(), List.of(newReviewWordToSave)
        );

        // Then
        assertThat(resultWords).isEmpty();
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

    private static Stream<Arguments> postTestSource() {
        return Stream.of(
                Arguments.of(Named.of("New review word",
                                List.of(new StandaloneWord("test_spel", "test_read"))),
                        List.of(new JpUserWord("1", TEST_USER.getId(), "1", 0L))
                )
        );
    }
}
