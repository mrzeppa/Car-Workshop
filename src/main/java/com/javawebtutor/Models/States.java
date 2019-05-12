package com.javawebtutor.Models;

import javax.persistence.*;

@Entity
@Table(name = "states")
public class States {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stateId")
    private int stateId;

    @Column(name = "name")
    private String name;

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
