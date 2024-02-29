package web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import web.dao.SeasonDao;
import web.dao.model.SeasonModel;
import web.service.exception.CustomException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeasonService {
    private final SeasonDao seasonDao;

    public void insertSeason(String season) {
        SeasonModel model = new SeasonModel(
                UUID.randomUUID().toString(),
                season, null
        );
        seasonDao.insertSeason(model);
    }

    public String getSeason(String id) {
        return seasonDao.getSeasonById(id)
                .map(s -> s.getName())
                .orElseThrow(() -> new CustomException("No season found"));
    }

    public List<String> getSeasons() {
        return seasonDao.getSeasons().stream()
                .map(s -> s.getName())
                .collect(Collectors.toList());
    }
}
