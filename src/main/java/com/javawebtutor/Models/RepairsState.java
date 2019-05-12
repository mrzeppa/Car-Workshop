package com.javawebtutor.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "repairsstate")
public class RepairsState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "repairStateId")
    private int repairStateId;

    @Column(name = "repairId")
    private int repairId;

    @Column(name = "dateOfStart")
    private Date dateOfStart;

    @Column(name = "dateOfEnd")
    private Date dateOfEnd;

    @Column(name = "stateId")
    private int stateId;

    public int getRepairStateId() {
        return repairStateId;
    }

    public void setRepairStateId(int repairStateId) {
        this.repairStateId = repairStateId;
    }

    public int getRepairId() {
        return repairId;
    }

    public void setRepairId(int repairId) {
        this.repairId = repairId;
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

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }
}
