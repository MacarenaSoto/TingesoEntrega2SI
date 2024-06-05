package tingeso.carrepairservice.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestCar {

    Long id;
    String patent;
    String model;
    int year;
    int seats;
    Long brandId;
    Long typeId;
    Long engineId;


}
