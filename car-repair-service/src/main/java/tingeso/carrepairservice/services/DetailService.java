package tingeso.carrepairservice.services;

import tingeso.carrepairservice.entities.DetailEntity;
import tingeso.carrepairservice.entities.RepairEntity;
import tingeso.carrepairservice.repositories.DetailRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.text.ParseException;

import tingeso.carrepairservice.clients.BrandsFeignClient;
import tingeso.carrepairservice.clients.CarsFeignClient;
import tingeso.carrepairservice.clients.TypesFeignClient;
import tingeso.carrepairservice.clients.EnginesFeignClient;
import tingeso.carrepairservice.clients.BonusFeignClient;
import tingeso.carrepairservice.clients.RepairsFeignClient;

import tingeso.carrepairservice.requests.RequestBrand;
import tingeso.carrepairservice.requests.RequestCar;
import tingeso.carrepairservice.requests.RequestEngine;
import tingeso.carrepairservice.requests.RequestType;
import tingeso.carrepairservice.requests.RequestBonus;
import tingeso.carrepairservice.requests.RequestRepair;

@Service
public class DetailService {

    @Autowired
    DetailRepository detailRepository;

    /*
     * @Autowired
     * RestTemplate restTemplate;
     */

    @Autowired
    AppliedDiscountsService appliedDiscountsService;

    @Autowired
    AppliedSurchargeService appliedSurchargeService;

    @Autowired
    RepairService repairService;

    @Autowired
    CarsFeignClient carsFeignClient;

    @Autowired
    BrandsFeignClient brandsFeignClient;

    @Autowired
    TypesFeignClient typesFeignClient;

    @Autowired
    EnginesFeignClient enginesFeignClient;

    @Autowired
    BonusFeignClient bonusesFeignClient;

    @Autowired
    RepairsFeignClient repairsFeignClient;

    public DetailEntity getDetailById(Long id) {
        return detailRepository.findById(id).get();
    }

    public DetailEntity saveDetail(DetailEntity detail) {
        return detailRepository.save(detail);
    }

    public boolean deleteDetail(Long id) throws Exception {
        try {
            detailRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception("No se pudo eliminar el detalle con id: " + id);
        }
    }

    public DetailEntity updateDetail(DetailEntity detail) {
        return detailRepository.save(detail);
    }

    // getDetailsByCarId
    public List<DetailEntity> getDetailsByCarId(Long carId) {
        List<DetailEntity> details = detailRepository.findByCarId(carId);
        return details;
    }

    // getDetailByCarId and totalAmount == null
    public DetailEntity getDetailsByCarIdAndTotalAmountNull(Long carId) {
        DetailEntity details = detailRepository.findByCarIdAndTotalAmountIsNull(carId);
        return details;
    }

    // getDetails
    public List<DetailEntity> getDetails() {
        List<DetailEntity> details = detailRepository.findAll();
        return details;
    }

    // Trae los autos del MS1
    public ArrayList<RequestCar> getCars() {
        ArrayList<RequestCar> cars = carsFeignClient.car();
        System.out.println("cars: " + cars);
        return cars;
    }

    // Trae las marcas del MS1
    public ArrayList<RequestBrand> getBrands() {
        ArrayList<RequestBrand> brands = brandsFeignClient.brand();
        System.out.println("brands: " + brands);
        return brands;
    }

    // Trae los tipos del MS1
    public ArrayList<RequestType> getTypes() {
        ArrayList<RequestType> types = typesFeignClient.type();
        System.out.println("types: " + types);
        return types;
    }

    // Trae los engines del MS1
    public ArrayList<RequestEngine> getEngines() {
        ArrayList<RequestEngine> engines = enginesFeignClient.engine();
        System.out.println("engines: " + engines);
        return engines;
    }

    // Trae los bonus del MS2
    public ArrayList<RequestBonus> getBonuses() {
        ArrayList<RequestBonus> bonuses = bonusesFeignClient.bonus();
        System.out.println("bonuses: " + bonuses);
        return bonuses;
    }

    // Trae los repairs del MS2
    public ArrayList<RequestRepair> getRepairs() {
        ArrayList<RequestRepair> repairs = repairsFeignClient.repair();
        System.out.println("repairs: " + repairs);
        return repairs;
    }

    // PROBAR SI ESTA FUNCIONAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
    // getCarById
    public RequestCar getCarById(Long carId) {
        ArrayList<RequestCar> cars = getCars();
        RequestCar car = new RequestCar();
        for (RequestCar c : cars) {
            if (c.getId() == carId) {
                car = c;
            }
        }
        return car;

    }

    // findByCarIdAndRealExitDateIsNull que tiene repairService

    // updateDetailByCarId
    public DetailEntity updateDetailByCarId(Long carId, DetailEntity detail) {
        DetailEntity detailToUpdate = getDetailsByCarIdAndTotalAmountNull(carId);
        detailToUpdate.setTotalAmount(detail.getTotalAmount());
        detailToUpdate.setAdmissionDate(detail.getAdmissionDate());
        return updateDetail(detailToUpdate);
    }

    // -------------------------------------------------------------------------------------------------------------
    // MÉTODOS QUE CALCULAN DESCUENTOS Y RECARGOS:

    // 1. Métodos para SURCHARGE.....................

    // Método que obtiene el monto total de las reparaciones base
    public double getInitialAmount(Long carId) {
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------Entra a la función getInitialAmount ");
        DetailEntity detail = getDetailsByCarIdAndTotalAmountNull(carId);
        System.out.println("Detail: " + detail);
        List<Long> repairIds = detail.getRepairIds();
        System.out.println("RepairIds: " + repairIds);
        List<RequestRepair> repairs = getRepairs();
        System.out.println("Repairs: " + repairs);
        double initialAmount = 0;
        for (int i = 0; i < repairIds.size(); i++) {
            for (int j = 0; j < repairs.size(); j++) {
                if (repairIds.get(i).equals( repairs.get(j).getId())) {
                    initialAmount += repairs.get(j).getAmmount();
                }
            }
        }
        System.out.println("initialAmount: " + initialAmount);
        return initialAmount;
    }

    // Recargo por kilometraje
    public double getSurchargeByKm(Long carId, double km) {
        double surchargeByKm = 0;
        double initialAmount = getInitialAmount(carId);
        RequestCar car = getCarById(carId);
        Long type = car.getTypeId();
        if (km > 0 && km <= 5000) {
            if (type.equals(1L)) {// Sedan
                surchargeByKm = initialAmount * 0;
            }
            if (type.equals(2L)) {// Hatchback
                surchargeByKm = initialAmount * 0;
            }   
            if (type.equals(3L)) {// SUV
                surchargeByKm = initialAmount * 0;
            }
            if (type.equals(4L)) {// pickup
                surchargeByKm = initialAmount * 0;
        
            if (type.equals(5L)) {// Furgoneta
                surchargeByKm = initialAmount * 0;
            }
        }
        if (km > 5001 && km <= 12000) {
            if (type.equals(1L)) {// Sedan
                surchargeByKm = initialAmount * 0.03;
            }
            if  (type.equals(2L)) {// Hatchback
                surchargeByKm = initialAmount * 0.03;
            }
            if (type.equals(3L)) {// SUV
                surchargeByKm = initialAmount * 0.05;
            }
            if (type.equals(4L)) {// pickup
                surchargeByKm = initialAmount * 0.05;
            }
            if (type.equals(5L)) {// Furgoneta
                surchargeByKm = initialAmount * 0.05;
            }
        }
        if (km > 12001 && km <= 25000) {
            if (type.equals(1L)) {// Sedan
                surchargeByKm = initialAmount * 0.07;
            }
            if (type.equals(2L)) {// Hatchback
                surchargeByKm = initialAmount * 0.07;
            }
            if (type.equals(3L)) {// SUV
                surchargeByKm = initialAmount * 0.09;
            }
            if (type.equals(4L)) {// pickup
                surchargeByKm = initialAmount * 0.09;
            }
            if (type.equals(5L)) {// Furgoneta
                surchargeByKm = initialAmount * 0.09;
            }
        }
        if (km > 25001 && km <= 40000) {
            if (type.equals(1L)) {// Sedan
                surchargeByKm = initialAmount * 0.12;
            }
            if (type.equals(2L)) {// Hatchback
                surchargeByKm = initialAmount * 0.12;
            }
            if (type.equals(3L)) {// SUV
                surchargeByKm = initialAmount * 0.12;
            }
            if (type.equals(4L)) {// pickup
                surchargeByKm = initialAmount * 0.12;
            }
            if (type.equals(5L)) {// Furgoneta
                surchargeByKm = initialAmount * 0.12;
            }
        }
        if (km > 40001) {
            if (type.equals(1L)) {// Sedan
                surchargeByKm = initialAmount * 0.2;
            }
            if (type.equals(2L)) {// Hatchback
                surchargeByKm = initialAmount * 0.2;
            }
            if (type.equals(3L)) {// SUV
                surchargeByKm = initialAmount * 0.2;
            }
            if (type.equals(4L)) {// pickup
                surchargeByKm = initialAmount * 0.2;
            }
            if (type.equals(5L)) {// Furgoneta
                surchargeByKm = initialAmount * 0.2;
            }
        }
    }
        return surchargeByKm;
    }

    // Recargo por Antiguëdad del vehículo
    // Método que calcula el recargo por antigüedad del auto
    public double getSurchargeByAge(Long carId) {
        double surchargeByAge = 0;
        double initialAmount = getInitialAmount(carId);
        RequestCar car = getCarById(carId);
        int year = car.getYear();
        Long type = car.getTypeId();
        // obtener fecha actual:
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        year = currentYear - year;
  
        if (year > 0 && year <= 5) {
            if (type.equals(1L)) {// Sedan
                surchargeByAge = initialAmount * 0;
            }
            if (type.equals(2L)) {// Hatchback
                surchargeByAge = initialAmount * 0;
            }
            if (type.equals(3L)) {// SUV
                surchargeByAge = initialAmount * 0;
            }
            if (type.equals(4L)) {// pickup
                surchargeByAge = initialAmount * 0;
            }
            if (type.equals(5L)) {// Furgoneta
                surchargeByAge = initialAmount * 0;
            }
        }
  
        if (year > 5 && year <= 10) {
            if (type.equals(1L)) {// Sedan
                surchargeByAge = initialAmount * 0.05;
            }
            if (type.equals(2L)) {// Hatchback
                surchargeByAge = initialAmount * 0.05;
            }
            if (type.equals(3L)) {// SUV
                 surchargeByAge = initialAmount * 0.07;
            }
            if (type.equals(4L)) {// pickup
                surchargeByAge = initialAmount * 0.07;
            }
            if (type.equals(5L)) {// Furgoneta
                surchargeByAge = initialAmount * 0.07;
            }
        }
        if (year > 10 && year <= 15) {
            if (type.equals(1L)) {// Sedan
                surchargeByAge = initialAmount * 0.09;
            }
            if (type.equals(2L)) {// Hatchback
                surchargeByAge = initialAmount * 0.09;
            }
            if (type.equals(3L)) {// SUV
                surchargeByAge = initialAmount * 0.11;
            }
            if (type.equals(4L)) {// pickup
                surchargeByAge = initialAmount * 0.11;
            }
            if (type.equals(5L)) {// Furgoneta
                surchargeByAge = initialAmount * 0.11;
            }
        } 
        if (year > 15) {
            if (type.equals(1L)) {// Sedan
                surchargeByAge = initialAmount * 0.15;
            }
            if (type.equals(2L)) {// Hatchback
                surchargeByAge = initialAmount * 0.15;
            }
            if (type.equals(3L)) {// SUV
                surchargeByAge = initialAmount * 0.2;
            }
            if (type.equals(4L)) {// pickup
                surchargeByAge = initialAmount * 0.2;
            }
            if (type.equals(5L)) {// Furgoneta
                surchargeByAge = initialAmount * 0.2;
            }
        }
        return surchargeByAge;
  }

    // Recargo por retraso en la recogida del auto
    // Método que calcula el recargo por retraso en la recogida del auto, se aplica
    // un 5% por día de retraso

    public double getSurchargeByDelay(Long carId, Date realExitDate) {
        double surchargeByDelay = 0;
        double initialAmount = getInitialAmount(carId);
        // obtiene repair por carId y realExitDate = null
        RepairEntity repair = repairService.getRepairsByCarIdAndRealExitDateIsNull(carId);
  
        // obtener exitDate de repair
        Date exitDate = repair.getExitDate();
        System.out.println("exitDate: " + exitDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(exitDate);
        int day = calendar.get(Calendar.DAY_OF_YEAR); // retorna el día de la semana como valor numérico (1 = domingo, 2// = lunes, 3 = martes, 4 = miércoles, 5 = jueves, 6 = viernes, 7// = sábado).
  
        // obtener realExitDate
        calendar.setTime(realExitDate);
        calendar.setTime(realExitDate);
        int realDay = calendar.get(Calendar.DAY_OF_YEAR);
  
        int delay = realDay - day;
        System.out.println("delay: " + delay);
  
        // OJO QUE DEBO AGREGAR LA CONDICIÓN DE SI ES DENTRO DEL MISMO AÑO, SI PASA A
        // SER OTRO AÑO HAY QUE CAMBIAR EL CÁLCULO
  
        if (delay > 0) {
            surchargeByDelay = initialAmount * 0.05 * delay;
            }
        System.out.println("surchargeByDelay: " + surchargeByDelay);
  
        return surchargeByDelay;
    }

    // Método que calcula el total de los recargos
    public double getTotalSurcharge(Long carId, double km, Date realExitDate) {
        double surchargeByKm = getSurchargeByKm(carId, km);
        System.out.println("surchargeByKm: " + surchargeByKm);
        double surchargeByAge = getSurchargeByAge(carId);
        System.out.println("surchargeByAge: " + surchargeByAge);
        double surchargeByDelay = getSurchargeByDelay(carId, realExitDate);
        System.out.println("surchargeByDelay: " + surchargeByDelay);
        double totalSurcharge = surchargeByKm + surchargeByAge + surchargeByDelay;
        System.out.println("totalSurcharge: " + totalSurcharge);
        return totalSurcharge;
    }

    // 2. Métodos para DISCOUNTS.....................

    // Método que calcula el descuento por cantidad de reparaciones que ha tenido el
    // auto
    public double getDiscountByNumberRepairs(Long carId) {
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------Entra a la función getDiscountByNumberRepairs ");
        double discountByNumberRepairs = 0;
        double initialAmount = getInitialAmount(carId);
        System.out.println("initialAmount: " + initialAmount);
        // obtener el engineId del auto de getCarById
        RequestCar car = getCarById(carId);
        System.out.println("car: " + car);
        Long engineId = car.getEngineId();
        System.out.println("engineId: " + engineId);
        // buscar en detalle, los que tengan = carId
        List<DetailEntity> details = getDetailsByCarId(carId);
        System.out.println("DETAILS de ese auto: " + details);
        //int repairsN = details.size();
        int repairsN = 0;
        // recorro esos y obtengo repairsN, y los sumo
        for (DetailEntity detail : details) {
            repairsN += detail.getRepairIds().size();
        }
        System.out.println("numberRepairs: " + repairsN);

        if (repairsN > 0 && repairsN <= 2) {
            System.out.println("Entra al if de repairsN > 0 && repairsN <= 2");
            if (engineId.equals(1L)) {// Gasolina
                discountByNumberRepairs = initialAmount * 0.05;
                System.out.println("discountByNumberRepairs: " + discountByNumberRepairs);
            }
            if (engineId.equals(2L)) {// Diésel
                discountByNumberRepairs = initialAmount * 0.07;
                System.out.println("discountByNumberRepairs: " + discountByNumberRepairs);
            }
            if (engineId.equals(3L)) {// Híbrido
                discountByNumberRepairs = initialAmount * 0.1;
                System.out.println("discountByNumberRepairs: " + discountByNumberRepairs);
            }
            if (engineId.equals(4L)) {// Eléctrico
                discountByNumberRepairs = initialAmount * 0.08;
                System.out.println("discountByNumberRepairs: " + discountByNumberRepairs);
            }
        } else if (repairsN >= 3 && repairsN <= 5) {
            System.out.println("Entra al if de repairsN >= 3 && repairsN <= 5");
            if (engineId.equals(1L)) {// Gasolina
                discountByNumberRepairs = initialAmount * 0.1;
            }
            if (engineId.equals(2L)) {// Diésel
                discountByNumberRepairs = initialAmount * 0.12;
            }
            if (engineId.equals(3L)) {// Híbrido
                discountByNumberRepairs = initialAmount * 0.15;
            }
            if (engineId.equals(4L)) {// Eléctrico
                discountByNumberRepairs = initialAmount * 0.13;
            }
        } else if (repairsN >= 6 && repairsN <= 9) {
            System.out.println("Entra al if de repairsN >= 6 && repairsN <= 9");
            if (engineId.equals(1L)) {// Gasolina
                discountByNumberRepairs = initialAmount * 0.15;
            }
            if (engineId.equals(2L)) {// Diésel
                discountByNumberRepairs = initialAmount * 0.17;
            }
            if (engineId.equals(3L)) {// Híbrido
                discountByNumberRepairs = initialAmount * 0.2;
            }
            if (engineId.equals(4L)) {// Eléctrico
                discountByNumberRepairs = initialAmount * 0.18;
            }
        } else if (repairsN >= 10) {
            System.out.println("Entra al if de repairsN >= 10");
            if (engineId.equals(1L)) {// Gasolina
                discountByNumberRepairs = initialAmount * 0.2;
            }
            if (engineId.equals(2L)) {// Diésel
                discountByNumberRepairs = initialAmount * 0.22;
            }
            if (engineId.equals(3L)) {// Híbrido
                discountByNumberRepairs = initialAmount * 0.25;
            }
            if (engineId.equals(4L)) {// Eléctrico
                discountByNumberRepairs = initialAmount * 0.23;
            }
        }
        System.out.println("discountByNumberRepairs: " + discountByNumberRepairs);
        return discountByNumberRepairs;
    }

    // Descuento por día de atención
    // Método que hace que un 10% de descuento si el auto ingresa un día Lunes o
    // Jueves entre 9:00 y 12:00
    public double getDiscountByDay(Long carId) {
        double discountByDay = 0;
        double initialAmount = getInitialAmount(carId);
        // obtiene el detail por carId y realExitDate = null
        DetailEntity detail = getDetailsByCarIdAndTotalAmountNull(carId);
        Date admissionDate = detail.getAdmissionDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(admissionDate);
        int day = calendar.get(Calendar.DAY_OF_WEEK); // retorna el día de la semana como valor numérico (1 = domingo, 2
        // = lunes, 3 = martes, 4 = miércoles, 5 = jueves, 6 = viernes, 7
        // = sábado).

        // obtiene la hora de admisión
        LocalTime admissionHour = detail.getAdmissionHour();
        int hour = admissionHour.getHour();

        if ((day == 2 || day == 5) && (hour >= 9 && hour <= 12)) {
            discountByDay = initialAmount * 0.1;
        }
        return discountByDay;
    }

    // Descuento por bono

    public double getDiscountByBonus(Long carId, Long selectedBonus) {
        double discountByBonus = 0;
        if (selectedBonus == 0) {
            return 0.0;
        }

        double initialAmount = getInitialAmount(carId);

        List<RequestBonus> bonuses = getBonuses();
        for (RequestBonus bonus : bonuses) {
            if (bonus.getId() == selectedBonus) {
                discountByBonus = initialAmount * bonus.getAmount();
            }
        }
        return discountByBonus;
    }

    // Método que calcula el total de los descuentos
    public double getTotalDiscount(Long carId, Long selectedBonus) {
        double discountByNumberRepairs = getDiscountByNumberRepairs(carId);
        System.out.println("discountByNumberRepairs: " + discountByNumberRepairs);
        double discountByDay = getDiscountByDay(carId);
        System.out.println("discountByDay: " + discountByDay);
        double discountByBonus = getDiscountByBonus(carId, selectedBonus);
        System.out.println("discountByBonus: " + discountByBonus);
        double totalDiscount = discountByNumberRepairs + discountByDay +
                discountByBonus;
        System.out.println("totalDiscount: " + totalDiscount);
        return totalDiscount;
    }

    // -------------------------------------------------------------------------------------------------------------

    // Método que buscar los montos de las reparaciones de un auto y las guarda en
    // un arreglo
    public List<Integer> getRepairAmounts(Long carId) {
        DetailEntity detail = getDetailsByCarIdAndTotalAmountNull(carId);
        System.out.println("Detail: " + detail);
        List<Long> repairIds = detail.getRepairIds();
        System.out.println("RepairIds: " + repairIds);
        List<RequestRepair> repairs = getRepairs();
        System.out.println("Repairs: " + repairs);
        // busco en repairs las que tengan id = repairIds
        List<Integer> repairAmounts = new ArrayList<>();
        for (int i = 0; i < repairIds.size(); i++) {
            for (int j = 0; j < repairs.size(); j++) {
                if (repairIds.get(i).equals(repairs.get(j).getId())) {
                    repairAmounts.add(repairs.get(j).getAmmount());
                }
                System.out.println("repairAmounts1: " + repairAmounts);
            }
            System.out.println("repairAmounts2: " + repairAmounts);
        }
        System.out.println("------------------------------------------------------------repairAmounts Final: " + repairAmounts);

        return repairAmounts;

    }

    // Método que suma los valores de repairAmounts
    public int getRepairAmountsSum(Long carId) {
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------........................................................................-.-.-.-.-.-.Entra a la función getRepairAmountsSum ");

        // busca el detail por carId y totalAmount == null
        DetailEntity detail = getDetailsByCarIdAndTotalAmountNull(carId);
        System.out.println("........................................................................-.-.-.-.-.-.detail: " + detail);

        // obtiene los montos de las reparaciones
        List<Integer> repairAmounts = detail.getRepairAmounts();
        System.out.println("........................................................................-.-.-.-.-.-.repairAmounts: " + repairAmounts);

        // suma los montos de las reparaciones
        int repairAmountsSum = 0;
        for (int i = 0; i < repairAmounts.size(); i++) {
            repairAmountsSum += repairAmounts.get(i);
        }
        System.out.println("repairAmountsSum: " + repairAmountsSum);
        return repairAmountsSum;
    }

    // Método que buscar los nombres de las reparaciones de un auto y las guarda en

    // un arreglo
    public List<String> getRepairNames(Long carId) {
        System.out.println(" --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------Entra a la función getRepairNames ");
        System.out.println("carId: " + carId);

        DetailEntity detail = getDetailsByCarIdAndTotalAmountNull(carId);
        System.out.println("Dentro de la fx getRepairNames , detail: " + detail);

        List<Long> repairIds = detail.getRepairIds();
        System.out.println("Dentro de la fx getRepairNames, repairIds: " +repairIds);

        List<RequestRepair> repairs = getRepairs();
        System.out.println("Dentro de la fx getRepairNames, repairs: " + repairs);

        List<String> repairNames = new ArrayList<>();
        System.out.println("Dentro de la fx getRepairNames, aquí ya crea la lista vacía");
        for (int i = 0; i < repairIds.size(); i++) {
            System.out.println("Dentro de la fx getRepairNames, aqupi ya entró al for de la fx getRepairNames");
            System.out.println("i: " + i);
            for (int j = 0; j < repairs.size(); j++) {
                System.out.println("j: " + j);
                System.out.println("repairIds.get(i): " + repairIds.get(i));
                System.out.println("Tipo de dato de repairIds.get(i): " + repairIds.get(i).getClass().getName());
                System.out.println("repairs.get(j).getId(): " + repairs.get(j).getId());
                System.out.println("Tipo de dato de repairs.get(j).getId(): " + repairs.get(j).getId().getClass().getName());
                //imprimir tipo de dato de repairIds.get(i)
                
                //imprimir tipo de dato de repairs.get(j).getId()
                
                if (repairIds.get(i).equals(repairs.get(j).getId())) {
                    System.out.println("repairIds.get(i): " + repairIds.get(i));
                    System.out.println("repairs.get(j).getId(): " + repairs.get(j).getId());
                    repairNames.add(repairs.get(j).getName());
                    }   
            }
        }
        System.out.println("repairNames: " + repairNames);
        return repairNames;
    }

    // Método que aplica los descuentos y recargos a un auto

    public double getFinalAmountPrevious(Long carId, double km, Date realExitDate, Long selectedBonus) {
        System.out.println(" -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- ENTRÓ A LA FX DE getFinalAmountPrevious ");

        double initialAmount = getRepairAmountsSum(carId);
        System.out.println("44444444444444444444444444444444 initialAmount: " + initialAmount);

        double discountByNumberRepairs = getDiscountByNumberRepairs(carId);
        System.out.println("44444444444444444444444444444444 discountByNumberRepairs: " + discountByNumberRepairs);

        double discountByDay = getDiscountByDay(carId);
        System.out.println("44444444444444444444444444444444 discountByDay: " + discountByDay);

        double discountByBonus = getDiscountByBonus(carId, selectedBonus);
        System.out.println("44444444444444444444444444444444 discountByBonus: " + discountByBonus);

        double surchargeByKm = getSurchargeByKm(carId, km);
        System.out.println("44444444444444444444444444444444 surchargeByKm: " + surchargeByKm);

        double surchargeByAge = getSurchargeByAge(carId);
        System.out.println("44444444444444444444444444444444 surchargeByAge: " + surchargeByAge);

        double surchargeByDelay = getSurchargeByDelay(carId, realExitDate);
        System.out.println("44444444444444444444444444444444 surchargeByDelay: " + surchargeByDelay);

        double finalAmount = initialAmount - discountByNumberRepairs - discountByDay
                - discountByBonus + surchargeByKm
                + surchargeByAge + surchargeByDelay;

        System.out.println("4444444444444444444444444444444444444444444444444444444444444444444444 finalAmount: " + finalAmount);
        return finalAmount;
    }

    // Método que calcula el IVA

    public double getIva(Long carId, double km, Date realExitDate, Long selectedBonus) {
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- Entra a la función getIva ");
        double finalAmount = getFinalAmountPrevious(carId, km, realExitDate,
                selectedBonus);
        double iva = finalAmount * 0.19;
        return iva;
    }

    // Método que calcula el monto final

    public double getFinalAmount(Long carId, double km, Date realExitDate, Long selectedBonus) {
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- ENTRÓ A LA FX DE getFinalAmount ");
        System.out.println(" ????? carId: " + carId);
        System.out.println(" ????? km: " + km);
        System.out.println(" ????? realExitDate: " + realExitDate);
        System.out.println(" ????? selectedBonus: " + selectedBonus);
        double finalAmount = getFinalAmountPrevious(carId, km, realExitDate,selectedBonus);
        System.out.println("????????????????????????????????????????????????????     finalAmount: " + finalAmount);
        double iva = getIva(carId, km, realExitDate, selectedBonus);
        System.out.println("?????????????????????????????????????????????????    iva: " + iva);
        double finalAmountWithIva = finalAmount + iva;
        System.out.println(" ????? finalAmountWithIva: " + finalAmountWithIva);
        return finalAmountWithIva;
    }

    // Método que verifica si un descuento es != 0, devuelve el nombre del descuento

    public List<String> getDiscountsNames(Long carId, Long selectedBonus) {
        System.out.println(" --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------  ENTRÓ A LA FX DE getDiscountsNames ");
        double discountByNumberRepairs = getDiscountByNumberRepairs(carId);
        System.out.println("+++++++++++++discountByNumberRepairs: " + discountByNumberRepairs);
        double discountByDay = getDiscountByDay(carId);
        System.out.println("+++++++++++++discountByDay: " + discountByDay);
        double discountByBonus = getDiscountByBonus(carId, selectedBonus);
        System.out.println("+++++++++++++discountByBonus: " + discountByBonus);
        // Crea lista vacía
        List<String> discountsNames = new ArrayList<>();

        if (discountByNumberRepairs != 0) {
            discountsNames.add("Descuento por número de reparaciones");
        }
        if (discountByDay != 0) {
            discountsNames.add("Descuento por día de ingreso");
        }
        if (discountByBonus != 0) {
            discountsNames.add("Descuento por bono agregado");
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------, SALE DE LA FX DE getDiscountsNames con ---> discountsNames: " + discountsNames);
        return discountsNames;
    }

    // Método que verifica si un descuento es != 0, devuelve el monto del descuento*

    public List<Double> getDiscountsAmounts(Long carId, Long selectedBonus) {
        double discountByNumberRepairs = getDiscountByNumberRepairs(carId);
        double discountByDay = getDiscountByDay(carId);
        double discountByBonus = getDiscountByBonus(carId, selectedBonus);
        // Crea lista vacía
        List<Double> discountsAmounts = new ArrayList<>();

        if (discountByNumberRepairs != 0) {
            discountsAmounts.add(discountByNumberRepairs);
        }
        if (discountByDay != 0) {
            discountsAmounts.add(discountByDay);
        }
        if (discountByBonus != 0) {
            discountsAmounts.add(discountByBonus);
        }
        return discountsAmounts;
    }

    // Método que verifica si un recargo es != 0, devuelve el nombre del recargo
    public List<String> getSurchargesNames(Long carId, double km, Date realExitDate, Long selectedBonus) {
        double surchargeByKm = getSurchargeByKm(carId, km);
        double surchargeByAge = getSurchargeByAge(carId);
        double surchargeByDelay = getSurchargeByDelay(carId, realExitDate);

        // Crea lista vacía
        List<String> surchargesNames = new ArrayList<>();

        if (surchargeByKm != 0) {
            surchargesNames.add("Recargo por kilometraje");
        }
        if (surchargeByAge != 0) {
            surchargesNames.add("Recargo por antigüedad del auto");
        }
        if (surchargeByDelay != 0) {
            surchargesNames.add("Recargo por retraso en la recogida");
        }
        return surchargesNames;
    }

    // Método que verifica si un recargo es != 0, devuelve el monto del recargo
    public List<Double> getSurchargesAmounts(Long carId, double km, Date realExitDate, Long selectedBonus) {
        double surchargeByKm = getSurchargeByKm(carId, km);
        double surchargeByAge = getSurchargeByAge(carId);
        double surchargeByDelay = getSurchargeByDelay(carId, realExitDate);

        // Crea lista vacía
        List<Double> surchargesAmounts = new ArrayList<>();

        if (surchargeByKm != 0) {
            surchargesAmounts.add(surchargeByKm);
        }
        if (surchargeByAge != 0) {
            surchargesAmounts.add(surchargeByAge);
        }
        if (surchargeByDelay != 0) {
            surchargesAmounts.add(surchargeByDelay);
        }
        return surchargesAmounts;
    }

    // updateDetailByCarId

    // función que convierte un string a Date
    public Date stringToDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateConverted = null;
        try {
            dateConverted = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateConverted;
    }

    

    public DetailEntity updateDetailByCarId(Long carId, double km, String realExitDate, Long selectedBonus) {
        System.out.println(".................................................   ENTRÓ A LA FX DE UPDATE DETAIL ");
        System.out.println("carId: " + carId);

        Date date = new Date();
        // Convierte el string a Date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = dateFormat.parse(realExitDate);
            System.out.println("Fecha en formato Date: " + date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DetailEntity detailToUpdate = getDetailsByCarIdAndTotalAmountNull(carId);
        System.out.println("------------detailToUpdate: " + detailToUpdate);

        System.out.println("Aquí inicia el obtener los nombres y montos  de los descuentos y recargos");
        List<String> repairNames = getRepairNames(carId);
        System.out.println("repairNames: " + repairNames);

        List<Integer> repairAmounts = getRepairAmounts(carId);
        System.out.println("repairAmounts: " + repairAmounts);

        List<String> discountNames = getDiscountsNames(carId, selectedBonus);
        System.out.println("discountNames: " + discountNames);

        List<Double> discountAmounts = getDiscountsAmounts(carId, selectedBonus);
        System.out.println("discountAmounts: " + discountAmounts);

        List<String> surchargeNames = getSurchargesNames(carId, km, date,selectedBonus);
        System.out.println("surchargeNames: " + surchargeNames);

        List<Double> surchargeAmounts = getSurchargesAmounts(carId, km, date,selectedBonus);
        System.out.println("surchargeAmounts: " + surchargeAmounts);

        detailToUpdate.setRepairNames(repairNames);
        System.out.println("AQUÍ DEBERÍA HABER SETEADO LOS NOMBRES DE LAS REPARACIONES ---> detailToUpdate.getRepairNames(): " + detailToUpdate.getRepairNames());
        detailToUpdate.setRepairAmounts(repairAmounts);
        System.out.println("AQUÍ DEBERÍA HABER SETEADO LOS MONTOS DE LAS REPARACIONES ---> detailToUpdate.getRepairAmounts(): " + detailToUpdate.getRepairAmounts());
        detailToUpdate.setDiscountNames(discountNames);
        System.out.println("AQUÍ DEBERÍA HABER SETEADO LOS NOMBRES DE LOS DESCUENTOS ---> detailToUpdate.getDiscountNames(): " + detailToUpdate.getDiscountNames());
        detailToUpdate.setDiscountAmounts(discountAmounts);
        System.out.println("AQUÍ DEBERÍA HABER SETEADO LOS MONTOS DE LOS DESCUENTOS ---> detailToUpdate.getDiscountAmounts(): " + detailToUpdate.getDiscountAmounts());
        detailToUpdate.setSurchargeNames(surchargeNames);
        System.out.println("AQUÍ DEBERÍA HABER SETEADO LOS NOMBRES DE LOS RECARGOS ---> detailToUpdate.getSurchargeNames(): " + detailToUpdate.getSurchargeNames());
        detailToUpdate.setSurchargeAmounts(surchargeAmounts);
        System.out.println("AQUÍ DEBERÍA HABER SETEADO LOS MONTOS DE LOS RECARGOS ---> detailToUpdate.getSurchargeAmounts(): " + detailToUpdate.getSurchargeAmounts());

        return updateDetail(detailToUpdate);
    }


    public DetailEntity updateDetailByCarId2(Long carId, double km, String realExitDate, Long selectedBonus) {
        System.out.println(".................................................   ENTRÓ A LA FX DE UPDATE DETAIL ");
        System.out.println("carId: " + carId);

        Date date = new Date();
        // Convierte el string a Date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = dateFormat.parse(realExitDate);
            System.out.println("Fecha en formato Date: " + date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DetailEntity detailToUpdate = getDetailsByCarIdAndTotalAmountNull(carId);
        System.out.println("------------detailToUpdate: " + detailToUpdate);


        int totalAmount = (int) getFinalAmount(carId, km, date, selectedBonus);
        System.out.println("totalAmount: " + totalAmount);

        detailToUpdate.setTotalAmount(totalAmount);

        return updateDetail(detailToUpdate);
    }
}
