package tingeso.repairservice.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import tingeso.repairservice.entities.RepairEntity;
import tingeso.repairservice.repositories.RepairRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class RepairService {
    @Autowired
    RepairRepository repairRepository;

    public List<RepairEntity> getRepairs(){
        return repairRepository.findAll();
    }

    public RepairEntity getRepairById(Long id){
        return repairRepository.findById(id).get();
    }

    public List<RepairEntity> getRepairsByEngineId(Long engineId){
        return repairRepository.findByEngineId(engineId);
    }

    public RepairEntity saveRepair(RepairEntity repairEntity){
        return repairRepository.save(repairEntity);
    }

    public RepairEntity updateRepair(RepairEntity repairEntity){
        return repairRepository.save(repairEntity);
    }

    public boolean deleteRepair(Long id) throws Exception{
        try{
            repairRepository.deleteById(id);
            return true;
        }catch (Exception e){
            throw new Exception("Error al eliminar la reparación");
        }
    }

    //obtiene los nombres de las reparaciones
    public ArrayList<String> getRepairsNames(){
        System.out.println("Entró a getRepairsNames");
        ArrayList<String> names = new ArrayList<>();
        for (RepairEntity repair : repairRepository.findAll()){
            names.add(repair.getName());
        }
        return names;
    }

    //elimina los nombres repetidos de las reparaciones
    public ArrayList<String> getRepairsNamesNoRepeat(){
        System.out.println("Entró a getRepairsNamesNoRepeat");
        ArrayList<String> names = new ArrayList<>();
        for (RepairEntity repair : repairRepository.findAll()){
            if (!names.contains(repair.getName())){
                names.add(repair.getName());
            }
        }
        return names;
    }

    

}
