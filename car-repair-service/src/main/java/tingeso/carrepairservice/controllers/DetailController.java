package tingeso.carrepairservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import tingeso.carrepairservice.entities.DetailEntity;
import tingeso.carrepairservice.services.DetailService;

@RestController
@RequestMapping("/api/v2/details")
public class DetailController {

    @Autowired
    DetailService detailService;

    @GetMapping("/all")
    public ResponseEntity<List<DetailEntity>> getAllDetails() {
        try {
            List<DetailEntity> details = detailService.getDetails();
            return ResponseEntity.ok(details);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailEntity> getDetailById(@PathVariable Long id) {
        try {
            DetailEntity detail = detailService.getDetailById(id);
            return ResponseEntity.ok(detail);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<DetailEntity> saveDetail(@RequestBody DetailEntity detailEntity){
        try {
            DetailEntity detail = detailService.saveDetail(detailEntity);
            return ResponseEntity.ok(detail);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<DetailEntity> updateDetail(@RequestBody DetailEntity detailEntity) {
        try {
            DetailEntity detail = detailService.updateDetail(detailEntity);
            return ResponseEntity.ok(detail);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteDetail(@PathVariable Long id){
        try {
            boolean deleted = detailService.deleteDetail(id);
            return ResponseEntity.ok(deleted);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
