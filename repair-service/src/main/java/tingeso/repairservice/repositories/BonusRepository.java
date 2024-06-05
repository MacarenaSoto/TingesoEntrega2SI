package tingeso.repairservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tingeso.repairservice.entities.BonusEntity;

import java.util.List;


@Repository
public interface BonusRepository extends JpaRepository<BonusEntity, Long>{

    List<BonusEntity> findAll();

}
