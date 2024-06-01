package tingeso.carrepairservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.Date;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "detail")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class DetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private Long carId;
    private int numberRepairs;

    //lista con los id de las reparaciones
    private List<Long> repairIds;

    //lista con los nombres de las reparaciones
    private List<String> repairNames;

    //Lista con los amount de las reparaciones
    private List<Integer> repairAmounts;

    //Lista con los nombres de los descuentos
    private List<String> discountNames;

    //Lista con los amount de los descuentos
    private List<Double> discountAmounts;

    //Lista con los nombres de los recargos
    private List<String> surchargeNames;

    //Lista con los amount de los recargos
    private List<Double> surchargeAmounts;

    private int totalAmount;
    private Date admissionDate;
    private LocalTime admissionHour;
    private Date exitDate;
    private Double iva;

}
