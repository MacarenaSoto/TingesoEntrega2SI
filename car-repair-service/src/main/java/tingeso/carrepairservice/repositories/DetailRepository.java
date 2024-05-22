package tingeso.carrepairservice.repositories;

import tingeso.carrepairservice.entities.DetailEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Date;

@Repository
public interface DetailRepository extends JpaRepository<DetailEntity, Long>{

    List<DetailEntity> findByCarId(Long carId);
    List<DetailEntity> findAll();
    DetailEntity findByAdmissionDate(Date admissionDate);
    

}
