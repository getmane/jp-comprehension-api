package jp.comprehension.api.jpcomprehensionapi.map;

import jp.comprehension.api.jpcomprehensionapi.domain.Word;
import jp.comprehension.api.jpcomprehensionapi.dto.StandaloneWord;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WordMapper {

    Word toWord(StandaloneWord standalone);
}
