package tingeso.carservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import tingeso.carservice.entities.EngineEntity;
import tingeso.carservice.services.EngineService;

@RestController
@RequestMapping("/api/v2/engines")
@CrossOrigin(origins = "*")
public class EngineController {

    @Autowired
    private EngineService engineService;

    @GetMapping("/all")
    public ResponseEntity<List<EngineEntity>> getAllEngines() {
        try {
            List<EngineEntity> engines = engineService.getEngines();
            return ResponseEntity.ok(engines);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<EngineEntity> getEngineById(@PathVariable Long id) {
        try {
            EngineEntity engine = engineService.getEngineById(id);
            return ResponseEntity.ok(engine);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<EngineEntity> addEngine(@RequestBody EngineEntity engineEntity) {
        try {
            EngineEntity engine = engineService.saveEngine(engineEntity);
            return ResponseEntity.ok(engine);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<EngineEntity> updateEngine(@RequestBody EngineEntity engineEntity) {
        try {
            EngineEntity engine = engineService.updateEngine(engineEntity);
            return ResponseEntity.ok(engine);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteEngine(@PathVariable Long id) {
        try {
            engineService.deleteEngine(id);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}

