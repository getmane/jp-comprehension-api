package jp.comprehension.api.jpcomprehensionapi.service;

import jp.comprehension.api.jpcomprehensionapi.domain.Word;
import jp.comprehension.api.jpcomprehensionapi.dto.StandaloneWord;
import jp.comprehension.api.jpcomprehensionapi.map.WordMapper;
import jp.comprehension.api.jpcomprehensionapi.repository.WordRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WordServiceTest {

    @Mock
    private WordRepository wordRepository;

    @InjectMocks
    private WordService wordService;

    @Spy
    private WordMapper wordMapper = Mappers.getMapper(WordMapper.class);

    @Test
    void testSaveExistingWord() {
        // Given
        StandaloneWord wordToSave = new StandaloneWord();
        wordToSave.setSpelling("test_read");
        wordToSave.setReading("test_spel");

        Word existingWord = new Word("id", wordToSave.getSpelling(), wordToSave.getReading(), List.of());

        when(this.wordRepository.findFirstBySpellingAndReading(
                wordToSave.getSpelling(),
                wordToSave.getReading()
        )).thenReturn(Optional.of(existingWord));

        // When
        Word actualSavedWord = wordService.saveWord(wordToSave);

        // Then
        assertWordSaved(actualSavedWord, wordToSave);
    }

    @Test
    void testSaveNewWord() {
        // GIVEN
        StandaloneWord wordToSave = new StandaloneWord();
        wordToSave.setSpelling("test_read");
        wordToSave.setReading("test_spel");

        Word expectedSavedWord = new Word("id", wordToSave.getSpelling(), wordToSave.getReading(), List.of());

        when(this.wordRepository.findFirstBySpellingAndReading(
                wordToSave.getSpelling(),
                wordToSave.getReading()
        )).thenReturn(Optional.empty());

        when(this.wordRepository.save(any(Word.class)))
                .thenReturn(expectedSavedWord);

        // WHEN
        Word actualSavedWord = wordService.saveWord(wordToSave);

        // THEN
        assertWordSaved(actualSavedWord, wordToSave);
    }

    private static void assertWordSaved(Word actualSavedWord, StandaloneWord wordToSave) {
        assertThat(actualSavedWord.getId()).isNotNull();
        assertThat(actualSavedWord.getSpelling()).isEqualTo(wordToSave.getSpelling());
        assertThat(actualSavedWord.getReading()).isEqualTo(wordToSave.getReading());
    }
}
