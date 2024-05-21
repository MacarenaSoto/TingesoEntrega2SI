package tingeso.carrepairservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "appliedSurcharge")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppliedSurchargeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private double amount;
    private String surchargeName;
    private Long carId;
    private Long repairId;

}
