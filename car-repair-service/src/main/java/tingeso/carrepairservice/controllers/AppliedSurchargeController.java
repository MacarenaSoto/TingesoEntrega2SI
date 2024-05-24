package tingeso.carrepairservice.controllers;

import tingeso.carrepairservice.entities.AppliedSurchargeEntity;
import tingeso.carrepairservice.services.AppliedSurchargeService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/v2/appliedSurcharges")
public class AppliedSurchargeController {

    @Autowired
    AppliedSurchargeService appliedSurchargeService;

    @GetMapping("/all")
    public ResponseEntity<List<AppliedSurchargeEntity>> getAllAppliedSurcharges() {
        try {
            List<AppliedSurchargeEntity> appliedSurcharges = appliedSurchargeService.getAppliedSurcharges();
            return ResponseEntity.ok(appliedSurcharges);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<AppliedSurchargeEntity> getAppliedSurchargeById(@PathVariable Long id) {
        try {
            AppliedSurchargeEntity appliedSurcharge = appliedSurchargeService.getAppliedSurchargeById(id);
            return ResponseEntity.ok(appliedSurcharge);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<AppliedSurchargeEntity> saveAppliedSurcharge(@RequestBody AppliedSurchargeEntity appliedSurchargeEntity){
        try {
            AppliedSurchargeEntity appliedSurcharge = appliedSurchargeService.saveAppliedSurcharge(appliedSurchargeEntity);
            return ResponseEntity.ok(appliedSurcharge);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<AppliedSurchargeEntity> updateAppliedSurcharge(@RequestBody AppliedSurchargeEntity appliedSurchargeEntity) {
        try {
            AppliedSurchargeEntity appliedSurcharge = appliedSurchargeService.updateAppliedSurcharge(appliedSurchargeEntity);
            return ResponseEntity.ok(appliedSurcharge);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteAppliedSurcharge(@PathVariable Long id){
        try {
            boolean deleted = appliedSurchargeService.deleteAppliedSurcharge(id);
            return ResponseEntity.ok(deleted);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
