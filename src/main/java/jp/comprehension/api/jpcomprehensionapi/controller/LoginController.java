package jp.comprehension.api.jpcomprehensionapi.controller;

import jakarta.validation.Valid;
import jp.comprehension.api.jpcomprehensionapi.domain.JpUser;
import jp.comprehension.api.jpcomprehensionapi.dto.user.CreateJpUser;
import jp.comprehension.api.jpcomprehensionapi.dto.user.UserCreated;
import jp.comprehension.api.jpcomprehensionapi.service.user.JpUserService;
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
    private final JpUserService userService;

    @PostMapping("/register")
    public UserCreated register(@RequestBody @Valid CreateJpUser newUser) {
        return userService.register(newUser);
    }
}
