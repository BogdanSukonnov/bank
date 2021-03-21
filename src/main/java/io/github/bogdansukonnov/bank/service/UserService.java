package io.github.bogdansukonnov.bank.service;

import io.github.bogdansukonnov.bank.converter.UserConverter;
import io.github.bogdansukonnov.bank.dto.NewUserDto;
import io.github.bogdansukonnov.bank.dto.UserDto;
import io.github.bogdansukonnov.bank.model.User;
import io.github.bogdansukonnov.bank.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Log4j2
public class UserService {
    @NonNull
    private final UserRepository userRepository;
    @NonNull
    private final UserConverter userConverter;

    public List<UserDto> users() {
        log.debug("users()");
        List<User> userList = userRepository.findAll();
        return userList.stream()
                .map(userConverter::toDto)
                .collect(Collectors.toList());
    }

    public UserDto user(UUID id) {
        log.debug("user({})", id);
        User user = getUserByIdOrThrow(id, "");
        return userConverter.toDto(user);
    }

    public UserDto addUser(NewUserDto userNoIdDto) {
        log.debug("addUser({})", userNoIdDto);
        User user = userConverter.toModel(userNoIdDto, UUID.randomUUID());
        user = userRepository.save(user);
        log.debug("newUser({})", user);
        return userConverter.toDto(user);
    }

    public void deleteUser(UUID id) {
        log.debug("deleteUser({})", id);
        User user = getUserByIdOrThrow(id, "to delete");
        userRepository.delete(user);
    }

    public User getUserByIdOrThrow(UUID id, String operation) {
        log.debug("getUserByIdOrThrow({}, {})", id, operation);
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            String errorMsg = String.format("Can't find user %s with id - %s", operation, id);
            log.error(errorMsg);
            throw new RuntimeException(errorMsg);
        }
        User user = optionalUser.get();
        log.debug("{}", user);
        return user;
    }
}
