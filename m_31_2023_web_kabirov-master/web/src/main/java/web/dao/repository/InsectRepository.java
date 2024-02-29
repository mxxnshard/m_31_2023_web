package web.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.dao.model.InsectModel;

import java.util.List;

public interface InsectRepository extends JpaRepository<InsectModel, String> {
    List<InsectModel> findAllByUserId(String userId);
}
