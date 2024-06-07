package tingeso.reports_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tingeso.reports_service.request.RequestDetail;
import tingeso.reports_service.services.Report1Service;
import tingeso.reports_service.request.RequestType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tingeso.reports_service.clients.CarRepairsFeignClient;
import tingeso.reports_service.entities.Report1Entity;

@RestController

@RequestMapping("/api/v2/reports1")
public class Report1Controller {

    @Autowired
    Report1Service report1Service;

    @Autowired
    CarRepairsFeignClient carRepairsFeignClient;

  


    //getCarRepairs
    @GetMapping("/carrepairs")
    public ResponseEntity<?> getCarRepairs(){
        System.out.println("Entró a CONTROLLER getCarRepairs");
        try {
            return ResponseEntity.ok(report1Service.getCarRepairs());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //getCarRepairsByType
    @GetMapping("/car/{type}")
    public ResponseEntity<?> getCarByType(@PathVariable Long type){
        System.out.println("Entró a CONTROLLER getCarRepairsByType");
        try {
            return ResponseEntity.ok(report1Service.getCarsByType(type));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //getDetails

    @GetMapping("/details")
    public ResponseEntity<?> getDetails(){
        System.out.println("Entró a CONTROLLER getDetails");
        try {
            return ResponseEntity.ok(report1Service.getDetails());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //getRepairs
    @GetMapping("/repairs")
    public ResponseEntity<?> getRepairs(){
        System.out.println("Entró a CONTROLLER getRepairs");
        try {
            return ResponseEntity.ok(report1Service.getRepairs());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //getTypes
    @GetMapping("/types")
    public ResponseEntity<?> getTypes(){
        System.out.println("Entró a CONTROLLER getTypes");
        try {
            return ResponseEntity.ok(report1Service.getTypes());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //------------------------------------------------------------------------

    //LISTO
    //getDetailsByCarType
    @GetMapping("/details/{type}")
    public ResponseEntity<?> getDetailsByCarType(@PathVariable Long type){
        System.out.println("Entró a CONTROLLER getDetailsByCarType");
        try {
            return ResponseEntity.ok(report1Service.getDetailsByCarType(type));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //getRepairNamesForDetails
    @GetMapping("/repairnames/{details}")
    public ResponseEntity<?> getRepairNamesForDetails(@PathVariable ArrayList<RequestDetail> details ){
        System.out.println("Entró a CONTROLLER getRepairNamesForDetails");
        try {
            return ResponseEntity.ok(report1Service.getRepairNamesForDetails(details));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //getRepairAmountsForDetails
    @GetMapping("/repairamounts/{details}")
    public ResponseEntity<?> getRepairAmountsForDetails(@PathVariable ArrayList<RequestDetail> details ){
        System.out.println("Entró a CONTROLLER getRepairAmountsForDetails");
        try {
            return ResponseEntity.ok(report1Service.getRepairAmountsForDetails(details));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    //LISTO
    //getTypeName
    @GetMapping("/typename")
    public ResponseEntity<?> getTypeName(@RequestParam List<Long> typesIds){
        System.out.println("Entró a CONTROLLER getTypeName");
        try {
            ArrayList<RequestType> types = report1Service.getTypesByIds(typesIds); // Necesitas implementar este método
            return ResponseEntity.ok(report1Service.getTypeName(types));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    
    //LISTO
    //getTypeId
    @GetMapping("/typeid")
    public ResponseEntity<?> getTypeId(@RequestParam List<Long> typeIds ){
        System.out.println("Entró a CONTROLLER getTypeId");
        try {
            ArrayList<RequestType> types = report1Service.getTypesByIds(typeIds);
            return ResponseEntity.ok(report1Service.getTypeId(types));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //LISTO
    //getTypeNameById
    @GetMapping("/typenamebyid/{typeId}")
    public ResponseEntity<?> getTypeNameById(@PathVariable Long typeId ){
        System.out.println("Entró a CONTROLLER getTypeNameById");
        try {
            return ResponseEntity.ok(report1Service.getTypeNameById(typeId));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //LISTO
    //getRepairNames
    @GetMapping("/repairnames")
    public ResponseEntity<?> getRepairNames(){
        System.out.println("Entró a CONTROLLER getRepairNames");
        try {
            return ResponseEntity.ok(report1Service.getRepairNames());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //report1
    @GetMapping("/report1")
    public ResponseEntity<?> report1(){
        System.out.println("Entró a CONTROLLER report1");
        try {
            return ResponseEntity.ok(report1Service.report1());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //filterReports
    @GetMapping("/filterReports")
    public ResponseEntity<List<Report1Entity>> filterReports(
            @RequestParam List<String> carTypes, 
            @RequestParam List<String> repairNames) {
        List<Report1Entity> filteredReports = report1Service.filterReports(carTypes, repairNames);
        return ResponseEntity.ok(filteredReports);
    }

    //filterReports
    @GetMapping("/filterReportsByDate")
    public ResponseEntity<List<Report1Entity>> filterReportsByDate(
            @RequestParam List<String> carTypes, 
            @RequestParam List<String> repairNames,
            @RequestParam String month,
            @RequestParam String year) {
        System.out.println("Entró a CONTROLLER filterReportsByDate");
        List<Report1Entity> filteredReports = report1Service.filterReports2(carTypes, repairNames, month, year);
        return ResponseEntity.ok(filteredReports);
    }
















    
















}
