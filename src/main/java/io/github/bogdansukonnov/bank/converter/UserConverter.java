package io.github.bogdansukonnov.bank.converter;

import io.github.bogdansukonnov.bank.dto.UserDto;
import io.github.bogdansukonnov.bank.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserDto convert(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }
}
