package com.javawebtutor.Models;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CarModels")
public class CarModels {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "carModelId")
    private int carModelId;

    @Column(name = "modelName")
    private String modelName;

    @Column(name = "carMarkId")
    private int carMarkId;



    public int getCarModelId() {
        return carModelId;
    }

    public void setCarModelId(int carModelId) {
        this.carModelId = carModelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getCarMarkId() {
        return carMarkId;
    }

    public void setCarMarkId(int carMarkId) {
        this.carMarkId = carMarkId;
    }
}
