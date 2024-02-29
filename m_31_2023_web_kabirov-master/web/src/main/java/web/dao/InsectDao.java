package web.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.model.InsectModel;
import web.dao.model.UserModel;
import web.dao.repository.InsectRepository;
import web.service.TimeService;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InsectDao {
    private final DataSource dataSource;
    private final TimeService timeService;
    private final InsectRepository repo;

    @PersistenceContext
    EntityManager em;

    @Transactional
    public String insertInsect(InsectModel model) {
        String id = UUID.randomUUID().toString();
        model.setId(id);
        model.setCreatedAt(timeService.getNow());
        em.persist(model);
        return id;
    }

    public Optional<InsectModel> findById(String id) {
        return Optional.ofNullable(em.find(InsectModel.class, id));
    }

    public List<InsectModel> getInsectsByUser(UserModel user) {
        TypedQuery<InsectModel> query =
                em.createQuery("select h from InsectModel h where user = :user", InsectModel.class);
        query.setParameter("user", user);
        return query.getResultList();
    }

    public List<InsectModel> getInsectsByUserId(String userId) {
        return repo.findAllByUserId(userId);
    }
}
