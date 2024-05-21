package tingeso.carservice.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import tingeso.carservice.entities.TypeEntity;
import tingeso.carservice.repositories.TypeRepository;


import java.util.ArrayList;

@Service

public class TypeService {

    @Autowired
    TypeRepository typeRepository;

    public ArrayList<TypeEntity> getTypes(){
        return (ArrayList<TypeEntity>) typeRepository.findAll();
    }

    public TypeEntity getTypeById(Long id){
        return typeRepository.findById(id).get();
    }

    public TypeEntity getTypeByName(String name){
        return typeRepository.findByName(name);
    }

    public TypeEntity saveType(TypeEntity typeEntity){
        return typeRepository.save(typeEntity);
    }

    public TypeEntity updateType(TypeEntity typeEntity){
        return typeRepository.save(typeEntity);
    }

    public boolean deleteType(Long id) throws Exception{
        try{
            typeRepository.deleteById(id);
            return true;
        }catch (Exception e){
            throw new Exception("Error al eliminar el tipo");
        }
    }
}
