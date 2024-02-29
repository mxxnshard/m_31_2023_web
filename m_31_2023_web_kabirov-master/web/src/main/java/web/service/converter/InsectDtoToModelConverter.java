package web.service.converter;

import org.springframework.stereotype.Service;
import web.dao.model.InsectModel;
import web.service.dto.InsectDto;

@Service
public class InsectDtoToModelConverter implements Converter<InsectDto, InsectModel> {
    @Override
    public InsectModel convert(InsectDto insectDto) {
        return new InsectModel(
                null,
                insectDto.getUserId(),
                null,
                null,
                insectDto.getName(),
                insectDto.getSize(),
                null
        );
    }
}
