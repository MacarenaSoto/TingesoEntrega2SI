package tingeso.reports_service.request;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RequestDetail {

    private Long id;

    private Long carId;
    private int numberRepairs;
    private List<Long> repairIds;
    private List<String> repairNames;
    private List<Integer> repairAmounts;
    private List<String> discountNames;
    private List<Double> discountAmounts;
    private List<String> surchargeNames;
    private List<Double> surchargeAmounts;

    private int totalAmount;
    private Date admissionDate;
    private LocalTime admissionHour;
    private Date exitDate;
    private Double iva;



}
