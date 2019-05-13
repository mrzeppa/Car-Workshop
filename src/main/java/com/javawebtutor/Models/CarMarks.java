package com.javawebtutor.Models;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carmarks")
public class CarMarks {
    @Id
    @Column(name = "carMarkId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int carMarkId;

    @Column(name = "markName")
    private String markName;

    @OneToMany(mappedBy = "carMarks",fetch = FetchType.EAGER)
    private List<CarModels> carModels;

    public CarMarks(){}

    public CarMarks(String markName) {
        this.markName = markName;
    }

    public List<CarModels> getCarModels() {
        return carModels;
    }

    public void setCarModels(List<CarModels> carModels) {
        this.carModels = carModels;
    }

    public int getCarMarkId() {
        return carMarkId;
    }

    public void setCarMarkId(int carMarkId) {
        this.carMarkId = carMarkId;
    }

    public String getMarkName() {
        return markName;
    }

    public void setMarkName(String markName) {
        this.markName = markName;
    }
}
