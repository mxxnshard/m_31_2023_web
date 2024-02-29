package web.service.converter;

import org.springframework.stereotype.Service;
import web.dao.model.UserModel;
import web.service.dto.UserDto;

import java.util.Collections;

@Service
public class UserDtoToModelConverter implements Converter<UserDto, UserModel> {
    @Override
    public UserModel convert(UserDto userDto) {
        return new UserModel(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getSecondName(),
                userDto.getEmail(),
                null,
                null,
                Collections.emptySet()
        );
    }
}
