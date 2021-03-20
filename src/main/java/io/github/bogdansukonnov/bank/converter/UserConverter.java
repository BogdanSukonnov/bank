package io.github.bogdansukonnov.bank.converter;

import io.github.bogdansukonnov.bank.dto.UserDto;
import io.github.bogdansukonnov.bank.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserDto toDto(User userModel) {
        return UserDto.builder()
                .id(userModel.getId())
                .firstName(userModel.getFirstName())
                .lastName(userModel.getLastName())
                .build();
    }

    public User toModel(UserDto userDto, String newId) {
        return User.builder()
                .id(StringUtils.isEmpty(newId) ? userDto.getId() : newId)
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .build();
    }
}
