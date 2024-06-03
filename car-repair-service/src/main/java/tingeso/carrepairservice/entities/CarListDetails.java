package tingeso.carrepairservice.entities;

import java.util.Date;
import java.util.List;

public class CarListDetails {

    private Long id;
    private String patent;
    private Date admissionDate;
    private Date exitDate;
    private Date realExitDate;
    private List<String> repairs;
    private List<Integer> repairAmounts;
    private double totalRepairAmounts;
    private List<String> discounts;
    private List<Double> discountAmounts;
    private double totalDiscountAmounts;
    private List<String> surcharges;
    private List<Double> surchargeAmounts;
    private double totalSurchargeAmounts;
    private double finalAmount;

    //Constructor vacío
    public CarListDetails() {
    }

    //Constructor con parámetros
    public CarListDetails(Long id, String patent, Date admissionDate, Date exitDate, Date realExitDate, List<String> repairs,  List<Integer> repairAmounts, double totalRepairAmounts, List<String> discounts, List<Double> discountAmounts, double totalDiscountAmounts, List<String> surcharges, List<Double> surchargeAmounts, double totalSurchargeAmounts, double finalAmount) {
        this.id = id;
        this.patent = patent;
        this.admissionDate = admissionDate;
        this.exitDate = exitDate;
        this.realExitDate = realExitDate;
        this.repairs = repairs;
        this.repairAmounts = repairAmounts;
        this.totalRepairAmounts = totalRepairAmounts;
        this.discounts = discounts;
        this.discountAmounts = discountAmounts;
        this.totalDiscountAmounts = totalDiscountAmounts;
        this.surcharges = surcharges;
        this.surchargeAmounts = surchargeAmounts;
        this.totalSurchargeAmounts = totalSurchargeAmounts;
        this.finalAmount = finalAmount;
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
    public Date getExitDate() {
        return exitDate;
    }
    public void setExitDate(Date exitDate) {
        this.exitDate = exitDate;
    }
    public Date getRealExitDate() {
        return realExitDate;
    }
    public void setRealExitDate(Date realExitDate) {
        this.realExitDate = realExitDate;
    }
    public List<String> getRepairs() {
        return repairs;
    }
    public void setRepairs(List<String> repairs) {
        this.repairs = repairs;
    }
    public List<Integer> getRepairAmounts() {
        return repairAmounts;
    }
    public void setRepairAmounts(List<Integer> repairAmounts) {
        this.repairAmounts = repairAmounts;
    }
    public double getTotalRepairAmounts() {
        return totalRepairAmounts;
    }
    public void setTotalRepairAmounts(double totalRepairAmounts) {
        this.totalRepairAmounts = totalRepairAmounts;
    }
    public List<String> getDiscounts() {
        return discounts;
    }
    public void setDiscounts(List<String> discounts) {
        this.discounts = discounts;
    }
    public List<Double> getDiscountAmounts() {
        return discountAmounts;
    }
    public void setDiscountAmounts(List<Double> discountAmounts) {
        this.discountAmounts = discountAmounts;
    }
    public double getTotalDiscountAmounts() {
        return totalDiscountAmounts;
    }
    public void setTotalDiscountAmounts(double totalDiscountAmounts) {
        this.totalDiscountAmounts = totalDiscountAmounts;
    }
    public List<String> getSurcharges() {
        return surcharges;
    }
    public void setSurcharges(List<String> surcharges) {
        this.surcharges = surcharges;
    }
    public List<Double> getSurchargeAmounts() {
        return surchargeAmounts;
    }
    public void setSurchargeAmounts(List<Double> surchargeAmounts) {
        this.surchargeAmounts = surchargeAmounts;
    }
    public double getTotalSurchargeAmounts() {
        return totalSurchargeAmounts;
    }
    public void setTotalSurchargeAmounts(double totalSurchargeAmounts) {
        this.totalSurchargeAmounts = totalSurchargeAmounts;
    }
    public double getFinalAmount() {
        return finalAmount;
    }
    public void setFinalAmount(double finalAmount) {
        this.finalAmount = finalAmount;
    }



}


