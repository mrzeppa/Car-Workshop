package com.javawebtutor.Models;


import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Cars {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "carId")
    private int carId;

    @Column(name = "carModelId")
    private int carModelId;

    @Column(name = "course")
    private String course;

    @Column(name = "userId")
    private int userId;

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getCarModelId() {
        return carModelId;
    }

    public void setCarModelId(int carModelId) {
        this.carModelId = carModelId;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
