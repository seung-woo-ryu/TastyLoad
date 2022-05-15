package blockchainstudy.example.tastyload.user;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @EntityGraph(attributePaths = "roles")
    Optional<User> findOneWithRolesByUserId(String userId);

    Optional<User> findByUserId(String userId);
}



