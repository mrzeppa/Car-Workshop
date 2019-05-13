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

    @ManyToOne
    @JoinColumn(name = "carMarkId")
    private CarMarks carMarks;

    @OneToMany(mappedBy = "carModels",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Cars> Cars;

    public List<com.javawebtutor.Models.Cars> getCars() {
        return Cars;
    }

    public CarModels() {
    }

    public CarModels(String modelName, CarMarks carMarks) {
        this.modelName = modelName;
        this.carMarks = carMarks;
    }

    public void setCars(List<com.javawebtutor.Models.Cars> cars) {
        Cars = cars;
    }

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

    public com.javawebtutor.Models.CarMarks getCarMarks() {
        return carMarks;
    }

    public void setCarMarks(com.javawebtutor.Models.CarMarks carMarks) {
        carMarks = carMarks;
    }
}
