package dgu.choco_express.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import dgu.choco_express.domain.box.Box;
import dgu.choco_express.domain.user.User;
import java.util.Optional;

public interface BoxRepository extends JpaRepository<Box, Long> {
    Optional<Box> findByIdAndUserId(Long boxId, Long userId);
    Optional<Box> findByUser(User user);
}
