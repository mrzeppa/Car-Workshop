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
    private Repairs repairId;

    @Column(name = "dateOfStart")
    private Date dateOfStart;

    @Column(name = "dateOfEnd")
    private Date dateOfEnd;

    @ManyToOne
    @JoinColumn(name = "stateId")
    private States states;


    public RepairsState() {
    }

    public RepairsState(Repairs repairId, Date dateOfStart, Date dateOfEnd, States states) {
        this.repairId = repairId;
        this.dateOfStart = dateOfStart;
        this.dateOfEnd = dateOfEnd;
        this.states = states;
    }

    public Repairs getRepairId() {
        return repairId;
    }

    public void setRepairId(Repairs repairId) {
        this.repairId = repairId;
    }

    public States getStates() {
        return states;
    }

    public void setStates(States states) {
        this.states = states;
    }

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