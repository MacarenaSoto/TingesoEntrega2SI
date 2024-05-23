package tingeso.carrepairservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Repair {

    private String name;
    private int ammount;
    private Long engineId;

}
