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

    @Column(name = "invoiceNumber")
    private String invoiceNumber;

    @Column(name = "dateOfMake")
    private Date dateOfMake;

    @OneToMany(mappedBy = "invoiceId",cascade = CascadeType.ALL)
    private List<RepairsOnInvoice> RON;

    public Invoices(String invoiceNumber, Date dateOfMake, List<RepairsOnInvoice> RON) {
        this.invoiceNumber = invoiceNumber;
        this.dateOfMake = dateOfMake;
        this.RON = RON;
    }

    public Invoices() {
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
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

    public List<RepairsOnInvoice> getRON() {
        return RON;
    }

    public void setRON(List<RepairsOnInvoice> RON) {
        this.RON = RON;
    }
}
