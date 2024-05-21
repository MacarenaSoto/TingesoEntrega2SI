package tingeso.repairservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tingeso.repairservice.entities.RepairEntity;

import java.util.List;

@Repository
public interface RepairRepository extends JpaRepository<RepairEntity, Long>{

    List<RepairEntity> findByName(String name);
    RepairEntity findById(long id);
    List<RepairEntity> findByEngineId(long engineId);

}
