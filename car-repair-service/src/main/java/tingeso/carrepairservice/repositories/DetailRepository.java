package tingeso.carrepairservice.repositories;

import tingeso.carrepairservice.entities.DetailEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Date;

@Repository
public interface DetailRepository extends JpaRepository<DetailEntity, Long>{

    List<DetailEntity> findByCarId(Long carId);
    List<DetailEntity> findAll();
    DetailEntity findByAdmissionDate(Date admissionDate);

    /* // Para obtener los detalles que aún no tienen un monto total de un auto en específico
    @Query("SELECT d FROM DetailEntity d WHERE d.totalAmount IS NULL AND d.carId = :carId")
    DetailEntity findByCarIdAndTotalAmountIsNull(Long carId); */

    @Query("SELECT d FROM DetailEntity d WHERE (d.totalAmount IS NULL OR d.totalAmount = 0) AND d.carId = :carId")
    DetailEntity findByCarIdAndTotalAmountIsNull(Long carId);






}
