package tingeso.reports_service.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class Report1Entity {

    private Long id;

    private List<String> repairs;
    private List<Long> repairIds;
    private List<String> carType;
    private List<Long> typesIds;
    private List<Integer> nCarsByRepairs;
    private List<Double> totalAmountsByRepairs;





}
