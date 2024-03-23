package jp.comprehension.api.jpcomprehensionapi.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document("users")
public class JpUser {

    @Id
    private String id;

    @Indexed(unique = true)
    private String username;

    private String password;
    private String email;
}
