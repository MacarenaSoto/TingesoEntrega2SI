package tingeso.repairservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tingeso.repairservice.entities.BonusEntity;
import tingeso.repairservice.services.BonusService;

import java.util.List;

@RestController
@RequestMapping("/api/v2/bonus")

public class BonusController {

    @Autowired
    BonusService bonusService;

    @GetMapping("/all")
    public ResponseEntity<List<BonusEntity>> getAllBonus() {
        try {
            List<BonusEntity> bonus = bonusService.getBonuses();
            return ResponseEntity.ok(bonus);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<BonusEntity> getBonusById(@PathVariable Long id) {
        try {
            BonusEntity bonus = bonusService.getBonusById(id);
            return ResponseEntity.ok(bonus);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<BonusEntity> saveBonus(@RequestBody BonusEntity bonusEntity){
        try {
            BonusEntity bonus = bonusService.saveBonus(bonusEntity);
            return ResponseEntity.ok(bonus);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<BonusEntity> updateBonus(@RequestBody BonusEntity bonusEntity) {
        try {
            BonusEntity bonus = bonusService.updateBonus(bonusEntity);
            return ResponseEntity.ok(bonus);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteBonus(@PathVariable Long id){
        try {
            boolean deleted = bonusService.deleteBonus(id);
            return ResponseEntity.ok(deleted);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
