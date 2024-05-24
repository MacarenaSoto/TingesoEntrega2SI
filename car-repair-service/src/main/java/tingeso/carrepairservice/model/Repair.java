package tingeso.carrepairservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Repair {

    private Long id;
    private String name;
    private int amount;
    private Long engineId;

}
