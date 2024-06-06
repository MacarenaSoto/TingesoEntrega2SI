package tingeso.reports_service.services;

import tingeso.reports_service.clients.CarRepairsFeignClient;
import tingeso.reports_service.clients.DetailsFeignClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import tingeso.reports_service.request.RequestCarRepairs;
import tingeso.reports_service.request.RequestCar;

@Service
public class Report1Service {

    @Autowired
    CarRepairsFeignClient carRepairsFeignClient;

    @Autowired
    DetailsFeignClient detailsFeignClient;


    //Trae las repairs del MS car-repair-service
    public ArrayList<RequestCarRepairs> getCarRepairs(){
        System.out.println("Entró a SERVICE getCarRepairs");
        ArrayList<RequestCarRepairs> carRepairs = carRepairsFeignClient.carRepairs();
        return carRepairs;
    }

    //Trae las repairs del MS car-repair-service por type con getCarRepairsByType
    public ArrayList<RequestCar> getCarRepairsByType(Long type){
        System.out.println("Entró a SERVICE getCarRepairsByType");
        ArrayList<RequestCar> carRepairs = detailsFeignClient.carRepairsByType(type);
        System.out.println("carRepairs: " + carRepairs);
        return carRepairs;
    }
/* 
    //Trae las repairs del MS car-repair-service por type
    public ArrayList<RequestCarRepairs> getCarRepairsByType(Long type){
        ArrayList<RequestCarRepairs> carRepairs = carRepairsFeignClient.carRepairs();
        ArrayList<RequestCarRepairs> carRepairsByType = new ArrayList<>();
        for(RequestCarRepairs carRepair : carRepairs){
            if(carRepair.getCarType().equals(type)){
                carRepairsByType.add(carRepair);
            }
        }
        return carRepairsByType;
    }
 */




    





}
