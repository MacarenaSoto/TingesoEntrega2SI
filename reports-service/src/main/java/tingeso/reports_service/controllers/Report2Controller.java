package tingeso.reports_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tingeso.reports_service.entities.Report2Entity;
import tingeso.reports_service.services.Report2Service;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v2/reports2")
public class Report2Controller {

    @Autowired
    Report2Service report2Service;

    //report2
    @GetMapping("/report2")
    public ResponseEntity<?> report2(@RequestParam String month, @RequestParam String year){
        System.out.println("Entró a CONTROLLER report2");
        try {
            return ResponseEntity.ok(report2Service.report2(month, year));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


     // report2Final: 
    @GetMapping("/report2Final")
    public ResponseEntity<?> report2Final(@RequestParam String month, @RequestParam String year){
        System.out.println("Entró a CONTROLLER report2Final");
        try {
            return ResponseEntity.ok(report2Service.report2Final(month , year));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // report2Alls:
    @GetMapping("/report2Alls")
    public ResponseEntity<?> report2Alls( @RequestParam String month, @RequestParam String year){
        System.out.println("Entró a CONTROLLER report2Alls");
        try {
            return ResponseEntity.ok(report2Service.report2Alls(month, year));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/report2AllsFiltered")
    public ResponseEntity<?> report2AllsFiltered( @RequestParam String month, @RequestParam String year){
        System.out.println("Entró a CONTROLLER report2AllsFiltered");
        try {
            List<List<Report2Entity>> List = report2Service.report2Alls(month,year);
            return ResponseEntity.ok(report2Service.filterReports2(List));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }



}
