package jp.comprehension.api.jpcomprehensionapi.user.mapper;

import jp.comprehension.api.jpcomprehensionapi.user.domain.JpUser;
import jp.comprehension.api.jpcomprehensionapi.user.dto.CreateUser;
import jp.comprehension.api.jpcomprehensionapi.user.dto.UserCreated;
import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserCreated toJpUserCreated(JpUser jpUser);

    JpUser toJpUser(CreateUser createUser, @Context PasswordEncoder encoder);

    @AfterMapping
    default void toJpUser(CreateUser createUser, @MappingTarget JpUser jpUser, @Context PasswordEncoder encoder) {
        jpUser.setPassword(encoder.encode(jpUser.getPassword()));
    }
}
