package jp.comprehension.api.jpcomprehensionapi.controller;

import jakarta.validation.Valid;
import jp.comprehension.api.jpcomprehensionapi.domain.JpUser;
import jp.comprehension.api.jpcomprehensionapi.dto.CreateJpUser;
import jp.comprehension.api.jpcomprehensionapi.service.JpUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class LoginController {
    private final JpUserService userService;

    @PostMapping("/register")
    public JpUser register(@RequestBody @Valid CreateJpUser newUser) {
        return userService.register(newUser);
    }
}
