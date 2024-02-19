package jp.comprehension.api.jpcomprehensionapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CreateJpUser {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @Email
    private String email;
}
