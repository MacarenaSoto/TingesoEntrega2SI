package tingeso.carrepairservice.services;

import tingeso.carrepairservice.entities.AppliedDiscountsEntity;
import tingeso.carrepairservice.entities.DetailEntity;
import tingeso.carrepairservice.entities.RepairEntity;

import tingeso.carrepairservice.repositories.AppliedDiscountsRepository;
import tingeso.carrepairservice.repositories.DetailRepository;
import tingeso.carrepairservice.repositories.RepairRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppliedDiscountsService {

    @Autowired
    AppliedDiscountsRepository appliedDiscountsRepository;

    @Autowired
    DetailService detailService;

    public ArrayList<AppliedDiscountsEntity> getAppliedDiscounts(){
        return (ArrayList<AppliedDiscountsEntity>) appliedDiscountsRepository.findAll();
    }

    public AppliedDiscountsEntity getAppliedDiscountsById(Long id){
        return appliedDiscountsRepository.findById(id).get();
    }

    public ArrayList<AppliedDiscountsEntity> getAppliedDiscountsByCarId(Long carId){
        return (ArrayList<AppliedDiscountsEntity>) appliedDiscountsRepository.findByCarId(carId);
    }

    public ArrayList<AppliedDiscountsEntity> getAppliedDiscountsByRepairId(Long repairId){
        return (ArrayList<AppliedDiscountsEntity>) appliedDiscountsRepository.findByRepairId(repairId);
    }

    public AppliedDiscountsEntity saveAppliedDiscounts(AppliedDiscountsEntity appliedDiscounts){
        return appliedDiscountsRepository.save(appliedDiscounts);
    }

    public boolean deleteAppliedDiscounts(Long id) throws Exception{
        try{
            appliedDiscountsRepository.deleteById(id);
            return true;
        }catch (Exception e){
            throw new Exception("No se pudo eliminar el descuento aplicado con id: "+id);
        }
    }

    public AppliedDiscountsEntity updateAppliedDiscounts(AppliedDiscountsEntity appliedDiscounts){
        return appliedDiscountsRepository.save(appliedDiscounts);
    }

    // Método que obtiene el monto total de las reparaciones base
    public double getInitialAmount(Long carId){
        double total = 0;
        //busco en detalle, los que tengan = carId
        List<DetailEntity> details = detailService.getDetailsByCarId(carId);
        for (DetailEntity detail : details){
            Integer totalAmount = detail.getTotalAmount();
            if (totalAmount == null){
                List<Double> repairAmounts = detail.getRepairAmounts();
                for (Double repairAmount : repairAmounts){
                    total += repairAmount;
                }
            }
        }
        return total;
    }


    //Descuento por cantidad de reparaciones
     // Método que calcula el descuento por cantidad de reparaciones que ha tenido el auto
     public double getDiscountByNumberRepairs(Long carId){
        double discountByNumberRepairs = 0;
        double initialAmount = getInitialAmount(carId);
        //buscar el engineId del auto por carId----- ESTE HAY QUE SACARLO DEL MS1 , CÓOOOOOMO?????
        Long engineId = 1L; // SÓLO por ahora lo dejo así


        //busco en detalle, los que tengan = carId
        List<DetailEntity> details = detailService.getDetailsByCarId(carId);
        //de esos, busco el que tenga totalAmount = null
        for (DetailEntity detail : details){
            Integer totalAmount = detail.getTotalAmount();
            if (totalAmount == null){
                //de ese, busco el número de reparaciones
                int numberRepairs = detail.getNumberRepairs();
                if (numberRepairs >0 && numberRepairs <=2){
                    if (engineId.equals(1L)){// Gasolina
                        discountByNumberRepairs = initialAmount * 0.05;
                    }
                    if (engineId.equals(2L)){// Diésel
                        discountByNumberRepairs = initialAmount * 0.07;
                    }
                    if (engineId.equals(3L)){// Híbrido
                        discountByNumberRepairs = initialAmount * 0.1;
                    }
                    if (engineId.equals(4L)){// Eléctrico
                        discountByNumberRepairs = initialAmount * 0.08;
                    }
                }
                else if (numberRepairs >= 3 && numberRepairs <= 5){
                    if (engineId.equals(1L)){// Gasolina
                        discountByNumberRepairs = initialAmount * 0.1;
                    }
                    if (engineId.equals(2L)){// Diésel
                        discountByNumberRepairs = initialAmount * 0.12;
                    }
                    if (engineId.equals(3L)){// Híbrido
                        discountByNumberRepairs = initialAmount * 0.15;
                    }
                    if (engineId.equals(4L)){// Eléctrico
                        discountByNumberRepairs = initialAmount * 0.13;
                    }
                }
                else if (numberRepairs >= 6 && numberRepairs <= 9){
                    if (engineId.equals(1L)){// Gasolina
                        discountByNumberRepairs = initialAmount * 0.15;
                    }
                    if (engineId.equals(2L)){// Diésel
                        discountByNumberRepairs = initialAmount * 0.17;
                    }
                    if (engineId.equals(3L)){// Híbrido
                        discountByNumberRepairs = initialAmount * 0.2;
                    }
                    if (engineId.equals(4L)){// Eléctrico
                        discountByNumberRepairs = initialAmount * 0.18;
                    }
                }
                else if (numberRepairs >= 10){
                    if (engineId.equals(1L)){// Gasolina
                        discountByNumberRepairs = initialAmount * 0.2;
                    }
                    if (engineId.equals(2L)){// Diésel
                        discountByNumberRepairs = initialAmount * 0.22;
                    }
                    if (engineId.equals(3L)){// Híbrido
                        discountByNumberRepairs = initialAmount * 0.25;
                    }
                    if (engineId.equals(4L)){// Eléctrico
                        discountByNumberRepairs = initialAmount * 0.23;
                    }
                }
            }
        }
        return discountByNumberRepairs;
    }



    //--------------------------------------------------------------PLANIFICAR Y HACER--------------------------------------------------------------
    // Descuento por día de atención.
    // Método que hace un 10% de descuento si el auto ingresa un día Lunes o Jueves
    // entre 9:00 y 12:00



    //--------------------------------------------------------------PLANIFICAR Y HACER--------------------------------------------------------------
    // Método para descuento por bono


    //--------------------------------------------------------------PLANIFICAR Y HACER--------------------------------------------------------------
    // Método que calcula el total de los descuentos










