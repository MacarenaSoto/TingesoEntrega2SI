package tingeso.carservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import tingeso.carservice.entities.TypeEntity;
import tingeso.carservice.services.TypeService;

@RestController
@RequestMapping("/api/v2/types")
//@CrossOrigin("*")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/all")
    public ResponseEntity<List<TypeEntity>> getAllTypes() {
        try {
            List<TypeEntity> types = typeService.getTypes();
            return ResponseEntity.ok(types);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeEntity> getTypeById(@PathVariable Long id) {
        try {
            TypeEntity type = typeService.getTypeById(id);
            return ResponseEntity.ok(type);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<TypeEntity> addType(@RequestBody TypeEntity typeEntity) {
        try {
            TypeEntity type = typeService.saveType(typeEntity);
            return ResponseEntity.ok(type);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<TypeEntity> updateType(@RequestBody TypeEntity typeEntity) {
        try {
            TypeEntity type = typeService.updateType(typeEntity);
            return ResponseEntity.ok(type);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteType(@PathVariable Long id) {
        try {
            typeService.deleteType(id);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    //getTypesIds
    @GetMapping("/ids")
    public ResponseEntity<List<Long>> getTypesIds() {
        System.out.println("Entró a CONTROLLER getTypesIds");
        try {
            List<Long> ids = typeService.getTypesIds();
            return ResponseEntity.ok(ids);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //getTypesNames
    @GetMapping("/names")
    public ResponseEntity<List<String>> getTypesNames() {
        System.out.println("Entró a CONTROLLER getTypesNames");
        try {
            List<String> names = typeService.getTypesNames();
            return ResponseEntity.ok(names);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}

