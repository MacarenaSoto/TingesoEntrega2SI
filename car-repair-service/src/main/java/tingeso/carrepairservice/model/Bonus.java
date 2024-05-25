package tingeso.carrepairservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Bonus {

    private Long id;
    private String brand;
    private Long brandId;
    private int number;
    private int amount;

}
