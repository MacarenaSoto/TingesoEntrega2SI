package tingeso.carservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepairDto {

    private Long id;
    private String name;
    private int ammount;
    private Long engineId;

}
