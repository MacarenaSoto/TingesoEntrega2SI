package tingeso.carrepairservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.Date;
import java.time.LocalTime;
@Entity
@Table(name = "repair")
@Data
@NoArgsConstructor
@AllArgsConstructor


public class RepairEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private Date admissionDate;
    private LocalTime admissionHour;
    private Date exitDate ;
    private LocalTime exitHour;
    private Date realExitDate;
    private LocalTime realExitHour;
    private Long carId;
    private Long detailId;
    private double discountAmount;
    private double surchargeAmount;
    private double finalAmount;
    private double iva;
    private int numberRepairs;//dejar o quitar???? no es necesario sería un dato más no más



    
}