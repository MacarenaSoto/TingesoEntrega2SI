package tingeso.carservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tingeso.carservice.entities.CarEntity;
import java.util.Optional;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long>{

    Optional<CarEntity> findById(Long id);
    public CarEntity findByPatent(String patent);
    List<CarEntity> findByBrandId(Long brandId);

}
