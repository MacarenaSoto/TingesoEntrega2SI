package tingeso.carrepairservice.entities;

import java.util.Date;
import java.time.LocalTime;
;
public class CarList {

    private Long id;
    private String patent;
    private Date admissionDate;
    private LocalTime admissionHour;
    private Date exitDate;
    private LocalTime exitHour;
    private Date realExitDate;
    private LocalTime realExitHour;
    private Long detailId;
    private double discountAmounts;
    private double surchargeAmounts;
    private double totalAmounts;



    public CarList() {
    }

    //Constructor con parámetros
    public CarList(Long id, String patent, Date admissionDate, LocalTime admissionHour, Date exitDate, LocalTime exitHour, Date realExitDate, LocalTime realExitHour, Long detailId, double discountAmounts, double surchargeAmounts, double totalAmounts) {
        this.id = id;
        this.patent = patent;
        this.admissionDate = admissionDate;
        this.admissionHour = admissionHour;
        this.exitDate = exitDate;
        this.exitHour = exitHour;
        this.realExitDate = realExitDate;
        this.realExitHour = realExitHour;
        this.detailId = detailId;
        this.discountAmounts = discountAmounts;
        this.surchargeAmounts = surchargeAmounts;
        this.totalAmounts = totalAmounts;
    }

    //Getters y Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getPatent() {
        return patent;
    }
    public void setPatent(String patent) {
        this.patent = patent;
    }
    public Date getAdmissionDate() {
        return admissionDate;
    }
    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }
    public LocalTime getAdmissionHour() {
        return admissionHour;
    }
    public void setAdmissionHour(LocalTime admissionHour) {
        this.admissionHour = admissionHour;
    }
    public Date getExitDate() {
        return exitDate;
    }
    public void setExitDate(Date exitDate) {
        this.exitDate = exitDate;
    }
    public LocalTime getExitHour() {
        return exitHour;
    }
    public void setExitHour(LocalTime exitHour) {
        this.exitHour = exitHour;
    }
    public Date getRealExitDate() {
        return realExitDate;
    }
    public void setRealExitDate(Date realExitDate) {
        this.realExitDate = realExitDate;
    }
    public LocalTime getRealExitHour() {
        return realExitHour;
    }
    public void setRealExitHour(LocalTime realExitHour) {
        this.realExitHour = realExitHour;
    }
    public Long getDetailId() {
        return detailId;
    }
    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }
    public double getDiscountAmounts() {
        return discountAmounts;
    }
    public void setDiscountAmounts(double discountAmounts) {
        this.discountAmounts = discountAmounts;
    }
    public double getSurchargeAmounts() {
        return surchargeAmounts;
    }
    public void setSurchargeAmounts(double surchargeAmounts) {
        this.surchargeAmounts = surchargeAmounts;
    }
    public double getTotalAmounts() {
        return totalAmounts;
    }
    public void setTotalAmounts(double totalAmounts) {
        this.totalAmounts = totalAmounts;
    }


}