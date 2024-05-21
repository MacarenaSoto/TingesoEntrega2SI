package tingeso.carservice.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import tingeso.carservice.entities.BrandEntity;
import tingeso.carservice.repositories.BrandRepository;

import java.util.ArrayList;

@Service
public class BrandService {
    @Autowired
    BrandRepository brandRepository;

    public ArrayList<BrandEntity> getBrands(){
        return (ArrayList<BrandEntity>) brandRepository.findAll();
    }

    public BrandEntity getBrandById(Long id){
        return brandRepository.findById(id).get();
    }

    public BrandEntity getBrandByName(String name){
        return brandRepository.findByName(name);
    }

    public BrandEntity saveBrand(BrandEntity brandEntity){
        return brandRepository.save(brandEntity);
    }

    public BrandEntity updateBrand(BrandEntity brandEntity){
        return brandRepository.save(brandEntity);
    }

    public boolean deleteBrand(Long id) throws Exception{
        try{
            brandRepository.deleteById(id);
            return true;
        }catch (Exception e){
            throw new Exception("Error al eliminar la marca");
        }
    }

}
