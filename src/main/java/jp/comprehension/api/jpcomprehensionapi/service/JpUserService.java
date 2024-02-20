package jp.comprehension.api.jpcomprehensionapi.service;

import jp.comprehension.api.jpcomprehensionapi.domain.JpUser;
import jp.comprehension.api.jpcomprehensionapi.dto.CreateJpUser;
import jp.comprehension.api.jpcomprehensionapi.repository.JpUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class JpUserService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JpUserService.class);
    private final JpUserRepository userRepository;
    private final PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("Loading user by username: {}", username);

        JpUser user = userRepository.findFirstByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Username not found: " + username)
        );
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("USER"));
        return new User(user.getUsername(), user.getPassword(),  authorities);
    }

    public JpUser register(CreateJpUser newUser) {
        JpUser user = JpUser.builder()
                .username(newUser.getUsername())
                .email(newUser.getEmail())
                .password(encoder.encode(newUser.getPassword()))
                .build();
        return userRepository.save(user);
    }

    public JpUser getUserById(String userId) {
        return this.userRepository.findFirstById(userId).orElseThrow(
                () -> new IllegalArgumentException("User not found id: " + userId)
        );
    }
}
