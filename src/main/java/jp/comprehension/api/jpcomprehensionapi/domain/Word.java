package jp.comprehension.api.jpcomprehensionapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Document("words")
public class Word {

    @Id
    private String id;

    private String spelling;
    private String reading;
    private String meaning;
}
