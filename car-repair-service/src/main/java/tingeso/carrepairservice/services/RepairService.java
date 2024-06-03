package tingeso.carrepairservice.services;

import tingeso.carrepairservice.entities.CarList;
import tingeso.carrepairservice.entities.CarListDetails;
import tingeso.carrepairservice.entities.DetailEntity;
import tingeso.carrepairservice.entities.RepairEntity;
import tingeso.carrepairservice.repositories.RepairRepository;
import tingeso.carrepairservice.requests.RequestCar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tingeso.carrepairservice.clients.CarsFeignClient;

@Service
public class RepairService {

    @Autowired
    RepairRepository repairRepository;

    @Autowired
    AppliedDiscountsService appliedDiscountsService;

    @Autowired
    AppliedSurchargeService appliedSurchargeService;

    @Autowired
    CarsFeignClient carsFeignClient;

    @Autowired
    DetailService detailService;

    // métodos de repository
    public ArrayList<RepairEntity> getRepairs() {
        return (ArrayList<RepairEntity>) repairRepository.findAll();
    }

    public RepairEntity getRepairById(Long id) {
        return repairRepository.findById(id).get();
    }

    public ArrayList<RepairEntity> getRepairsByCarId(Long carId) {
        return (ArrayList<RepairEntity>) repairRepository.findByCarId(carId);
    }

    public RepairEntity getRepairsByDetailId(Long detailId) {
        return repairRepository.findByDetailId(detailId);
    }

    public RepairEntity getRepairsByCarIdAndRealExitDateIsNull(Long carId) {
        return repairRepository.findByCarIdAndRealExitDateIsNull(carId);
    }

    // métodos
    public RepairEntity saveRepair(RepairEntity repair) {
        return repairRepository.save(repair);
    }

    public boolean deleteRepair(Long id) throws Exception {
        try {
            repairRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception("No se pudo eliminar la reparación con id: " + id);
        }
    }

    public RepairEntity updateRepair(RepairEntity repair) {
        return repairRepository.save(repair);
    }

    // métodos de servicio / lógica de negocio

    // Método para guardar realExitDate y realExitHour en un repair ya creado:
    public void saveRealExitDateAndHour(Long id, Date realExitDate, LocalTime realExitHour) {
        RepairEntity repair = getRepairById(id);
        repair.setRealExitDate(realExitDate);
        repair.setRealExitHour(realExitHour);
        updateRepair(repair);
    }

    // Método para setear el detailId en un repair ya creado:
    public void setDetailId(Long repairId, Long detailId) {
        RepairEntity repair = getRepairById(repairId);
        repair.setDetailId(detailId);
        updateRepair(repair);
    }

    /*
     * //getDetailIdByCarIdAndAdmissionDate:
     * public DetailEntity getDetailIdByCarIdAndAdmissionDate(Long carId, Date
     * admissionDate){
     * //obtener todos los detalles de un auto:
     * List<DetailEntity> details = detailService.getDetailsByCarId(carId);
     * //recorrer los detalles y comparar la fecha de admisión:
     * for(DetailEntity detail : details){
     * if(detail.getAdmissionDate().equals(admissionDate)){
     * return detail;
     * }
     * }
     * return null;
     * }
     */

    // Trae los autos del MS1
    public ArrayList<RequestCar> getCars() {
        ArrayList<RequestCar> cars = carsFeignClient.car();
        System.out.println("cars: " + cars);
        return cars;
    }

    // updateRepairByCarId
    public RepairEntity updateRepairByCarId(Long carId, RepairEntity repair) {
        System.out.println(
                "....................................................................ENTRÓ AL updateRepairByCarId");
        RepairEntity repairToUpdate = getRepairsByCarIdAndRealExitDateIsNull(carId);
        System.out.println(
                "....................................................................Se obtuvo la reparación con id: "
                        + repairToUpdate.getId());
        repairToUpdate.setRealExitDate(repair.getRealExitDate());
        repairToUpdate.setRealExitHour(repair.getRealExitHour());
        // obtener fecha de admisión la repairToUpdate:
        Date dateAdmissionRepair = repairToUpdate.getAdmissionDate();

        // buscar el detail que tenga = carId y admissionDate = dateAdmissionRepair:
        DetailEntity detail = detailService.getDetailIdByCarIdAndAdmissionDate(carId, dateAdmissionRepair);
        repairToUpdate.setDetailId(detail.getId());

        List<Double> discountAmount = detail.getDiscountAmounts();

        // Suma de los descuentos aplicados:
        double totalDiscount = 0;
        for (Double discount : discountAmount) {
            totalDiscount += discount;
        }
        repairToUpdate.setDiscountAmount(totalDiscount);

        List<Double> surchargeAmount = detail.getSurchargeAmounts();

        // Suma de los recargos aplicados:
        double totalSurcharge = 0;
        for (Double surcharge : surchargeAmount) {
            totalSurcharge += surcharge;
        }
        repairToUpdate.setSurchargeAmount(totalSurcharge);

        repairToUpdate.setFinalAmount(detail.getTotalAmount());

        repairToUpdate.setIva(detail.getIva());

        // actualiza el iva
        // repairToUpdate.setIva(detailService.getIva(carId, km, realExitDate,
        // selectedBonus));

        System.out.println(
                "....................................................................Se actualizó la reparación con id: "
                        + repairToUpdate.getId());
        return updateRepair(repairToUpdate);
    }

    /*
     * //updateRepairByCarId
     * public RepairEntity updateRepairByCarId(Long carId, RepairEntity repair, Long
     * selectedBonus, int km, Date realExitDate){
     * RepairEntity repairToUpdate = getRepairsByCarIdAndRealExitDateIsNull(carId);
     * repairToUpdate.setRealExitDate(repair.getRealExitDate());
     * repairToUpdate.setRealExitHour(repair.getRealExitHour());
     * 
     * 
     * 
     * //actualiza el discountAmount que obtiene de appliedDiscounts
     * //repairToUpdate.setDiscountAmount(detailService.getTotalDiscount(carId,
     * selectedBonus));
     * 
     * 
     * //actualiza el surchargeAmount que obtiene de appliedSurcharge
     * //repairToUpdate.setSurchargeAmount(detailService.getTotalSurcharge(carId,
     * km, realExitDate));
     * 
     * 
     * //actualiza el finalAmount
     * //repairToUpdate.setFinalAmount(detailService.getFinalAmount(carId, km,
     * realExitDate, selectedBonus));
     * 
     * 
     * //actualiza el iva
     * //repairToUpdate.setIva(detailService.getIva(carId, km, realExitDate,
     * selectedBonus));
     * return updateRepair(repairToUpdate);
     * }
     */

    public List<CarList> getCarsList() {
        List<RequestCar> cars = getCars();
        List<CarList> carsList = new ArrayList<>();
        for (RequestCar car : cars) {
            ArrayList<RepairEntity> repairs = getRepairsByCarId(car.getId());
            for (RepairEntity r : repairs) {
                CarList carList = new CarList(
                        car.getId(),
                        car.getPatent(),
                        r.getAdmissionDate(),
                        r.getAdmissionHour(),
                        r.getExitDate(),
                        r.getExitHour(),
                        r.getRealExitDate(),
                        r.getRealExitHour(),
                        r.getDetailId(),
                        r.getDiscountAmount(),
                        r.getSurchargeAmount(),
                        r.getFinalAmount()

                );
                carsList.add(carList);
            }
        }
        return carsList;
    }

    public List<CarListDetails> getCarsListDetails(Long carId) {
        System.out.println("Entró a getCarsListDetails");
        List<RequestCar> cars = getCars();
        RequestCar car2 = null;
    
        // Obtener el auto que tenga el mismo carId
        for (RequestCar car : cars) {
            if (car.getId().equals(carId)) {
                car2 = car;
                break; // Salir del bucle una vez encontrado el auto
            }
        }
    
        if (car2 == null) {
            System.out.println("No se encontró el auto con id: " + carId);
            return new ArrayList<>(); // Retornar una lista vacía si no se encuentra el auto
        }
    
        System.out.println("car2: " + car2);
    
        List<RepairEntity> repairs = getRepairsByCarId(carId);
        System.out.println("ESTAS SON LAS repairs: " + repairs);
    
        List<DetailEntity> details = detailService.getDetailsByCarId(carId);
        List<CarListDetails> carsListDetailsAA = new ArrayList<>();
        System.out.println("ESTAS SON LAS details: " + details);
    
        for (DetailEntity detail : details) {
            Long detailId = detail.getId();
            System.out.println("Entra al PRIMER FOR : " );
            System.out.println("detail: " + detail);
            for (RepairEntity repair : repairs) {
                System.out.println("Entra al SEGUNDO FOR : " );
                System.out.println("repair: " + repair);
                System.out.println("repair.getDetailId(): " + repair.getDetailId());
                System.out.println("detail.getId(): " + detail.getId());
                if (repair.getDetailId() != null && repair.getDetailId().equals(detail.getId())) {
                    System.out.println("Entra al IF : " );

                    CarListDetails carListDetails = new CarListDetails();
                    carListDetails.setId(detail.getCarId());
                    carListDetails.setPatent(car2.getPatent());
    
                    carListDetails.setAdmissionDate(detail.getAdmissionDate());
                    carListDetails.setExitDate(detail.getExitDate());
                    carListDetails.setRealExitDate(repair.getRealExitDate());
    
                    carListDetails.setRepairs(detail.getRepairNames());
                    carListDetails.setRepairAmounts(detail.getRepairAmounts());
    
                    carListDetails.setDiscounts(detail.getDiscountNames());
                    carListDetails.setDiscountAmounts(detail.getDiscountAmounts());
    
                    carListDetails.setSurcharges(detail.getSurchargeNames());
                    carListDetails.setSurchargeAmounts(detail.getSurchargeAmounts());
    
                    carListDetails.setFinalAmount(detail.getTotalAmount());
    
                    // Añadir a la lista de resultados
                    carsListDetailsAA.add(carListDetails);
                    System.out.println("carsListDetails: " + carsListDetailsAA);
                }
            }
        }
        return carsListDetailsAA;
    }
    

}