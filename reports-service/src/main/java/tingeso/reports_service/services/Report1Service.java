package tingeso.reports_service.services;

import tingeso.reports_service.clients.CarRepairsFeignClient;
import tingeso.reports_service.entities.Report1Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import tingeso.reports_service.request.RequestCarRepairs;

@Service
public class Report1Service {

    @Autowired
    CarRepairsFeignClient carRepairsFeignClient;


    //Trae las repairs del MS car-repair-service
    public ArrayList<RequestCarRepairs> getCarRepairs(){
        System.out.println("Entró a SERVICE getCarRepairs");
        ArrayList<RequestCarRepairs> carRepairs = carRepairsFeignClient.carRepairs();
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
