package jp.comprehension.api.jpcomprehensionapi.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateJpUser {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @Email
    private String email;
}
