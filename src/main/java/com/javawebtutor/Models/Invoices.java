package com.javawebtutor.Models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "invoices")
public class Invoices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoiceId")
    private int invoiceId;

    @Column(name = "repairId")
    private int repairId;

    @Column(name = "invoiceNumber")
    private String invoiceNumber;

    @Column(name = "dateOfMake")
    private Date dateOfMake;

    @Column(name = "description")
    private String description;

//    @OneToMany(mappedBy = "invoices",fetch = FetchType.EAGER)
//    private List<Repairs> repairs;

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getRepairId() {
        return repairId;
    }

    public void setRepairId(int repairId) {
        this.repairId = repairId;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Date getDateOfMake() {
        return dateOfMake;
    }

    public void setDateOfMake(Date dateOfMake) {
        this.dateOfMake = dateOfMake;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
