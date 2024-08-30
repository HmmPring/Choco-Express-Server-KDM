package dgu.choco_express.repository;

import dgu.choco_express.domain.box.Box;
import dgu.choco_express.domain.choco.Choco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChocoRepository extends JpaRepository<Choco, Long> {
    @Query("SELECT count(*) FROM Choco WHERE box = :boxId")
    Integer findChocoCountByBox(@Param("boxId") Box box);

    @Query(
            value = "SELECT * " +
                    "FROM choco c " +
                    "WHERE c.box_id = :boxId",
            countQuery = "SELECT COUNT(*) " +
                         "FROM choco c " +
                         "WHERE c.box_id = :boxId",
            nativeQuery = true
    )
    Page<Choco> findAllByBoxId(Long boxId, Pageable pageable);
}
