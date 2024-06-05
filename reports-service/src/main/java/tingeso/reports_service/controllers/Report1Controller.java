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

    @GetMapping("/all")
    public ResponseEntity<List<Report1Entity>> getAllReports1() {
        try {
            List<Report1Entity> reports1 = report1Service.getReports1();
            return ResponseEntity.ok(reports1);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Report1Entity> getReport1ById(@PathVariable Long id) {
        try {
            Report1Entity report1 = report1Service.getReport1ById(id);
            return ResponseEntity.ok(report1);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Report1Entity> saveReport1(@RequestBody Report1Entity report1Entity){
        try {
            Report1Entity report1 = report1Service.saveReport1(report1Entity);
            return ResponseEntity.ok(report1);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteReport1(@PathVariable Long id){
        try {
            report1Service.deleteReport1(id);
            return ResponseEntity.ok("Report1 deleted");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
