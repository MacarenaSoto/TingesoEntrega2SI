 package tingeso.carrepairservice.services;

import tingeso.carrepairservice.entities.AppliedDiscountsEntity;
import tingeso.carrepairservice.entities.DetailEntity;
//import tingeso.carrepairservice.entities.RepairEntity;

import tingeso.carrepairservice.model.Car;

import tingeso.carrepairservice.repositories.AppliedDiscountsRepository;
//import tingeso.carrepairservice.repositories.DetailRepository;
//import tingeso.carrepairservice.repositories.RepairRepository;

import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tingeso.carrepairservice.model.Repair;

@Service
public class AppliedDiscountsService {

    @Autowired
    AppliedDiscountsRepository appliedDiscountsRepository;

    @Autowired
    DetailService detailService;

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

    //Obtiene las reparaciones del MS2
    public List<Repair> getRepairs(){
        List<Repair> repairs = restTemplate.getForObject("http://repair-service/api/v2/repairs/all" , List.class);
        return repairs;
    }

    //Obtiene los autos del MS1
    public List<Car> getCars(){
        List<Car> cars = restTemplate.getForObject("http://car-service/api/v2/cars/all" , List.class);
        return cars;
    }

    //Obtiene un car del MS1 por id
    public Car getCarById(Long carId){
        Car car = restTemplate.getForObject("http://car-service/api/v2/cars/{id}" , Car.class, carId);
        return car;
    }

    /* DE ESTA FORMA LO TIENE EL PROFE, La otra me la hizo cop
    
    //Obtiene un car del MS1 por id
    public Car getCarById(Long carId){
        Car car = restTemplate.getForObject("http://car-service/api/v2/cars/" +  carId, Car.class);
        return car;
    } */


     // Método que obtiene el monto total de las reparaciones base
    public double getInitialAmount(Long carId){
        double total = 0;
        //busco en detalle, los que tengan = carId y totalAmount != null
        DetailEntity detail = detailService.getDetailsByCarIdAndTotalAmountNotNull(carId);
        //obtengo el repairIds
        List<Long> repairIds = detail.getRepairIds();
        // busco en repairs las que tengan id = repairIds
        List<Repair> repairs = getRepairs();
        for (Repair repair : repairs){
            for (Long repairId : repairIds){
                if (repair.getId().equals(repairId)){
                    total += repair.getAmount();
                }
            }
        }
        return total;

    }

    //Método que calcula el descuento por cantidad de reparaciones que ha tenido el auto
    public double getDiscountByNumberRepairs(Long carId){
        double discountByNumberRepairs = 0;
        double initialAmount = getInitialAmount(carId);
        //obtener el engineId del auto de getCarById
        Car car = getCarById(carId);
        Long engineId = car.getEngineId();
        //buscar en detalle, los que tengan = carId
        List<DetailEntity> details = detailService.getDetailsByCarId(carId);
        //imprimir details
        System.out.println("DETAILS de ese auto: " + details);
        int repairsN = 0 ;
        //recorro esos y obtengo numberRepairs, y los sumo
        for (DetailEntity detail : details){
            repairsN += detail.getNumberRepairs();
            System.out.println("Número de reparaciones: " + repairsN);
        }
        System.out.println("Número total de reparaciones: " + repairsN);

        if (repairsN > 0 && repairsN <= 2){
            if(engineId.equals(1L)){// Gasolina
                discountByNumberRepairs = initialAmount * 0.05;
            }
            if(engineId.equals(2L)){// Diésel
                discountByNumberRepairs = initialAmount * 0.07;
            }
            if(engineId.equals(3L)){// Híbrido
                discountByNumberRepairs = initialAmount * 0.1;
            }
            if(engineId.equals(4L)){// Eléctrico
                discountByNumberRepairs = initialAmount * 0.08;
            }
        }
        else if (repairsN >= 3 && repairsN <= 5){
            if(engineId.equals(1L)){// Gasolina
                discountByNumberRepairs = initialAmount * 0.1;
            }
            if(engineId.equals(2L)){// Diésel
                discountByNumberRepairs = initialAmount * 0.12;
            }
            if(engineId.equals(3L)){// Híbrido
                discountByNumberRepairs = initialAmount * 0.15;
            }
            if(engineId.equals(4L)){// Eléctrico
                discountByNumberRepairs = initialAmount * 0.13;
            }
        }
        else if (repairsN >= 6 && repairsN <= 9){
            if(engineId.equals(1L)){// Gasolina
                discountByNumberRepairs = initialAmount * 0.15;
            }
            if(engineId.equals(2L)){// Diésel
                discountByNumberRepairs = initialAmount * 0.17;
            }
            if(engineId.equals(3L)){// Híbrido
                discountByNumberRepairs = initialAmount * 0.2;
            }
            if(engineId.equals(4L)){// Eléctrico
                discountByNumberRepairs = initialAmount * 0.18;
            }
        }
        else if (repairsN >= 10){
            if(engineId.equals(1L)){// Gasolina
                discountByNumberRepairs = initialAmount * 0.2;
            }
            if(engineId.equals(2L)){// Diésel
                discountByNumberRepairs = initialAmount * 0.22;
            }
            if(engineId.equals(3L)){// Híbrido
                discountByNumberRepairs = initialAmount * 0.25;
            }
            if(engineId.equals(4L)){// Eléctrico
                discountByNumberRepairs = initialAmount * 0.23;
            }
        }
        return discountByNumberRepairs;
    }


    //Descuento por día de atención
    //Método que hace que un 10% de descuento si el auto ingresa un día Lunes o Jueves entre 9:00 y 12:00
    public double getDiscountByDay(Long carId){
        double discountByDay = 0;
        double initialAmount = getInitialAmount(carId);
        //obtiene una reparación por carId y totalAmount = null
        DetailEntity detail = detailService.getDetailsByCarIdAndTotalAmountNotNull(carId);
        //obtiene la fecha de admisión
        Date admissionDate = detail.getAdmissionDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(admissionDate);
        int day = calendar.get(Calendar.DAY_OF_WEEK); // 1: Domingo, 2: Lunes, 3: Martes, 4: Miércoles, 5: Jueves, 6: Viernes, 7: Sábado


        //obtiene la hora de admisión
        LocalTime admissionHour = detail.getAdmissionHour();
        int hour = admissionHour.getHour();

        if ((day == 2 || day == 5) && (hour >= 9 && hour <= 12)){
            discountByDay = initialAmount * 0.1;
        }
        return discountByDay;

    }

}

    /* //Descuento por bono
    public double getDiscountByBonus(Long carId, Long selectedBonus){
        double discountByBonus = 0;
        if (selectedBonus == 0){
            return 0.0;
        }


    } */     

        


/* 



    //--------------------------------------------------------------PLANIFICAR Y HACER--------------------------------------------------------------
    // Método para descuento por bono


    //--------------------------------------------------------------PLANIFICAR Y HACER--------------------------------------------------------------
    // Método que calcula el total de los descuentos

 */






 