package com.javawebtutor.Models;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "repairs")
public class Repairs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "repairId")
    private int repairId;

    @Column(name = "repairCauses")
    private String repairCauses;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "carId")
    private Cars carId;

    @Column(name = "price")
    private int price;

//    @ManyToOne
//    @JoinColumn(name = "repairId", insertable = false, updatable = false)
//    private Invoices invoices;

    @OneToMany(mappedBy = "repairId",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<RepairsState> RepairState;

    @OneToMany(mappedBy = "repairId", cascade = CascadeType.ALL)
    private List<RepairsOnInvoice> RON;


    public Repairs() {
    }

    public Repairs(String repairCauses, Cars carId) {
        this.repairCauses = repairCauses;
        this.carId = carId;
    }

    public List<RepairsOnInvoice> getRON() {
        return RON;
    }

    public void setRON(List<RepairsOnInvoice> RON) {
        this.RON = RON;
    }

    public Cars getCarId() {
        return carId;
    }

    public void setCarId(Cars carId) {
        this.carId = carId;
    }

    public List<RepairsState> getRepairState() {
        return RepairState;
    }

    public void setRepairState(List<RepairsState> repairState) {
        RepairState = repairState;
    }

    public int getRepairId() {
        return repairId;
    }

    public void setRepairId(int repairId) {
        this.repairId = repairId;
    }

    public String getRepairCauses() {
        return repairCauses;
    }

    public void setRepairCauses(String repairCauses) {
        this.repairCauses = repairCauses;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
