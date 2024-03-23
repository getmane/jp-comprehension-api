package jp.comprehension.api.jpcomprehensionapi.word.mapper;

import jp.comprehension.api.jpcomprehensionapi.word.domain.Word;
import jp.comprehension.api.jpcomprehensionapi.word.dto.StandaloneWord;
import jp.comprehension.api.jpcomprehensionapi.userword.srs.jpdb.JpdbWord;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WordMapper {

    Word toWord(StandaloneWord standalone);

    StandaloneWord jpdbToStandalone(JpdbWord jpdbWord);

    List<StandaloneWord> jpdbToStandalone(List<JpdbWord> jpdbWords);
}
