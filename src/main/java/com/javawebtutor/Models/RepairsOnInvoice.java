package com.javawebtutor.Models;

import javax.persistence.*;


@Entity
@Table(name = "repairsoninvoice")
public class RepairsOnInvoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RONId")
    private int RONId;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "invoiceId")
    private Invoices invoiceId;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "repairId")
    private Repairs repairId;

    public RepairsOnInvoice(Invoices invoiceId, Repairs repairId) {
        this.invoiceId = invoiceId;
        this.repairId = repairId;
    }

    public RepairsOnInvoice() {
    }

    public int getRONId() {
        return RONId;
    }

    public void setRONId(int RONId) {
        this.RONId = RONId;
    }

    public Invoices getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Invoices invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Repairs getRepairId() {
        return repairId;
    }

    public void setRepairId(Repairs repairId) {
        this.repairId = repairId;
    }
}
