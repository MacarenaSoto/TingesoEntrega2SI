package tingeso.carrepairservice.repositories;

import tingeso.carrepairservice.entities.RepairEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

@Repository
public interface RepairRepository extends JpaRepository<RepairEntity, Long>{

    List<RepairEntity> findByCarId(Long carId);
    RepairEntity findByDetailId(Long detailId);

    // Para obtener las reparaciones más recientes, a través de las que aún tienen
    // la fecha real de salida vacía por id
    @Query("SELECT r FROM RepairEntity r WHERE r.realExitDate IS NULL AND r.carId = :carId")
    RepairEntity findByCarIdAndRealExitDateIsNull(Long carId);
}
