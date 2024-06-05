package tingeso.reports_service.services;

import tingeso.reports_service.clients.CarRepairsFeignClient;
import tingeso.reports_service.entities.Report1Entity;
import tingeso.reports_service.repositories.Report1Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import tingeso.reports_service.request.RequestCarRepairs;

@Service
public class Report1Service {

    @Autowired
    private Report1Repository report1Repository;

    @Autowired
    CarRepairsFeignClient carRepairsFeignClient;

    //Trae todos los reports1
    public ArrayList<Report1Entity> getReports1(){
        return (ArrayList<Report1Entity>) report1Repository.findAll();
    }

    public Report1Entity getReport1ById(Long id){
        return report1Repository.findReport1ById(id);
    }

    public Report1Entity saveReport1 (Report1Entity report1Entity){
        return report1Repository.save(report1Entity);
    }

    public boolean deleteReport1(Long id) throws Exception{
        Report1Entity report1Entity = report1Repository.findReport1ById(id);
        if(report1Entity != null){
            report1Repository.deleteById(id);
            return true;
        }else{
            throw new Exception("Report1 not found");
        }
    }

    public Report1Entity updateReport1(Report1Entity report1Entity) throws Exception{
        Report1Entity report1 = report1Repository.findReport1ById(report1Entity.getId());
        if(report1 != null){
            return report1Repository.save(report1Entity);
        }else{
            throw new Exception("Report1 not found");
        }
    }

    //Trae las repairs del MS car-repair-service
    public ArrayList<RequestCarRepairs> getCarRepairs(){
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
