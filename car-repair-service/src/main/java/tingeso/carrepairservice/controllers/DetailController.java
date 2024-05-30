package tingeso.carrepairservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Date;
import java.util.ArrayList;

import tingeso.carrepairservice.entities.DetailEntity;
import tingeso.carrepairservice.services.DetailService;

import tingeso.carrepairservice.clients.CarsFeignClient;
import tingeso.carrepairservice.requests.RequestCar;



@RestController

@RequestMapping("/api/v2/details")
public class DetailController {

    CarsFeignClient carsFeignClient;

    DetailService detailService;

    @Autowired
    public DetailController(DetailService detailService, CarsFeignClient carsFeignClient) {
        this.detailService = detailService;
        this.carsFeignClient = carsFeignClient;
    }



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

 /*     //update a un repair específico según su carId
    @PutMapping("/update/{carId}/{kilometraje}/{realExitDate}/{selectedBonus}")
    public ResponseEntity<DetailEntity> updateDetailByCarId(@PathVariable Long carId, @PathVariable int kilometraje, @PathVariable String realExitDate, @PathVariable Long selectedBonus){
        System.out.println("...................--.-.-.--..-.--.-.ENTRÓ AL controller de updateDetailByCarId para actualizar el Detail");
        System.out.println("carId: "+carId);
        System.out.println("km: "+kilometraje);
        System.out.println("realExitDate: "+realExitDate);
        try {
            DetailEntity detail = detailService.updateDetailByCarId(carId, kilometraje, realExitDate, selectedBonus);
            return ResponseEntity.ok(detail);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    } */

    @GetMapping("/probando")
    public ResponseEntity<String> probando(){
        ArrayList<RequestCar> cars = carsFeignClient.car();
        System.out.println("cars: "+cars);
        return ResponseEntity.ok("Probando");
    }

  /*   //update a un detail específico según su carId
    @PutMapping("/update/{carId}")
    public ResponseEntity<DetailEntity> updateDetailByCarId(@PathVariable Long carId, @RequestBody DetailEntity detailEntity){
        try {
            DetailEntity detail = detailService.updateDetailByCarId(carId, detailEntity);
            return ResponseEntity.ok(detail);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    } */

}
