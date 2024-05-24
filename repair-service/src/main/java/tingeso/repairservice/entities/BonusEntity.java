package tingeso.repairservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "bonus")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class BonusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String brand;
    private Long brandId;
    private int number;
    private int amount;

}
