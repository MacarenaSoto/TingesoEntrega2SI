package tingeso.reports_service.request;

import java.time.LocalTime;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RequestCarRepairs {
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
    private int numberRepairs;



}
