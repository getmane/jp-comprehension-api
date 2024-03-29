package jp.comprehension.api.jpcomprehensionapi.word.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("words")
public class Word {

    @Id
    private String id;

    private String spelling;
    private String reading;
    private List<String> meanings;
}
