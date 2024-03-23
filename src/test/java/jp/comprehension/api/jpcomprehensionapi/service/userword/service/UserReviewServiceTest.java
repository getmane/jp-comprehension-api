package jp.comprehension.api.jpcomprehensionapi.service.userword.service;

import jp.comprehension.api.jpcomprehensionapi.user.domain.JpUser;
import jp.comprehension.api.jpcomprehensionapi.userword.domain.UserWord;
import jp.comprehension.api.jpcomprehensionapi.userword.service.UserReviewService;
import jp.comprehension.api.jpcomprehensionapi.word.domain.Word;
import jp.comprehension.api.jpcomprehensionapi.word.dto.StandaloneWord;
import jp.comprehension.api.jpcomprehensionapi.word.mapper.WordMapper;
import jp.comprehension.api.jpcomprehensionapi.userword.repository.UserWordRepository;
import jp.comprehension.api.jpcomprehensionapi.word.service.WordService;
import jp.comprehension.api.jpcomprehensionapi.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class UserReviewServiceTest {

    private static final JpUser TEST_USER = new JpUser("1", "test", "test", "test");

    @Mock
    private UserWordRepository userWordRepository;

    @Mock
    private WordService wordService;

    @Mock
    private UserService userService;

    @Spy
    private WordMapper wordMapper;

    private UserReviewService reviewService;

    @BeforeEach
    void setUp() {
        reviewService = new UserReviewService(wordService, userWordRepository, userService);
    }

    @Test
    void testSaveReviewWord_SameWordExists_ReviewWordExist() {
        // Given
        when(userService.getUserByUsername(TEST_USER.getUsername())).thenReturn(TEST_USER);

        StandaloneWord newReviewWordToSave = new StandaloneWord("test_spel", "test_read");

        Word existingWord = new Word(
                "1", newReviewWordToSave.getSpelling(), newReviewWordToSave.getReading(), List.of()
        );
        UserWord existingReviewWord = new UserWord("1", TEST_USER.getId(), existingWord.getId(), 0L);

        when(wordService.saveWords(List.of(newReviewWordToSave))).thenReturn(List.of(existingWord));

        when(userWordRepository.findFirstByWordIdAndJpUserId(existingWord.getId(), TEST_USER.getId()))
                .thenReturn(Optional.of(existingReviewWord));

        // When
        List<UserWord> resultWords = reviewService.saveWords(
                TEST_USER.getUsername(), List.of(newReviewWordToSave)
        );

        // Then
        assertThat(resultWords).isEmpty();
    }

    @Test
    void testSaveReviewWord_SameWordExists_ReviewWordAbsent() {
        // Given
        when(userService.getUserByUsername(TEST_USER.getUsername())).thenReturn(TEST_USER);

        StandaloneWord newReviewWordToSave = new StandaloneWord("test_spel", "test_read");

        Word existingWord = new Word(
                "1", newReviewWordToSave.getSpelling(), newReviewWordToSave.getReading(), List.of()
        );
        UserWord expectedNewSavedReviewWord = new UserWord("1", TEST_USER.getId(), existingWord.getId(), 0L);

        when(wordService.saveWords(List.of(newReviewWordToSave))).thenReturn(List.of(existingWord));

        when(userWordRepository.findFirstByWordIdAndJpUserId(existingWord.getId(), TEST_USER.getId()))
                .thenReturn(Optional.empty());

        when(userWordRepository.save(UserWord.builder()
                .wordId(existingWord.getId())
                .jpUserId(TEST_USER.getId())
                .timesSeen(0L)
                .build())
        ).thenReturn(expectedNewSavedReviewWord);

        // When
        List<UserWord> resultWords = reviewService.saveWords(
                TEST_USER.getUsername(), List.of(newReviewWordToSave)
        );

        // Then
        assertThat(resultWords).containsExactlyInAnyOrderElementsOf(List.of(expectedNewSavedReviewWord));
    }
}
