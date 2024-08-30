package dgu.choco_express.repository;

import dgu.choco_express.domain.Box.Box;
import dgu.choco_express.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoxRepository extends JpaRepository<Box, Long> {
    Optional<Box> findByIdAndUser(Long boxId, User user);
    Optional<Box> findByUser(User user);
}
