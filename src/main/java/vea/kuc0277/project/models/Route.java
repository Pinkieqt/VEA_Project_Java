package vea.kuc0277.project.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
//@Table(name = "routes")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String name;
    @NotNull
    private int length;

    public Route() {
    }

    public Route(String name, int length) {
        this.name = name;
        this.length = length;
    }

    public Route(int id, String name, int length) {
        this.id = id;
        this.name = name;
        this.length = length;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}