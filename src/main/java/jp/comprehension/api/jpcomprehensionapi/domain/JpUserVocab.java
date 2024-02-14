package jp.comprehension.api.jpcomprehensionapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@Document("jp_users_vocabs")
public class JpUserVocab {

    @Id
    private String id;

    private String jpUserId;
    private String vocabId;

    private Long timesSeen;
}
