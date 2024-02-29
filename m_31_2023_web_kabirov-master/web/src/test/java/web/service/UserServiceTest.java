package web.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import web.dao.UserDao;
import web.dao.model.UserModel;
import web.service.converter.UserDtoToModelConverter;
import web.service.converter.UserModelToDtoConverter;
import web.service.dto.UserDto;
import web.service.exception.CustomException;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    UserService subj;
    @Mock
    UserDao userDao;
    @Mock
    UserDtoToModelConverter converterDto2Model = new UserDtoToModelConverter();
    @Mock
    UserModelToDtoConverter converterModel2Dto = new UserModelToDtoConverter();
    @Mock
    PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {

    }

    @Test
    void register_success() {
        String pass = "pass";
        String hash = "hash";
        String email = "email";
        String uuid = UUID.randomUUID().toString();

        when(passwordEncoder.encode(pass)).thenReturn(hash);

        UserDto userDto = new UserDto(uuid, "name", "second", email);
        UserModel userModel = new UserModel(uuid, "name", "second", email, null, null, null);

        when(converterDto2Model.convert(userDto)).thenReturn(userModel);
        when(userDao.insertUser(userModel)).thenReturn(true);
        when(userDao.getUserByEmail(email)).thenReturn(Optional.empty());

        boolean b = subj.register(userDto, pass);

        assertTrue(b);

        verify(passwordEncoder, times(1)).encode(pass);
        verify(converterDto2Model, times(1)).convert(userDto);
        verify(userDao, times(1)).insertUser(userModel);
        verify(userDao, times(1)).getUserByEmail(email);
    }

    @Test
    void register_fail() {
        String pass = "pass";
        String hash = "hash";
        String email = "email";
        String uuid = UUID.randomUUID().toString();

        UserDto userDto = new UserDto(uuid, "name", "second", email);
        UserModel userModel = new UserModel(uuid, "name", "second", email, null, null, null);

        when(userDao.getUserByEmail(email)).thenReturn(Optional.of(userModel));

        boolean b = subj.register(userDto, pass);

        assertFalse(b);

        verifyNoInteractions(passwordEncoder);
        verifyNoInteractions(converterDto2Model);
    }

    @Test
    void login_success() {
        String pass = "pass";
        String mail = "mail";
        String hash = "hash";
        final String uuid = UUID.randomUUID().toString();
        UserModel userModel = new UserModel(uuid, "name", "second", "mail", hash, null, null);
        UserDto userDto = new UserDto(uuid, "name", "second", "mail");

        when(userDao.getUserByEmail(mail)).thenReturn(Optional.of(userModel));
        when(passwordEncoder.matches(pass, hash)).thenReturn(true);
        when(converterModel2Dto.convert(userModel)).thenReturn(userDto);

        final UserDto user = subj.login(mail, pass);

        assertEquals(userDto, user);

        verify(userDao, times(1)).getUserByEmail(mail);
        verify(passwordEncoder, times(1)).matches(pass, hash);
        verify(converterModel2Dto, times(1)).convert(userModel);
    }

    @Test
    void login_NoUserFound() {
        String pass = "pass";
        String mail = "mail";

        when(userDao.getUserByEmail(mail)).thenReturn(Optional.empty());

        //   assertThrows(CustomException.class, () -> subj.login(mail, pass));

        boolean b = false;
        try {
            subj.login(mail, pass);
        } catch (CustomException e) {
            assertEquals("No user found", e.getMessage());
            b = true;
        }
        assertTrue(b);

        verify(userDao, times(1)).getUserByEmail(mail);
    }

    @Test
    void login_passMismatch() {
        String pass = "pass";
        String mail = "mail";
        String hash = "hash";
        String incorrectHash = "qwerty";
        final String uuid = UUID.randomUUID().toString();
        UserModel userModel = new UserModel(uuid, "name", "second", "mail", incorrectHash, null, null);

        when(userDao.getUserByEmail(mail)).thenReturn(Optional.of(userModel));
        when(passwordEncoder.matches(pass, incorrectHash)).thenReturn(false);

        assertThrows(CustomException.class, () -> subj.login(mail, pass));

        verify(userDao, times(1)).getUserByEmail(mail);
        verify(passwordEncoder, times(1)).matches(pass, incorrectHash);
        verifyNoInteractions(converterModel2Dto);
    }
}