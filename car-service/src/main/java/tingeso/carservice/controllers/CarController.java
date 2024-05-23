package tingeso.carservice.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import tingeso.carservice.entities.CarEntity;
import tingeso.carservice.services.CarService;

@RestController
@RequestMapping("/api/v2/cars")

public class CarController {

    @Autowired
    CarService carService;

    @GetMapping("/all")
    public ResponseEntity<List<CarEntity>> getAllCars() {
        try {
            List<CarEntity> cars = carService.getCars();
            return ResponseEntity.ok(cars);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<CarEntity> getCarById(@PathVariable Long id) {
        try {
            CarEntity car = carService.getCarById(id);
            return ResponseEntity.ok(car);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/patent/{patent}")
    public ResponseEntity<CarEntity> getCarByPatent(@PathVariable String patent) {
        try {
            CarEntity car = carService.getCarByPatent(patent);
            return ResponseEntity.ok(car);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
        public ResponseEntity<CarEntity> saveCar(@RequestBody Map<String, String> car){
            String patent = car.get("patent");
            String model = car.get("model");
            int year = Integer.parseInt(car.get("year"));
            int seats = Integer.parseInt(car.get("seats"));
            Long brandId = Long.parseLong(car.get("brandId"));
            Long typeId = Long.parseLong(car.get("typeId"));
            Long engineId = Long.parseLong(car.get("engineId"));
                CarEntity newCar = new CarEntity();
                newCar.setPatent(patent);
                newCar.setModel(model);
                newCar.setYear(year);
                newCar.setSeats(seats);
                newCar.setBrandId(brandId);
                newCar.setTypeId(typeId);
                newCar.setEngineId(engineId);
                CarEntity carEntity = carService.saveCar(newCar);
                return ResponseEntity.ok(carEntity);

    }

    @PutMapping("/update")
    public ResponseEntity<CarEntity> updateCar(@RequestBody CarEntity carEntity) {
        try {
            CarEntity car = carService.updateCar(carEntity);
            return ResponseEntity.ok(car);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteCar(@PathVariable Long id) {
        try {
            carService.deleteCar(id);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

