package tingeso.carrepairservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    private String patent;
    private String model;
    private int year;
    private int seats;
    private Long brandId;
    private Long typeId;
    private Long engineId;

}
