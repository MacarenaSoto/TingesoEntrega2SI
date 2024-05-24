package tingeso.carrepairservice.services;

import tingeso.carrepairservice.entities.AppliedSurchargeEntity;
import tingeso.carrepairservice.repositories.AppliedSurchargeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class AppliedSurchargeService {

    @Autowired
    AppliedSurchargeRepository appliedSurchargeRepository;

    public AppliedSurchargeEntity saveAppliedSurcharge(AppliedSurchargeEntity appliedSurcharge){
        return appliedSurchargeRepository.save(appliedSurcharge);
    }

    public AppliedSurchargeEntity getAppliedSurchargeById(Long id){
        return appliedSurchargeRepository.findById(id).get();
    }

    public boolean deleteAppliedSurcharge(Long id) throws Exception{
        try{
            appliedSurchargeRepository.deleteById(id);
            return true;
        }catch (Exception e){
            throw new Exception("No se pudo eliminar el recargo aplicado con id: "+id);
        }
    }

    public AppliedSurchargeEntity updateAppliedSurcharge(AppliedSurchargeEntity appliedSurcharge){
        return appliedSurchargeRepository.save(appliedSurcharge);
    }

    //getAppliedSurcharges

    public List<AppliedSurchargeEntity> getAppliedSurcharges(){
        return appliedSurchargeRepository.findAll();
    }

}
