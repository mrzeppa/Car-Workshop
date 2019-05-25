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
    private Long carModelId;

    @Column(name = "modelName")
    private String modelName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "carMarkId")
    private CarMarks carMarks;

    @OneToMany(mappedBy = "carModels",fetch = FetchType.EAGER)
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

    public Long getCarModelId() {
        return carModelId;
    }

    public void setCarModelId(Long carModelId) {
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