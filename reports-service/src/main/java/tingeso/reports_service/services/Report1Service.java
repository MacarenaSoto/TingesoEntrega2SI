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

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;

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

    // Trae las repairs del MS car-repair-service
    public ArrayList<RequestCarRepairs> getCarRepairs() {
        // System.out.println("EN REPORT 1 SERVICE --> Entró a SERVICE getCarRepairs");
        ArrayList<RequestCarRepairs> carRepairs = carRepairsFeignClient.carRepairs();
        return carRepairs;
    }

    // Trae cars por type con getCarRepairsByType
    public ArrayList<RequestCar> getCarsByType(Long type) {
        // System.out.println(" EN REPORT 1 SERVICE -->Entró a SERVICE
        // getCarRepairsByType");
        ArrayList<RequestCar> cars = detailsFeignClient.carsByType(type);
        // System.out.println("cars: " + cars);
        return cars;
    }

    // Trae los detalles del MS car-repair-service
    public ArrayList<RequestDetail> getDetails() {
        // System.out.println(" EN REPORT 1 SERVICE -->Entró a SERVICE getDetails");
        ArrayList<RequestDetail> details = detailsFeignClient.details();
        return details;
    }

    // Trae las repairs del MS car-repair-service
    public ArrayList<RequestRepair> getRepairs() {
        // System.out.println(" EN REPORT 1 SERVICE -->Entró a SERVICE getRepairs");
        ArrayList<RequestRepair> repairs = repairFeignClient.repairs();
        return repairs;
    }

    // Trae los types del MS car-repair-service
    public ArrayList<RequestType> getTypes() {
        // System.out.println(" EN REPORT 1 SERVICE --> Entró a SERVICE getTypes");
        ArrayList<RequestType> types = typeFeignClient.types();
        return types;
    }

    // -----------------------------------------------------------------------------------------

    // LISTO
    // Obtiene los details para cada carByType
    public ArrayList<RequestDetail> getDetailsByCarType(Long type) {
        // System.out.println(" EN REPORT 1 SERVICE --> Entró a SERVICE
        // getDetailsByCarType");
        ArrayList<RequestCar> cars = getCarsByType(type);
        ArrayList<RequestDetail> details = getDetails();
        ArrayList<RequestDetail> detailsByCarType = new ArrayList<>();
        for (RequestCar car : cars) {
            for (RequestDetail detail : details) {
                if (car.getId().equals(detail.getCarId())) {
                    detailsByCarType.add(detail);
                }
            }
        }
        // System.out.println("detailsByCarType: " + detailsByCarType);
        // System.out.println("--> SALIÓ DE SERVICE getDetailsByCarType");
        return detailsByCarType;
    }

    // // LISTO
    // Obtiene el atributo repairNames de una lista de details
    public ArrayList<String> getRepairNamesForDetails(ArrayList<RequestDetail> details) {
        System.out.println(" EN REPORT 1 SERVICE --> Entró a SERVICE getRepairNamesForDetails");
        System.out.println("details: " + details);
        ArrayList<String> repairNames = new ArrayList<>();
        System.out.println("repairNames: " + repairNames);

        for (RequestDetail detail : details) {
            System.out.println(" Entro al primer for de getRepairNamesForDetails");
            System.out.println("detail: " + detail);
            List<String> repairName = detail.getRepairNames();
            System.out.println("repairName: " + repairName);
            if (repairName != null) {
                for (String name : repairName) {
                    System.out.println(" Entro al segundo for de getRepairNamesForDetails");
                    System.out.println("name: " + name);
                    repairNames.add(name);
                }
            }
        }
        System.out.println("repairNames: " + repairNames);
        System.out.println("--> SALIÓ DE SERVICE getRepairNamesForDetails");
        return repairNames;
    }

    // // LISTO
    // Obtiene el atributo repairAmounts de una lista de details
    public ArrayList<Integer> getRepairAmountsForDetails(ArrayList<RequestDetail> details) {
        // System.out.println(" EN REPORT 1 SERVICE --> Entró a SERVICE
        // getRepairAmountsForDetails");
        ArrayList<Integer> repairAmounts = new ArrayList<>();
        for (RequestDetail detail : details) {
            List<Integer> repairAmount = detail.getRepairAmounts();
            if (repairAmount != null) {
                for (Integer amount : repairAmount) {
                    repairAmounts.add(amount);
                }
            }
        }
        // System.out.println("repairAmounts: " + repairAmounts);
        // System.out.println("--> SALIÓ DE SERVICE getRepairAmountsForDetails");
        return repairAmounts;
    }
    // LISTO
    // Método para que funcione getTypeName

    public ArrayList<RequestType> getTypesByIds(List<Long> typeIds) {
        // Aquí debes implementar la lógica para obtener los objetos RequestType por sus
        // IDs
        ArrayList<RequestType> types = new ArrayList<>();
        for (Long typeId : typeIds) {
            RequestType type = findRequestTypeById(typeId); // Necesitas implementar este método
            if (type != null) {
                types.add(type);
            }
        }
        return types;
    }

    // LISTO
    // Método para que funcione getTypeName
    // findRequestTypeById
    private RequestType findRequestTypeById(Long typeId) {
        ArrayList<RequestType> types = getTypes();
        for (RequestType type : types) {
            if (type.getId().equals(typeId)) {
                return type;
            }
        }
        return null;
    }

    // LISTO
    // Obtiene el atributo name de Type
    public ArrayList<String> getTypeName(ArrayList<RequestType> types) {
        // System.out.println(" EN REPORT 1 SERVICE --> Entró a SERVICE getTypeName");
        // ArrayList<RequestType> types = getTypes();
        ArrayList<String> typeNames = new ArrayList<>();
        for (RequestType typeObj : types) {
            typeNames.add(typeObj.getName());
        }
        // System.out.println("typeNames: " + typeNames);
        // System.out.println("--> SALIÓ DE SERVICE getTypeName");
        return typeNames;
    }

    // LISTO
    // Obtiene el atributo id de Type
    public ArrayList<Long> getTypeId(ArrayList<RequestType> types) {
        // System.out.println(" EN REPORT 1 SERVICE --> Entró a SERVICE getTypeId");
        // ArrayList<RequestType> types = getTypes();
        ArrayList<Long> typeIds = new ArrayList<>();
        for (RequestType typeObj : types) {
            typeIds.add(typeObj.getId());
        }
        // System.out.println("typeIds: " + typeIds);
        // System.out.println("--> SALIÓ DE SERVICE getTypeId");
        return typeIds;
    }

    // LISTO
    // Obtiene el nombre de type por id
    public String getTypeNameById(Long typeId) {
        // System.out.println(" EN REPORT 1 SERVICE --> Entró a SERVICE
        // getTypeNameById");
        ArrayList<RequestType> types = getTypes();
        String typeName = "";
        for (RequestType typeObj : types) {
            if (typeObj.getId().equals(typeId)) {
                typeName = typeObj.getName();
            }
        }
        // System.out.println("typeName: " + typeName);
        // System.out.println("--> SALIÓ DE SERVICE getTypeNameById");
        return typeName;
    }

    // LISTO
    // Obtiene el atributo name de Repair
    public ArrayList<String> getRepairNames() {
        // System.out.println(" EN REPORT 1 SERVICE --> Entró a SERVICE
        // getRepairNames");
        ArrayList<RequestRepair> repairs = getRepairs();
        Set<String> repairNamesSet = new HashSet<>();
        for (RequestRepair repairObj : repairs) {
            repairNamesSet.add(repairObj.getName());
        }
        // System.out.println("repairNames: " + repairNames);
        // System.out.println("--> SALIÓ DE SERVICE getRepairNames");
        return new ArrayList<>(repairNamesSet);
    }

    // Método report1
    public List<Report1Entity> report1() {
        System.out.println(" EN REPORT 1 SERVICE --> Entró a SERVICE report1");
        ArrayList<Report1Entity> report1 = new ArrayList<>();
        System.out.println("report1: " + report1);
        ArrayList<RequestDetail> details = getDetails();
        System.out.println("details: " + details);
        ArrayList<RequestType> types = getTypes();
        System.out.println("types: " + types);

        ArrayList<Long> typeIds = getTypeId(types);
        System.out.println("typeIds: " + typeIds);
        for (Long typeId : typeIds) {
            System.out.println("---------------------Entró al for de typeIds");
            System.out.println("typeId: " + typeId);
            ArrayList<RequestDetail> detailsByCarType = getDetailsByCarType(typeId);
            System.out.println("detailsByCarType: " + detailsByCarType);
            ArrayList<String> repairNames = getRepairNamesForDetails(detailsByCarType);// nombres de las reparaciones
                                                                                       // que se han hecho
            System.out.println("repairNames: " + repairNames);
            ArrayList<Integer> repairAmounts = getRepairAmountsForDetails(detailsByCarType);// valores de las
                                                                                            // reparaciones que se han
                                                                                            // hecho
            System.out.println("repairAmounts: " + repairAmounts);
            ArrayList<String> baseRepairNames = getRepairNames();// nombres de todas las reparaciones que existen
            System.out.println("baseRepairNames: " + baseRepairNames);

            String carTypeName = getTypeNameById(typeId);
            System.out.println("carTypeName: " + carTypeName);

            for (String baseRepairName : baseRepairNames) {
                System.out.println("---------------------Entró al for de baseRepairNames");
                System.out.println("baseRepairName: " + baseRepairName);

                int nRepairedCars = 0;
                double nAmountRepairedCars = 0;

                for (String repairName : repairNames) {
                    System.out.println("---------------------Entró al for de repairNames");
                    System.out.println("repairName: " + repairName);
                    if (baseRepairName.equals(repairName)) {
                        System.out.println("---------------------Entró al if de baseRepairName.equals(repairName)");
                        System.out.println("baseRepairName: " + baseRepairName);
                        System.out.println("repairName: " + repairName);
                        // se crea un contador para contar cuantas veces se repite una reparación
                        nRepairedCars++;
                        System.out.println("nRepairedCars: " + nRepairedCars);
                        nAmountRepairedCars = nAmountRepairedCars + repairAmounts.get(repairNames.indexOf(repairName));
                        System.out.println("nAmountRepairedCars: " + nAmountRepairedCars);

                // se crea un objeto Report1Entity
                Report1Entity report1Entity = new Report1Entity();
                report1Entity.setCarType(carTypeName);
                report1Entity.setTypeId(typeId);
                report1Entity.setRepairName(baseRepairName);
                // report1Entity.setRepairId(baseRepairName);?????????la necesitaré????
                report1Entity.setNRepairedCars(nRepairedCars);
                report1Entity.setAmountRepairedCars(nAmountRepairedCars);

                report1.add(report1Entity);
                System.out.println("Cada reporte individual -->report1Entity: " + report1Entity);
                
            }
        }

            }
        }
        System.out.println("Todos los reportes que debería hacer --> report1: " + report1);
        System.out.println("--> SALIÓ DE SERVICE report1");
        return report1;

    }

    public List<Report1Entity> filterReports(List<String> carTypes, List<String> repairNames) {
        // Aquí deberías obtener la lista completa de Report1Entity desde tu base de datos o fuente de datos
        List<Report1Entity> allReports = report1(); // Implementa este método para obtener todos los reportes

        Map<String, Report1Entity> map = new HashMap<>();

        for (Report1Entity report : allReports) {
            if (carTypes.contains(report.getCarType()) && repairNames.contains(report.getRepairName())) {
                String key = report.getCarType() + "-" + report.getRepairName();
                if (!map.containsKey(key) || map.get(key).getNRepairedCars() < report.getNRepairedCars()) {
                    map.put(key, report);
                }
            }
        }

        return new ArrayList<>(map.values());

    }

    

    //getFilteredReports
    public List<Report1Entity> getFilteredReports(List<Long> typeIds, List<String> repairNames) {
        List<Report1Entity> allReports = report1();
        List<Report1Entity> filteredReports = new ArrayList<>();
        for (Report1Entity report : allReports) {
            if (typeIds.contains(report.getTypeId()) && repairNames.contains(report.getRepairName())) {
                filteredReports.add(report);
            }
        }
        return filteredReports;
    }

    public List<Map<String, Object>> summarizeRepairs(List<Report1Entity> reports) {
        System.out.println(" EN REPORT 1 SERVICE --> Entró a SERVICE summarizeRepairs");
        List<Integer> nRepairedCarsList = new ArrayList<>();
        List<Double> amountRepairedCarsList = new ArrayList<>();
        List<String> repairNameList = new ArrayList<>();
        for (Report1Entity report : reports) {
            String carType = report.getCarType();
            String repairName = report.getRepairName();
            Double amountRepairedCars = report.getAmountRepairedCars();
            //Agregar el nombre de la reparación a la lista
            if (!repairNameList.contains(repairName)) {
                repairNameList.add(repairName);
            }
            //Agregar carType a la lista
            if (!repairNameList.contains(carType)) {
                repairNameList.add(carType);
            }
        }
        for (String repairName : repairNameList) {
            for (String carType : repairNameList) {
                int count = 0;
                double amount = 0;
                for (Report1Entity report2 : reports) {
                    if (report2.getCarType().equals(carType) && report2.getRepairName().equals(repairName)) {
                        count += report2.getNRepairedCars();
                        amount += report2.getAmountRepairedCars();
                    }
                }
                nRepairedCarsList.add(count);
                amountRepairedCarsList.add(amount);
            }
        }
    }




    



}
