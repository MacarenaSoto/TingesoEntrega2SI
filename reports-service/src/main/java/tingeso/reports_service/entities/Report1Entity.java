package tingeso.reports_service.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor

public class Report1Entity {

    private String carType; 
    private Long typeId;
    private String repairName;
    private Long repairId;
    private Integer nRepairedCars;
    private Double amountRepairedCars;





}
