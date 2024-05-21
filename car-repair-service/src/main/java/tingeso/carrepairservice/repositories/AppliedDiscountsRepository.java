package tingeso.carrepairservice.repositories;

import tingeso.carrepairservice.entities.AppliedDiscountsEntity;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppliedDiscountsRepository extends JpaRepository<AppliedDiscountsEntity, Long>{

    AppliedDiscountsEntity findAppliedDiscountsById(Long id);

    List<AppliedDiscountsEntity> findByCarId(Long carId);
    List<AppliedDiscountsEntity> findByRepairId(Long repairId);
    
    List<AppliedDiscountsEntity> findAll();


}
