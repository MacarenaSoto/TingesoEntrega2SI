package tingeso.carrepairservice.services;

import tingeso.carrepairservice.entities.RepairEntity;
import tingeso.carrepairservice.repositories.RepairRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;



@Service
public class RepairService {

    @Autowired
    RepairRepository repairRepository;

    //métodos de repository
    public ArrayList<RepairEntity> getRepairs(){
        return (ArrayList<RepairEntity>) repairRepository.findAll();
    }

    public RepairEntity getRepairById(Long id){
        return repairRepository.findById(id).get();
    }

    public ArrayList<RepairEntity> getRepairsByCarId(Long carId){
        return (ArrayList<RepairEntity>) repairRepository.findByCarId(carId);
    }

    public RepairEntity getRepairsByDetailId(Long detailId){
        return repairRepository.findByDetailId(detailId);
    }

    public List<RepairEntity> getRepairsByCarIdAndRealExitDateIsNull(Long carId){
        return  repairRepository.findByCarIdAndRealExitDateIsNull(carId);
    }

    //métodos 
    public RepairEntity saveRepair(RepairEntity repair){
        return repairRepository.save(repair);
    }

    public boolean deleteRepair(Long id) throws Exception{
        try{
            repairRepository.deleteById(id);
            return true;
        }catch (Exception e){
            throw new Exception("No se pudo eliminar la reparación con id: "+id);
        }
    }

    public RepairEntity updateRepair(RepairEntity repair){
        return repairRepository.save(repair);
    }

    //métodos de servicio / lógica de negocio

    //Método para guardar realExitDate y realExitHour en un repair ya creado:
    public void saveRealExitDateAndHour(Long id, Date realExitDate, LocalTime realExitHour){
        RepairEntity repair = getRepairById(id);
        repair.setRealExitDate(realExitDate);
        repair.setRealExitHour(realExitHour);
        updateRepair(repair);
    }


    //Método para setear el detailId en un repair ya creado:
    public void setDetailId(Long repairId, Long detailId){
        RepairEntity repair = getRepairById(repairId);
        repair.setDetailId(detailId);
        updateRepair(repair);
    }




    






}