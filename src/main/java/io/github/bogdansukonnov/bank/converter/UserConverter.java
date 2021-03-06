package io.github.bogdansukonnov.bank.converter;

import io.github.bogdansukonnov.bank.dto.NewUserDto;
import io.github.bogdansukonnov.bank.dto.UserDto;
import io.github.bogdansukonnov.bank.model.User;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserConverter {

    public UserDto toDto(User userModel) {
        return UserDto.builder()
                .id(userModel.getId())
                .firstName(userModel.getFirstName())
                .lastName(userModel.getLastName())
                .build();
    }

    public User toModel(NewUserDto userDto, UUID newId) {
        return User.builder()
                .id(newId)
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .build();
    }
}
