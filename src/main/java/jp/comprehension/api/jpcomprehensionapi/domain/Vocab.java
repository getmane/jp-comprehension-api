package jp.comprehension.api.jpcomprehensionapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Document("vocabs")
public class Vocab {

    @Id
    private String id;

    private String spelling;
    private String reading;

    @DBRef(lazy = true)
    private List<User> users;
}
