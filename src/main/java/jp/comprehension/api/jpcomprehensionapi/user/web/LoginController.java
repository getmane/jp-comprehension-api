package jp.comprehension.api.jpcomprehensionapi.user.web;

import jakarta.validation.Valid;
import jp.comprehension.api.jpcomprehensionapi.user.dto.CreateUser;
import jp.comprehension.api.jpcomprehensionapi.user.dto.UserCreated;
import jp.comprehension.api.jpcomprehensionapi.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginController {

    private final UserService userService;

    @PostMapping("/register")
    public UserCreated register(@RequestBody @Valid CreateUser newUser) {
        return userService.register(newUser);
    }
}
