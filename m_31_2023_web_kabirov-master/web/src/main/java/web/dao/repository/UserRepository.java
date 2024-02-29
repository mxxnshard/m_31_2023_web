package web.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.dao.model.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, String> {
    List<UserModel> findAllByFirstName(String name);

    List<UserModel> findAllByEmail(String email);

}
