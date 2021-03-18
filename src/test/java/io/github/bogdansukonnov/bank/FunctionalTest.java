package io.github.bogdansukonnov.bank;

import io.github.bogdansukonnov.bank.model.User;
import io.github.bogdansukonnov.bank.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FunctionalTest {

    private User user1;
    private User user2;

    public FunctionalTest() {
        fillUsers();
    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepositoryMock;

    @Test
    public void usersShouldReturnUsersFromService() throws Exception {

        when(userRepositoryMock.findAll()).thenReturn(List.of(user1, user2));

        this.mockMvc.perform(get("/api/users")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(user1.getId())))
                .andExpect(content().string(containsString(user1.getFirstName())))
                .andExpect(content().string(containsString(user1.getLastName())))
                .andExpect(content().string(containsString(user2.getId())))
                .andExpect(content().string(containsString(user2.getFirstName())))
                .andExpect(content().string(containsString(user2.getLastName())));
    }

    @Test
    public void userShouldReturnUserFromService() throws Exception {

        when(userRepositoryMock.findById(user1.getId())).thenReturn(Optional.of(user1));

        this.mockMvc.perform(get("/api/users/" + user1.getId())).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(user1.getId())))
                .andExpect(content().string(containsString(user1.getFirstName())))
                .andExpect(content().string(containsString(user1.getLastName())));
    }


    private void fillUsers() {
        this.user1 = User.builder()
                .id("idUser1")
                .firstName("Firstnameuser1")
                .lastName("Lastnameuser1")
                .build();
        this.user2 = User.builder()
                .id("idUser2")
                .firstName("Firstnameuser2")
                .lastName("Lastnameuser2")
                .build();
    }
}
