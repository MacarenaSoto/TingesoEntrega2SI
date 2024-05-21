package tingeso.carservice.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import tingeso.carservice.entities.CarEntity;
import tingeso.carservice.repositories.CarRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {
    @Autowired
    CarRepository carRepository;

    public ArrayList<CarEntity> getCars(){
        return (ArrayList<CarEntity>) carRepository.findAll();
    }

    public CarEntity getCarById(Long id){
        return carRepository.findById(id).get();
    }

    public CarEntity getCarByPatent(String patent){
        return carRepository.findByPatent(patent);
    }

    public List<CarEntity> getCarsByBrandId(Long brandId){
        return carRepository.findByBrandId(brandId);
    }

    public CarEntity saveCar(CarEntity carEntity){
        return carRepository.save(carEntity);
    }

    public CarEntity updateCar(CarEntity carEntity){
        return carRepository.save(carEntity);
    }

    public boolean deleteCar(Long id) throws Exception{
        try{
            carRepository.deleteById(id);
            return true;
        }catch (Exception e){
            throw new Exception("Error al eliminar el auto");
        }
    }


}
