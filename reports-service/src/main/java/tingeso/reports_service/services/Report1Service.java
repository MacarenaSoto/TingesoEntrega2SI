package tingeso.reports_service.services;

import tingeso.reports_service.clients.CarRepairsFeignClient;
import tingeso.reports_service.clients.DetailsFeignClient;
import tingeso.reports_service.clients.RepairFeignClient;
import tingeso.reports_service.clients.TypeFeignClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;  

import tingeso.reports_service.request.RequestCarRepairs;
import tingeso.reports_service.request.RequestCar;
import tingeso.reports_service.request.RequestDetail;
import tingeso.reports_service.request.RequestRepair;
import tingeso.reports_service.request.RequestType;

import tingeso.reports_service.entities.Report1Entity;


@Service
public class Report1Service {

    @Autowired
    CarRepairsFeignClient carRepairsFeignClient;

    @Autowired
    DetailsFeignClient detailsFeignClient;

    @Autowired
    RepairFeignClient repairFeignClient;

    @Autowired
    TypeFeignClient typeFeignClient;


    //Trae las repairs del MS car-repair-service
    public ArrayList<RequestCarRepairs> getCarRepairs(){
        System.out.println("EN REPORT 1 SERVICE --> Entró a SERVICE getCarRepairs");
        ArrayList<RequestCarRepairs> carRepairs = carRepairsFeignClient.carRepairs();
        return carRepairs;
    }

    //Trae cars por type con getCarRepairsByType
    public ArrayList<RequestCar> getCarsByType(Long type){
        System.out.println(" EN REPORT 1 SERVICE -->Entró a SERVICE getCarRepairsByType");
        ArrayList<RequestCar> cars = detailsFeignClient.carsByType(type);
        System.out.println("cars: " + cars);
        return cars;
    }

    //Trae los detalles del MS car-repair-service
    public ArrayList<RequestDetail> getDetails(){
        System.out.println(" EN REPORT 1 SERVICE -->Entró a SERVICE getDetails");
        ArrayList<RequestDetail> details = detailsFeignClient.details();
        return details;
    }

    //Trae las repairs del MS car-repair-service 
    public ArrayList<RequestRepair> getRepairs(){ 
        System.out.println(" EN REPORT 1 SERVICE -->Entró a SERVICE getRepairs");
        ArrayList<RequestRepair> repairs = repairFeignClient.repairs();
        return repairs;
    }

    //Trae los types del MS car-repair-service 
    public ArrayList<RequestType> getTypes(){ 
        System.out.println(" EN REPORT 1 SERVICE --> Entró a SERVICE getTypes");
        ArrayList<RequestType> types = typeFeignClient.types();
        return types;
    }

    //-----------------------------------------------------------------------------------------

    //Obtiene los details para cada carByType
    public ArrayList<RequestDetail> getDetailsByCarType(Long type){
        System.out.println(" EN REPORT 1 SERVICE --> Entró a SERVICE getDetailsByCarType");
        ArrayList<RequestCar> cars = getCarsByType(type);
        ArrayList<RequestDetail> details = getDetails();
        ArrayList<RequestDetail> detailsByCarType = new ArrayList<>();
        for(RequestCar car : cars){
            for(RequestDetail detail : details){
                if(car.getId().equals(detail.getCarId())){
                    detailsByCarType.add(detail);
                }
            }
        }
        System.out.println("detailsByCarType: " + detailsByCarType);
        System.out.println("--> SALIÓ DE SERVICE getDetailsByCarType");
        return detailsByCarType;
    }

    //Obtiene el atributo repairNames de una lista de details
    public ArrayList<String> getRepairNamesForDetails(ArrayList<RequestDetail> details){
        System.out.println(" EN REPORT 1 SERVICE --> Entró a SERVICE getRepairNamesForDetails");
        ArrayList<String> repairNames = new ArrayList<>();
        for(RequestDetail detail : details){
            List<String> repairName = detail.getRepairNames();
            for(String name : repairName){
                repairNames.add(name);
            }
        }
        System.out.println("repairNames: " + repairNames);
        System.out.println("--> SALIÓ DE SERVICE getRepairNamesForDetails");
        return repairNames;
    }

    //Obtiene el atributo repairAmounts de una lista de details
    public ArrayList<Integer> getRepairAmountsForDetails(ArrayList<RequestDetail> details){
        System.out.println(" EN REPORT 1 SERVICE --> Entró a SERVICE getRepairAmountsForDetails");
        ArrayList<Integer> repairAmounts = new ArrayList<>();
        for(RequestDetail detail : details){
            List<Integer> repairAmount = detail.getRepairAmounts();
            for(Integer amount : repairAmount){
                repairAmounts.add(amount);
            }
        }
        System.out.println("repairAmounts: " + repairAmounts);
        System.out.println("--> SALIÓ DE SERVICE getRepairAmountsForDetails");
        return repairAmounts;
    }



    //Obtiene el atributo name de Type
    public ArrayList<String> getTypeName(ArrayList<RequestType> types){
        System.out.println(" EN REPORT 1 SERVICE --> Entró a SERVICE getTypeName");
        //ArrayList<RequestType> types = getTypes();
        ArrayList<String> typeNames = new ArrayList<>();
        for(RequestType typeObj : types){
            typeNames.add(typeObj.getName());
            }
        System.out.println("typeNames: " + typeNames);
        System.out.println("--> SALIÓ DE SERVICE getTypeName");
        return typeNames;
    }

    //Obtiene el atributo id de Type
    public ArrayList<Long> getTypeId(ArrayList<RequestType> types){
        System.out.println(" EN REPORT 1 SERVICE --> Entró a SERVICE getTypeId");
        //ArrayList<RequestType> types = getTypes();
        ArrayList<Long> typeIds = new ArrayList<>();
        for(RequestType typeObj : types){
            typeIds.add(typeObj.getId());
            }
        System.out.println("typeIds: " + typeIds);
        System.out.println("--> SALIÓ DE SERVICE getTypeId");
        return typeIds;
    }

    //Obtiene el nombre de type por id
    public String getTypeNameById(Long typeId){
        System.out.println(" EN REPORT 1 SERVICE --> Entró a SERVICE getTypeNameById");
        ArrayList<RequestType> types = getTypes();
        String typeName = "";
        for(RequestType typeObj : types){
            if(typeObj.getId().equals(typeId)){
                typeName = typeObj.getName();
            }
        }
        System.out.println("typeName: " + typeName);
        System.out.println("--> SALIÓ DE SERVICE getTypeNameById");
        return typeName;
    }

    //Obtiene el atributo name de Repair
    public ArrayList<String> getRepairNames(){
        System.out.println(" EN REPORT 1 SERVICE --> Entró a SERVICE getRepairNames");
        ArrayList<RequestRepair> repairs = getRepairs();
        ArrayList<String> repairNames = new ArrayList<>();
        for(RequestRepair repairObj : repairs){
            repairNames.add(repairObj.getName());
            }
        System.out.println("repairNames: " + repairNames);
        System.out.println("--> SALIÓ DE SERVICE getRepairNames");
        return repairNames;
    }







    //Método report1 
    public List<Report1Entity> report1(){
        System.out.println(" EN REPORT 1 SERVICE --> Entró a SERVICE report1");
        ArrayList<Report1Entity> report1 = new ArrayList<>();
        ArrayList<RequestDetail> details = getDetails();
        ArrayList<RequestType> types = getTypes();

        ArrayList<Long> typeIds = getTypeId(types);
        for (Long typeId : typeIds){
            ArrayList<RequestDetail> detailsByCarType = getDetailsByCarType(typeId);
            ArrayList<String> repairNames = getRepairNamesForDetails(detailsByCarType);//nombres de las reparaciones que se han hecho
            ArrayList<Integer> repairAmounts = getRepairAmountsForDetails(detailsByCarType);//valores  de las reparaciones que se han hecho
            ArrayList<String> baseRepairNames = getRepairNames();//nombres de todas las reparaciones que existen

            int nRepairedCars = 0;
            double nAmountRepairedCars = 0;

            String carTypeName = getTypeNameById(typeId);


            for (String baseRepairName : baseRepairNames){
                for (String repairName : repairNames){
                    if(baseRepairName.equals(repairName)){
                        System.out.println("baseRepairName: " + baseRepairName);
                        System.out.println("repairName: " + repairName);
                        //se crea un contador para contar cuantas veces se repite una reparación
                        nRepairedCars++;
                        nAmountRepairedCars = nAmountRepairedCars + repairAmounts.get(repairNames.indexOf(repairName));

                    }
                }
                //se crea un objeto Report1Entity
                Report1Entity report1Entity = new Report1Entity();
                report1Entity.setCarType(carTypeName);
                report1Entity.setTypeId(typeId);
                report1Entity.setRepairName(baseRepairName);
                //report1Entity.setRepairId(baseRepairName);?????????la necesitaré????
                report1Entity.setNRepairedCars(nRepairedCars);
                report1Entity.setAmountRepairedCars(nAmountRepairedCars);

                report1.add(report1Entity);
                System.out.println("Cada reporte individual -->report1Entity: " + report1Entity);

            }
        }
        System.out.println("Todos los reportes que debería hacer --> report1: " + report1);
        System.out.println("--> SALIÓ DE SERVICE report1");
        return report1;
        
    }










    





}
