package web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.InsectDao;
import web.dao.model.InsectModel;
import web.service.converter.InsectDtoToModelConverter;
import web.service.converter.InsectModelToDtoConverter;
import web.service.dto.InsectDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InsectService {
    private final InsectDao insectDao;
    private final InsectDtoToModelConverter converter;
    private final InsectModelToDtoConverter insectModelToDtoConverter;

    @Transactional
    public String insertInsect(InsectDto insectDto) {
        return insectDao.insertInsect(converter.convert(insectDto));
    }

    public List<InsectDto> getUsersInsects(String userId) {
        return insectDao.getInsectsByUserId(userId).stream()
                .map(insectModelToDtoConverter::convert)
                .collect(Collectors.toList());
    }


}
