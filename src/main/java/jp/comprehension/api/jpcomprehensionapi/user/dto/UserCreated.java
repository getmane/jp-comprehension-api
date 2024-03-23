package jp.comprehension.api.jpcomprehensionapi.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserCreated {

    @NotBlank
    private String username;

    @Email
    private String email;
}
