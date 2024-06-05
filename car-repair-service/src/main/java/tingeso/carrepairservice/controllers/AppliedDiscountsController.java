package tingeso.carrepairservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import tingeso.carrepairservice.entities.AppliedDiscountsEntity;
import tingeso.carrepairservice.services.AppliedDiscountsService;

@RestController
@RequestMapping("/api/v2/appliedDiscounts")
public class AppliedDiscountsController {

    @Autowired
    AppliedDiscountsService appliedDiscountsService;

    @GetMapping("/all")
    public ResponseEntity<List<AppliedDiscountsEntity>> getAllAppliedDiscounts() {
        try {
            List<AppliedDiscountsEntity> appliedDiscounts = appliedDiscountsService.getAppliedDiscounts();
            return ResponseEntity.ok(appliedDiscounts);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<AppliedDiscountsEntity> getAppliedDiscountsById(@PathVariable Long id) {
        try {
            AppliedDiscountsEntity appliedDiscounts = appliedDiscountsService.getAppliedDiscountsById(id);
            return ResponseEntity.ok(appliedDiscounts);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<AppliedDiscountsEntity> saveAppliedDiscounts(@RequestBody AppliedDiscountsEntity appliedDiscountsEntity){
        try {
            AppliedDiscountsEntity appliedDiscounts = appliedDiscountsService.saveAppliedDiscounts(appliedDiscountsEntity);
            return ResponseEntity.ok(appliedDiscounts);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<AppliedDiscountsEntity> updateAppliedDiscounts(@RequestBody AppliedDiscountsEntity appliedDiscountsEntity) {
        try {
            AppliedDiscountsEntity appliedDiscounts = appliedDiscountsService.updateAppliedDiscounts(appliedDiscountsEntity);
            return ResponseEntity.ok(appliedDiscounts);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteAppliedDiscounts(@PathVariable Long id){
        try {
            appliedDiscountsService.deleteAppliedDiscounts(id);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    



}
