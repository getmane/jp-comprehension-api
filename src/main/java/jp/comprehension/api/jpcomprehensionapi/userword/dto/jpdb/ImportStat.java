package jp.comprehension.api.jpcomprehensionapi.userword.dto.jpdb;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImportStat {

    private String description;
    private Integer wordsAdded;
}
