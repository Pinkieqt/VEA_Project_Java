package vea.kuc0277.project.models;

import javax.persistence.*;
import java.util.List;

@Entity
//@Table(name = "drivers")
public class Driver extends Person {
    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    //@JsonBackReference
    public List<Car> cars;

    public Driver() {
        super();
    }



    public Driver(int id, String login, String name, String password, int age) {
        super(id, login, name, password, age);
    }

    public Driver(String login, String name, String password, int age) {
        super(login, name, password, age);
    }

    public Driver(int id, String login, String name, String password, int age, List<Car> cars) {
        super(id, login, name, password, age);
        this.cars = cars;
    }

    public Driver(String login, String name, String password, int age, List<Car> cars) {
        super(login, name, password, age);
        this.cars = cars;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
