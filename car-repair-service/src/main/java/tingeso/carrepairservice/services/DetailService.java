package tingeso.carrepairservice.services;

import tingeso.carrepairservice.entities.DetailEntity;
import tingeso.carrepairservice.repositories.DetailRepository;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailService {

    @Autowired
    DetailRepository detailRepository;

    public DetailEntity getDetailById(Long id){
        return detailRepository.findById(id).get();
    }

    public DetailEntity saveDetail(DetailEntity detail){
        return detailRepository.save(detail);
    }

    public boolean deleteDetail(Long id) throws Exception{
        try{
            detailRepository.deleteById(id);
            return true;
        }catch (Exception e){
            throw new Exception("No se pudo eliminar el detalle con id: "+id);
        }
    }

    public DetailEntity updateDetail(DetailEntity detail){
        return detailRepository.save(detail);
    }

    // getDetailsByCarId
    public List<DetailEntity> getDetailsByCarId(Long carId){
        List<DetailEntity> details = detailRepository.findByCarId(carId);
        return details;
    }





}
