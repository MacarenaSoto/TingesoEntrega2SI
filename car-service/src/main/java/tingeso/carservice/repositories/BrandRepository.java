package tingeso.carservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tingeso.carservice.entities.BrandEntity;
import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Long>{

    BrandEntity findByName(String name);
    List<BrandEntity> findAll();

}
