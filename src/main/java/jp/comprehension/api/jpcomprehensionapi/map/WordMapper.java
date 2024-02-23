package jp.comprehension.api.jpcomprehensionapi.map;

import jp.comprehension.api.jpcomprehensionapi.domain.Word;
import jp.comprehension.api.jpcomprehensionapi.dto.StandaloneWord;
import jp.comprehension.api.jpcomprehensionapi.vocab.jpdb.JpdbWord;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WordMapper {

    Word toWord(StandaloneWord standalone);

    StandaloneWord jpdbToStandalone(JpdbWord jpdbWord);

    List<StandaloneWord> jpdbToStandalone(List<JpdbWord> jpdbWords);
}
