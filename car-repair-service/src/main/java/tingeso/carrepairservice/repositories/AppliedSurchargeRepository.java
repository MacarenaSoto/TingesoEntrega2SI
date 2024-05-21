package tingeso.carrepairservice.repositories;

import tingeso.carrepairservice.entities.AppliedSurchargeEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppliedSurchargeRepository extends JpaRepository<AppliedSurchargeEntity, Long>{

    AppliedSurchargeEntity findAppliedSurchargeById(Long id);

    AppliedSurchargeEntity findByCarId(Long carId);
    AppliedSurchargeEntity findByRepairId(Long repairId);
    
    AppliedSurchargeEntity findBySurchargeName(String surchargeName);

}
