package tingeso.repairservice.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import tingeso.repairservice.entities.RepairEntity;
import tingeso.repairservice.services.RepairService;

@RestController
@RequestMapping("/api/v2/repairs")
//@CrossOrigin("*")

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
    public ResponseEntity<RepairEntity> saveRepair(@RequestBody Map<String, String> repair){
        String name = repair.get("name");
        int ammount = Integer.parseInt(repair.get("ammount"));
        Long engineId = Long.parseLong(repair.get("engineId"));
        RepairEntity newRepair = new RepairEntity();
        newRepair.setName(name);
        newRepair.setAmmount(ammount);
        newRepair.setEngineId(engineId);
        RepairEntity repairEntity = repairService.saveRepair(newRepair);
        return ResponseEntity.ok(repairEntity);
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
            repairService.deleteRepair(id);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


}
