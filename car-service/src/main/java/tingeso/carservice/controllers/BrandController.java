package tingeso.carservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tingeso.carservice.entities.BrandEntity;
import tingeso.carservice.services.BrandService;

import java.util.List;


@RestController
@RequestMapping("/api/v2/brands")
@CrossOrigin("*")

public class BrandController {
    @Autowired
    private BrandService brandService;

    @GetMapping("/all")
    public ResponseEntity<List<BrandEntity>> getAllBrands() {
        try {
            List<BrandEntity> brands = brandService.getBrands();
            return ResponseEntity.ok(brands);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandEntity> getBrandById(@PathVariable Long id) {
        try {
            BrandEntity brand = brandService.getBrandById(id);
            return ResponseEntity.ok(brand);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<BrandEntity> addBrand(@RequestBody BrandEntity brandEntity) {
        try {
            BrandEntity brand = brandService.saveBrand(brandEntity);
            return ResponseEntity.ok(brand);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<BrandEntity> updateBrand(@RequestBody BrandEntity brandEntity) {
        try {
            BrandEntity brand = brandService.updateBrand(brandEntity);
            return ResponseEntity.ok(brand);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteBrand(@PathVariable Long id) {
        try {
            boolean deleted = brandService.deleteBrand(id);
            return ResponseEntity.ok(deleted);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
