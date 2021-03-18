package io.github.bogdansukonnov.bank.service;

import io.github.bogdansukonnov.bank.converter.UserConverter;
import io.github.bogdansukonnov.bank.dao.UserDao;
import io.github.bogdansukonnov.bank.dto.UserDto;
import io.github.bogdansukonnov.bank.model.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Log4j2
public class UserService {

    @NonNull
    private final UserDao userDao;
    @NonNull
    private final UserConverter userConverter;

    public List<UserDto> users() {
        log.debug("users()");
        List<User> userList = userDao.users();
        return userList.stream()
                .map(userConverter::convert)
                .collect(Collectors.toList());
    }
}
