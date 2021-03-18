package io.github.bogdansukonnov.bank;

import io.github.bogdansukonnov.bank.dao.UserDao;
import io.github.bogdansukonnov.bank.dto.UserDto;
import io.github.bogdansukonnov.bank.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class FunctionalTest {

    private static final String USER_1_NAME = "John";
    private static final String USER_2_NAME = "Laura";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDao userDaoMock;

    @Test
    public void usersShouldReturnUsersFromService() throws Exception {

        when(userDaoMock.users()).thenReturn(List.of(
                User.builder().firstName(USER_1_NAME).build(),
                User.builder().firstName(USER_2_NAME).build()));

        this.mockMvc.perform(get("/users")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(USER_1_NAME)))
                .andExpect(content().string(containsString(USER_2_NAME)));
    }
}
