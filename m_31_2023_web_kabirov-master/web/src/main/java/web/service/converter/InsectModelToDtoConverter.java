package web.service.converter;

import org.springframework.stereotype.Service;
import web.dao.model.InsectModel;
import web.service.dto.InsectDto;

@Service
public class InsectModelToDtoConverter implements Converter<InsectModel, InsectDto> {
    @Override
    public InsectDto convert(InsectModel insectModel) {
        return new InsectDto(
                insectModel.getUserId(),
                insectModel.getName(),
                insectModel.getSize());
    }
}
