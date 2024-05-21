package tingeso.carrepairservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "appliedDiscounts")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class AppliedDiscountsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private double amount;
    private String discountName;
    private Long carId;
    private Long repairId;//ver si esto sigue funcionandome como el de antes, porque antes este era para cada reparación individual,pero ahora sería como para todas las reparaciones que tenga??


}
