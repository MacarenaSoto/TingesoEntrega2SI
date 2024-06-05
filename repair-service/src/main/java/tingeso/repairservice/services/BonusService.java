package tingeso.repairservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tingeso.repairservice.entities.BonusEntity;
import tingeso.repairservice.repositories.BonusRepository;

import java.util.ArrayList;

@Service
public class BonusService {

    @Autowired
    BonusRepository bonusRepository;

    public ArrayList<BonusEntity> getBonuses(){
        return (ArrayList<BonusEntity>) bonusRepository.findAll();
    }


    public BonusEntity saveBonus(BonusEntity bonus){
        return bonusRepository.save(bonus);
    }

    public BonusEntity getBonusById(Long id){
        return bonusRepository.findById(id).get();
    }

    public boolean deleteBonus(Long id) throws Exception{
        try{
            bonusRepository.deleteById(id);
            return true;
        }catch (Exception e){
            throw new Exception("No se pudo eliminar el bono con id: "+id);
        }
    }

    public BonusEntity updateBonus(BonusEntity bonus){
        return bonusRepository.save(bonus);
    }

}
