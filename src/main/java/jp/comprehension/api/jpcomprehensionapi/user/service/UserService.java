package jp.comprehension.api.jpcomprehensionapi.user.service;

import jp.comprehension.api.jpcomprehensionapi.user.domain.JpUser;
import jp.comprehension.api.jpcomprehensionapi.user.dto.CreateUser;
import jp.comprehension.api.jpcomprehensionapi.user.dto.UserCreated;
import jp.comprehension.api.jpcomprehensionapi.user.exception.UserCreateException;
import jp.comprehension.api.jpcomprehensionapi.user.mapper.UserMapper;
import jp.comprehension.api.jpcomprehensionapi.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper mapper;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserCreated register(CreateUser newUser) {
        JpUser createdUser;
        try {
            createdUser = userRepository.save(mapper.toJpUser(newUser, encoder));
        } catch (DuplicateKeyException ex) {
            throw new UserCreateException(ex.getMessage());
        }
        return mapper.toJpUserCreated(createdUser);
    }

    public JpUser getUserByUsername(String username) {
        return this.userRepository.findFirstByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("User not found username: " + username)
        );
    }
}
