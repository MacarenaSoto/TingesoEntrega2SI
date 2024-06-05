package tingeso.reports_service.repositories;

import tingeso.reports_service.entities.Report1Entity;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Report1Repository extends JpaRepository<Report1Entity, Long>{


    Report1Entity findReport1ById(Long id);

    Report1Entity findByCarType(String carType);
    Report1Entity findByRepairId(Long repairId);
}
