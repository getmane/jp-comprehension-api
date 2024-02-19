package jp.comprehension.api.jpcomprehensionapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Builder
@AllArgsConstructor
@Document("users")
public class JpUser {

    @Id
    private String id;

    private String username;
    private String password;
    private String email;
}
