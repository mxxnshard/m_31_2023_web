package web.service.converter;

import org.springframework.stereotype.Service;
import web.service.dto.UserDto;
import web.web.form.RegForm;

@Service
public class UserRegFormToDto implements Converter<RegForm, UserDto> {
    @Override
    public UserDto convert(RegForm regForm) {
        return new UserDto(
                regForm.getName(),
                regForm.getSecondName(),
                regForm.getEmail());
    }
}
