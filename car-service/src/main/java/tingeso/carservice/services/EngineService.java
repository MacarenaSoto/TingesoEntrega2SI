package tingeso.carservice.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import tingeso.carservice.entities.EngineEntity;
import tingeso.carservice.repositories.EngineRepository;


import java.util.ArrayList;

@Service

public class EngineService {
    @Autowired 
    EngineRepository engineRepository;

    public ArrayList<EngineEntity> getEngines(){
        return (ArrayList<EngineEntity>) engineRepository.findAll();
    }

    public EngineEntity getEngineById(Long id){
        return engineRepository.findById(id).get();
    }

    public EngineEntity getEngineByName(String name){
        return engineRepository.findByName(name);
    }

    public EngineEntity saveEngine(EngineEntity engineEntity){
        return engineRepository.save(engineEntity);
    }

    public EngineEntity updateEngine(EngineEntity engineEntity){
        return engineRepository.save(engineEntity);
    }

    public boolean deleteEngine(Long id) throws Exception{
        try{
            engineRepository.deleteById(id);
            return true;
        }catch (Exception e){
            throw new Exception("Error al eliminar el motor");
        }
    }
}

