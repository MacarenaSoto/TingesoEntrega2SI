package tingeso.carrepairservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.thoughtworks.xstream.core.util.Types;

import java.util.List;
import java.util.Date;
import java.util.ArrayList;

import tingeso.carrepairservice.entities.DetailEntity;
import tingeso.carrepairservice.services.DetailService;
import tingeso.carrepairservice.clients.CarsFeignClient;
import tingeso.carrepairservice.requests.RequestBrand;
import tingeso.carrepairservice.requests.RequestCar;
import tingeso.carrepairservice.clients.BrandsFeignClient;
import tingeso.carrepairservice.clients.TypesFeignClient;



@RestController

@RequestMapping("/api/v2/details")
public class DetailController {

    CarsFeignClient carsFeignClient;

    BrandsFeignClient brandsFeignClient;

    TypesFeignClient typesFeignClient;

    DetailService detailService;

    @Autowired
    public DetailController(DetailService detailService, CarsFeignClient carsFeignClient, BrandsFeignClient brandsFeignClient, TypesFeignClient typesFeignClient) {
        this.detailService = detailService;
        this.carsFeignClient = carsFeignClient;
        this.brandsFeignClient = brandsFeignClient;
        this.typesFeignClient = typesFeignClient;
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

      //update a un repair específico según su carId
    @PutMapping("/update/{carId}/{kilometraje}/{realExitDate}/{selectedBonus}")
    public ResponseEntity<DetailEntity> updateDetailByCarId(@PathVariable Long carId, @PathVariable int kilometraje, @PathVariable String realExitDate, @PathVariable Long selectedBonus){
        System.out.println("...................--.-.-.--..-.--.-.ENTRÓ AL controller de updateDetailByCarId para actualizar el Detail");
        System.out.println("carId: "+carId);
        System.out.println("km: "+kilometraje);
        System.out.println("realExitDate: "+realExitDate);
        try {
            DetailEntity detail = detailService.updateDetailByCarId(carId, kilometraje, realExitDate, selectedBonus);
            DetailEntity detail2 = detailService.updateDetailByCarId2(carId, kilometraje, realExitDate, selectedBonus);
            return ResponseEntity.ok(detail2);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    } 

    @GetMapping("/probando")
    public ResponseEntity<String> probando(){
        ArrayList<RequestCar> cars = carsFeignClient.car();
        for (RequestCar car : cars) {
            Long id = car.getId();
            String patent = car.getPatent();
            String model = car.getModel();
            int year = car.getYear();
            int seats = car.getSeats();
            Long brandId = car.getBrandId();
            Long typeId = car.getTypeId();
            Long engineId = car.getEngineId();
            System.out.println("id: "+id);
            System.out.println("patent: "+patent);
            System.out.println("model: "+model);
            System.out.println("year: "+year);
            System.out.println("seats: "+seats);
            System.out.println("brandId: "+brandId);
            System.out.println("typeId: "+typeId);
            System.out.println("engineId: "+engineId);
        }
        //OBTENER datos de los autos:



        System.out.println("cars: "+cars);
        return ResponseEntity.ok("Probando");
    }

    @GetMapping("/probando2")
    public ResponseEntity<String> probando2(){
        detailService.getBrands();
        return ResponseEntity.ok("Probando2");
    }

    @GetMapping("/probando3")
    public ResponseEntity<String> probando3(){
        //llamar al método getCars22 de detailService:
        detailService.getCars();
        return ResponseEntity.ok("Probando3");
    }

    @GetMapping("/fxGetDiscountByNumberRepairs/{carId}")
    public ResponseEntity<String> fxGetDiscountByNumberRepairs(@PathVariable Long carId){
        detailService.getDiscountByNumberRepairs(carId);
        return ResponseEntity.ok("fxGgtDiscountByNumberRepairs");
    }

}