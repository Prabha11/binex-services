package pdn.ce.outlierhandler.modules.coremodule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdn.ce.outlierhandler.modules.coremodule.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
