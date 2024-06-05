package tingeso.carrepairservice.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestBonus {
    private Long id;
    private String brand;
    private Long brandId;
    private int number;
    private int amount;

}
