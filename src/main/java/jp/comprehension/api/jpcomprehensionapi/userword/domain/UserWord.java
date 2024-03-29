package jp.comprehension.api.jpcomprehensionapi.userword.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Document("jp_users_words")
public class UserWord {

    @Id
    private String id;

    private String jpUserId;
    private String wordId;

    private Long timesSeen;
}
