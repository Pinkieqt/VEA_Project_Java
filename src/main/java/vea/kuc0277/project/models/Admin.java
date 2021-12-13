package vea.kuc0277.project.models;

import javax.persistence.Entity;

@Entity
//@Table(name="admins")
public class Admin extends Person {
    public Admin() {
    }

    public Admin(int id, String login, String name, String password, int age) {
        super(id, login, name, password, age);
    }

    public Admin(String login, String name, String password, int age) {
        super(login, name, password, age);
    }
}