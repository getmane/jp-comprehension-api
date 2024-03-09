package jp.comprehension.api.jpcomprehensionapi.map;

import jp.comprehension.api.jpcomprehensionapi.domain.JpUser;
import jp.comprehension.api.jpcomprehensionapi.dto.user.CreateUser;
import jp.comprehension.api.jpcomprehensionapi.dto.user.UserCreated;
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
