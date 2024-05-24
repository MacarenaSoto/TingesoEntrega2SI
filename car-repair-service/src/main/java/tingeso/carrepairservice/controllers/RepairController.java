package tingeso.carrepairservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import tingeso.carrepairservice.entities.RepairEntity;
import tingeso.carrepairservice.services.RepairService;

@RestController
@RequestMapping("/api/v2/carrepairs")

public class RepairController {

    @Autowired
    RepairService repairService;

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


}
