package tingeso.carservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import tingeso.carservice.entities.EngineEntity;

@Repository
public interface EngineRepository extends JpaRepository<EngineEntity, Long>{
    public EngineEntity findByName(String name);
    List<EngineEntity> findAll();

}
