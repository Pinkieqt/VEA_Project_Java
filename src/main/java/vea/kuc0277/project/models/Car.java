package vea.kuc0277.project.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
//@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String manufacturer;
    @NotNull
    private String model;
    @NotNull
    private int mileage;

    @ManyToOne
    @JoinColumn(name = "person_id")
    @JsonBackReference
    private Driver owner;

    @Transient
    @Nullable
    @JsonBackReference
    public int ownerId;

    public Car() {
    }

    public Car(int id, String manufacturer, String model, int mileage, Driver owner) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.mileage = mileage;
        this.owner = owner;
        if (owner != null) {
            this.ownerId = owner.getId();
        }
    }

    public Car(String manufacturer, String model, int mileage, Driver owner) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.mileage = mileage;
        this.owner = owner;
        if (owner != null) {
            this.ownerId = owner.getId();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public Driver getOwner() {
        return owner;
    }

    public void setOwner(Driver owner) {
        this.owner = owner;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
}