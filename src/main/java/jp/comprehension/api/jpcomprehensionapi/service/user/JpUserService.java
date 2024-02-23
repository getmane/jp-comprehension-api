package jp.comprehension.api.jpcomprehensionapi.service.user;

import jp.comprehension.api.jpcomprehensionapi.domain.JpUser;
import jp.comprehension.api.jpcomprehensionapi.dto.user.CreateJpUser;
import jp.comprehension.api.jpcomprehensionapi.repository.JpUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class JpUserService {

    private final JpUserRepository userRepository;
    private final PasswordEncoder encoder;

    public JpUser register(CreateJpUser newUser) {
        JpUser user = JpUser.builder()
                .username(newUser.getUsername())
                .email(newUser.getEmail())
                .password(encoder.encode(newUser.getPassword()))
                .build();
        return userRepository.save(user);
    }

    public JpUser getUserByUsername(String username) {
        return this.userRepository.findFirstByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("User not found username: " + username)
        );
    }
}
