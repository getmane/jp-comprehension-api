package jp.comprehension.api.jpcomprehensionapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Document("users")
public class User {

    @Id
    private String id;

    private String username;
    private String email;

    @DBRef(lazy = true)
    private List<Vocab> vocabs;
}
