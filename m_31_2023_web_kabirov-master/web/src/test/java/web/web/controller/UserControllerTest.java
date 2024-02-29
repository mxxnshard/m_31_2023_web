package web.web.controller;

import org.hibernate.annotations.Immutable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import web.MockSecurityConfig;
import web.security.SecurityConfig;
import web.service.UserService;
import web.service.converter.UserRegFormToDto;
import web.service.dto.UserDto;
import web.web.form.RegForm;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import({SecurityConfig.class, MockSecurityConfig.class})
@WebMvcTest(UserController.class)
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    UserService userService;

    @MockBean
    UserRegFormToDto converter;

    @BeforeEach
    void setUp() {

    }

    @WithUserDetails(value = "email", userDetailsServiceBeanName = "getUserDetailService")

    @Test
    void indexGet_redirectedToLogin() throws Exception {
        mvc.perform(get("/web/index"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/web/login"));
    }

    @WithUserDetails(value = "email", userDetailsServiceBeanName = "getUserDetailService")
    @Test
    void indexGet_success() throws Exception {
        when(userService.getUserById("1")).thenReturn(Optional.of(
                new UserDto("1", "A", "B", "a@aa.aa")
        ));

        mvc.perform(get("/web/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));

        verify(userService, times(1)).getUserById("1");
    }

    @Test
    void registerGet() throws Exception {
        mvc.perform(get("/web/register"))
                .andExpect(model().attribute("reg_form", new RegForm()))
                .andExpect(status().isOk())
                .andExpect(view().name("reg"));
    }

    @Test
    void registerPost_success() throws Exception {
        RegForm regForm = new RegForm("A", "B", "a@aa.aa", "12345678");
        UserDto userDto = new UserDto("1", "A", "B", "a@aa.aa");
        when(converter.convert(regForm)).thenReturn(userDto);
        when(userService.register(userDto, regForm.getPassword())).thenReturn(true);
        when(userService.login(regForm.getEmail(), regForm.getPassword())).thenReturn(userDto);
        mvc.perform(post("/web/register")
                        .param("name", regForm.getName())
                        .param("secondName", regForm.getSecondName())
                        .param("email", regForm.getEmail())
                        .param("password", regForm.getPassword()))

                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/web/index"));
    }

    @Test
    void registerPost_formValidationFails() throws Exception {
        RegForm regForm = new RegForm("A", "B", "asda", "12345678");
        UserDto userDto = new UserDto("1", "A", "B", "a@aa.aa");
        when(converter.convert(regForm)).thenReturn(userDto);
        when(userService.register(userDto, regForm.getPassword())).thenReturn(true);
        when(userService.login(regForm.getEmail(), regForm.getPassword())).thenReturn(userDto);
        mvc.perform(post("/web/register")
                        .param("name", regForm.getName())
                        .param("secondName", regForm.getSecondName())
                        .param("email", regForm.getEmail())
                        .param("password", regForm.getPassword()))

                .andExpect(model().hasErrors());
    }

    @Test
    void loginGet() throws Exception {
    }

    @Test
    void loginPost() throws Exception {
    }

    @Test
    void logoutGet() throws Exception {
    }
}