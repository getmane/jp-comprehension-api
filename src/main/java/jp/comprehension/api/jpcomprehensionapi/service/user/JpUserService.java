package jp.comprehension.api.jpcomprehensionapi.service.user;

import com.mongodb.DuplicateKeyException;
import jp.comprehension.api.jpcomprehensionapi.domain.JpUser;
import jp.comprehension.api.jpcomprehensionapi.dto.user.CreateJpUser;
import jp.comprehension.api.jpcomprehensionapi.dto.user.UserCreated;
import jp.comprehension.api.jpcomprehensionapi.exception.JpUserCreateException;
import jp.comprehension.api.jpcomprehensionapi.map.JpUserMapper;
import jp.comprehension.api.jpcomprehensionapi.repository.JpUserRepository;
import lombok.RequiredArgsConstructor;
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
            createdUser = mapper.toJpUser(newUser, encoder);
        } catch (DuplicateKeyException ex) {
            throw new JpUserCreateException(ex.getResponse(), ex.getServerAddress(), ex.getWriteConcernResult());
        }
        return mapper.toJpUserCreated(userRepository.save(createdUser));
    }

    public JpUser getUserByUsername(String username) {
        return this.userRepository.findFirstByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("User not found username: " + username)
        );
    }
}
