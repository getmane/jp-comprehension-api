package jp.comprehension.api.jpcomprehensionapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@Document("vocabs")
public class Vocab {

    @Id
    private String id;

    private String spelling;
    private String reading;
}
