package io.github.bogdansukonnov.bank.service;

import io.github.bogdansukonnov.bank.converter.UserConverter;
import io.github.bogdansukonnov.bank.dto.UserDto;
import io.github.bogdansukonnov.bank.dto.UserNoIdDto;
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

    public UserDto user(String id) {
        log.debug("user({})", id);
        Optional<User> optionalUser = userRepository.findById(id);
        return userConverter.toDto(optionalUser.orElseThrow());
    }

    public UserDto addUser(UserNoIdDto userNoIdDto) {
        log.debug("addUser({})", userNoIdDto);
        User user = userConverter.toModel(userNoIdDto, UUID.randomUUID().toString());
        user = userRepository.save(user);
        log.debug("newUser({})", user);
        return userConverter.toDto(user);
    }

    public void deleteUser(String id) {
        log.debug("deleteUser({})", id);
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            String errorMsg = String.format("Can't find user to delete with id - %s", id);
            log.error(errorMsg);
            throw new RuntimeException(errorMsg);
        }
        userRepository.delete(optionalUser.get());
    }
}
