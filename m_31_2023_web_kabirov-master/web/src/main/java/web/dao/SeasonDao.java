package web.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.model.SeasonModel;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SeasonDao {
    @PersistenceContext
    EntityManager em;

    @Transactional
    public void insertSeason(SeasonModel model) {
        em.persist(model);
    }

    public List<SeasonModel> getSeasons() {
        return em.createQuery("select s from SeasonModel s", SeasonModel.class)
                .getResultList();
    }

    public Optional<SeasonModel> getSeasonById(String id) {
        return Optional.ofNullable(em.find(SeasonModel.class, id));
    }
}
