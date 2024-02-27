package jp.comprehension.api.jpcomprehensionapi.service.user;

import jp.comprehension.api.jpcomprehensionapi.domain.JpUser;
import jp.comprehension.api.jpcomprehensionapi.dto.user.CreateJpUser;
import jp.comprehension.api.jpcomprehensionapi.dto.user.UserCreated;
import jp.comprehension.api.jpcomprehensionapi.exception.JpUserCreateException;
import jp.comprehension.api.jpcomprehensionapi.map.JpUserMapper;
import jp.comprehension.api.jpcomprehensionapi.repository.JpUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpUserService {

    private final JpUserMapper mapper;
    private final JpUserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserCreated register(CreateJpUser newUser) {
        JpUser createdUser;
        try {
            createdUser = userRepository.save(mapper.toJpUser(newUser, encoder));
        } catch (DuplicateKeyException ex) {
            throw new JpUserCreateException(ex.getMessage());
        }
        return mapper.toJpUserCreated(createdUser);
    }

    public JpUser getUserByUsername(String username) {
        return this.userRepository.findFirstByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("User not found username: " + username)
        );
    }
}
