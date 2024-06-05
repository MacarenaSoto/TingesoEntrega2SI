package tingeso.carrepairservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import jakarta.ws.rs.Path;

import java.util.List;
import java.util.Collections;
import java.util.Date;

import tingeso.carrepairservice.entities.RepairEntity;
import tingeso.carrepairservice.services.RepairService;
import tingeso.carrepairservice.entities.CarList;
import tingeso.carrepairservice.entities.CarListDetails;


@RestController
@RequestMapping("/api/v2/carrepairs")

public class RepairController {

    @Autowired
    RepairService repairService;
    
    /* @Autowired
    RestTemplate restTemplate;  */

    @GetMapping("/all")
    public ResponseEntity<List<RepairEntity>> getAllRepairs() {
        try {
            List<RepairEntity> repairs = repairService.getRepairs();
            return ResponseEntity.ok(repairs);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<RepairEntity> getRepairById(@PathVariable Long id) {
        try {
            RepairEntity repair = repairService.getRepairById(id);
            return ResponseEntity.ok(repair);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<RepairEntity> saveRepair(@RequestBody RepairEntity repairEntity){
        try {
            RepairEntity repair = repairService.saveRepair(repairEntity);
            return ResponseEntity.ok(repair);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<RepairEntity> updateRepair(@RequestBody RepairEntity repairEntity) {
        try {
            RepairEntity repair = repairService.updateRepair(repairEntity);
            return ResponseEntity.ok(repair);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteRepair(@PathVariable Long id) {
        try {
            boolean deleted = repairService.deleteRepair(id);
            return ResponseEntity.ok(deleted);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    //update a un repair específico según su carId
    @PutMapping("/update/{carId}")
    public ResponseEntity<RepairEntity> updateRepairByCarId(@PathVariable Long carId, @RequestBody RepairEntity repairEntity) {
        try {

            RepairEntity repair = repairService.updateRepairByCarId(carId, repairEntity);
            return ResponseEntity.ok(repair);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /* //update a un repair específico según su carId
    @PutMapping("/update/{carId}")
    public ResponseEntity<RepairEntity> updateRepairByCarId(@PathVariable Long carId, @PathVariable Long selectedBonus,@PathVariable int km, @PathVariable Date realExitDate, @RequestBody RepairEntity repairEntity) {
        try {

            RepairEntity repair = repairService.updateRepairByCarId( carId,  repairEntity , selectedBonus, km ,realExitDate);
            return ResponseEntity.ok(repair);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    } */

    //obtener reparaciones por carId y realExitDate = null
    @GetMapping("/car/{carId}")
    public ResponseEntity<RepairEntity> getRepairsByCarIdAndRealExitDateIsNull(@PathVariable Long carId) {
        try {
            RepairEntity repair = repairService.getRepairsByCarIdAndRealExitDateIsNull(carId);
            return ResponseEntity.ok(repair);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("carsList")
    public ResponseEntity<List<CarList>> getCarsList() {
        System.out.println("dentro del CONTROLLER de /carList, que usa el método getCarsList");
        try {
            List<CarList> carsList = repairService.getCarsList();
            return ResponseEntity.ok(carsList);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("carsList/{id}/{admissionDate}")
    public ResponseEntity<List<CarListDetails>> getCarListById(@PathVariable Long id ,@PathVariable String admissionDate) {
        try {
            List<CarListDetails> carListDetails = repairService.getCarsListDetails(id, admissionDate);
            return ResponseEntity.ok(carListDetails);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


    

    /*  @GetMapping("/test")
    public List<Repair> testRepairs() {
        ParameterizedTypeReference<List<Repair>> responseType = new ParameterizedTypeReference<List<Repair>>() {};

        try {
            ResponseEntity<List<Repair>> responseEntity = restTemplate.exchange(
                "http://repair-service/api/v2/repairs/all", 
                HttpMethod.GET, 
                null, 
                responseType
            );
            System.out.println("dentro de getRepairs, responseEntity: " + responseEntity);
            List<Repair> repairs = responseEntity.getBody();
            System.out.println("dentro de getRepairs, repairs: " + repairs);
            return repairs;
        } catch (RestClientException e) {
            System.err.println("Error durante la llamada a restTemplate.exchange: " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList(); // o lanza una excepción según tu lógica de negocio
        }
    }  */
    


}
