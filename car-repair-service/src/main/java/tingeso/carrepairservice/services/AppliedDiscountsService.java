 package tingeso.carrepairservice.services;

import tingeso.carrepairservice.entities.AppliedDiscountsEntity;

import org.springframework.web.client.RestTemplate;


import tingeso.carrepairservice.repositories.AppliedDiscountsRepository;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppliedDiscountsService {

    @Autowired
    AppliedDiscountsRepository appliedDiscountsRepository;

    @Autowired
    RestTemplate restTemplate;

    public ArrayList<AppliedDiscountsEntity> getAppliedDiscounts(){
        return (ArrayList<AppliedDiscountsEntity>) appliedDiscountsRepository.findAll();
    }

    public AppliedDiscountsEntity getAppliedDiscountsById(Long id){
        return appliedDiscountsRepository.findById(id).get();
    }

    public ArrayList<AppliedDiscountsEntity> getAppliedDiscountsByCarId(Long carId){
        return (ArrayList<AppliedDiscountsEntity>) appliedDiscountsRepository.findByCarId(carId);
    }

    public ArrayList<AppliedDiscountsEntity> getAppliedDiscountsByRepairId(Long repairId){
        return (ArrayList<AppliedDiscountsEntity>) appliedDiscountsRepository.findByRepairId(repairId);
    }

    public AppliedDiscountsEntity saveAppliedDiscounts(AppliedDiscountsEntity appliedDiscounts){
        return appliedDiscountsRepository.save(appliedDiscounts);
    }

    public boolean deleteAppliedDiscounts(Long id) throws Exception{
        try{
            appliedDiscountsRepository.deleteById(id);
            return true;
        }catch (Exception e){
            throw new Exception("No se pudo eliminar el descuento aplicado con id: "+id);
        }
    }

    public AppliedDiscountsEntity updateAppliedDiscounts(AppliedDiscountsEntity appliedDiscounts){
        return appliedDiscountsRepository.save(appliedDiscounts);
    }





}

        







 