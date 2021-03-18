package io.github.bogdansukonnov.bank.controller;

import io.github.bogdansukonnov.bank.dto.UserDto;
import io.github.bogdansukonnov.bank.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Log4j2
@RequestMapping("api")
public class UserController {

    @NonNull
    private final UserService userService;

    @GetMapping("users")
    public List<UserDto> users() {
        log.debug("users()");
        return userService.users();
    }
}
