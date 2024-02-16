package jp.comprehension.api.jpcomprehensionapi.service;

import jp.comprehension.api.jpcomprehensionapi.domain.JpUser;
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
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class JpUserLoader implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JpUserLoader.class);
    private final JpUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("Loading user by username: {}", username);

        JpUser user = userRepository.findFirstByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Username not found: " + username)
        );
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("USER"));
        return new User(user.getUsername(), user.getPassword(),  authorities);
    }
}
