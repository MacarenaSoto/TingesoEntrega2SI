package tingeso.reports_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tingeso.reports_service.entities.Report1Entity;
import tingeso.reports_service.services.Report1Service;

import java.util.List;

import tingeso.reports_service.clients.CarRepairsFeignClient;

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

}
