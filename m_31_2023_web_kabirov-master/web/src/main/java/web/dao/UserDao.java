package web.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.model.UserModel;
import web.dao.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDao {
    private final UserRepository userRepo;
    @PersistenceContext
    EntityManager em;

    @Transactional
    public boolean insertUser(UserModel user) {
        em.persist(user);
        return true;
    }

    public Optional<UserModel> getUserByEmail(String email) {
        TypedQuery<UserModel> query = em.createQuery(
                "select u from UserModel as u where email = :email", UserModel.class);
        query.setParameter("email", email);
        return query.getResultList().stream().findAny();
    }

    public List<UserModel> getUserByName(String name) {
        return userRepo.findAllByFirstName(name);
    }

    public Optional<UserModel> getUserById(String userId) {
        return userRepo.findById(userId);
    }
}
