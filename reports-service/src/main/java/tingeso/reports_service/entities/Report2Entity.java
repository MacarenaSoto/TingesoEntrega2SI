package tingeso.reports_service.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report2Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private List<String> repairs;
    private List<Long> repairIds;
    private List<String> carType;
    private List<Integer> nCarsByRepairs;
    private List<Double> totalAmountsByRepairs;

}
