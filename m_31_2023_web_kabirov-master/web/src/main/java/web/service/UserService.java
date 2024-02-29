package web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.dao.model.UserModel;
import web.service.converter.UserDtoToModelConverter;
import web.service.converter.UserModelToDtoConverter;
import web.service.dto.UserDto;
import web.service.exception.CustomException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final UserDtoToModelConverter converterDto2Model;
    private final UserModelToDtoConverter converterModel2Dto;

    @Transactional
    public boolean register(UserDto userDto, String pass) {
        Optional<UserModel> fromDb = userDao.getUserByEmail(userDto.getEmail());
        if (fromDb.isPresent()) {
            return false;
        }

        String hash = passwordEncoder.encode(pass);
        UserModel userModel = converterDto2Model.convert(userDto);
        userModel.setPass(hash);
        userModel.setId(UUID.randomUUID().toString());

        return userDao.insertUser(userModel);
    }

    public UserDto login(String email, String pass) {
        Optional<UserModel> userModel = userDao.getUserByEmail(email);
        UserModel user = userModel.orElseThrow(() -> new CustomException("No user found"));

        if (!passwordEncoder.matches(pass, user.getPass())) {
            throw new CustomException("pass mismatch");
        }
        return converterModel2Dto.convert(user);
    }

    public List<UserDto> getUsersByFirsName(String name) {
        return userDao.getUserByName(name).stream()
                .map(converterModel2Dto::convert)
                .collect(Collectors.toList());
    }

    public Optional<UserDto> getUserById(String userId) {
        return userDao.getUserById(userId)
                .map(converterModel2Dto::convert);
    }
}
