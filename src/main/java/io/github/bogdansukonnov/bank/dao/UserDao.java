package io.github.bogdansukonnov.bank.dao;

import io.github.bogdansukonnov.bank.dto.UserDto;
import io.github.bogdansukonnov.bank.model.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
@Log4j2
public class UserDao {

    public List<User> users() {
        log.debug("users()");
        return Collections.emptyList();
    }
}
