package com.javawebtutor.Models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "repairsstate")
public class RepairsState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "repairStateId")
    private int repairStateId;

    @ManyToOne
    @JoinColumn(name = "repairId")
    private Address repairId;

    @Column(name = "dateOfStart")
    private Date dateOfStart;

    @Column(name = "dateOfEnd")
    private Date dateOfEnd;

    @ManyToOne
    @JoinColumn(name = "stateId")
    private States states;


    public int getRepairStateId() {
        return repairStateId;
    }

    public void setRepairStateId(int repairStateId) {
        this.repairStateId = repairStateId;
    }


    public Date getDateOfStart() {
        return dateOfStart;
    }

    public void setDateOfStart(Date dateOfStart) {
        this.dateOfStart = dateOfStart;
    }

    public Date getDateOfEnd() {
        return dateOfEnd;
    }

    public void setDateOfEnd(Date dateOfEnd) {
        this.dateOfEnd = dateOfEnd;
    }

}
