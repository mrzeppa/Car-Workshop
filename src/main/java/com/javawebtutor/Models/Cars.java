package com.javawebtutor.Models;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cars")
public class Cars {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "carId")
    private int cars;

    @ManyToOne
    @JoinColumn(name = "carModelId")
    private CarModels carModels;

    @Column(name = "course")
    private String course;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Users users;


    @OneToMany(mappedBy = "carId",fetch = FetchType.EAGER)
    private List<Repairs> repairs;

    public CarModels getCarModels() {
        return carModels;
    }

    public void setCarModels(CarModels carModels) {
        this.carModels = carModels;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }


    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

}
