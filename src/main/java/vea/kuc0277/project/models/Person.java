package vea.kuc0277.project.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
//@Table(name = "persons")
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    protected String login;
    @NotNull
    protected String name;
    @Column(name = "password", nullable = false)
    protected String password;
    @NotNull
    protected int age;

    public Person() {
    }

    public Person(int id, String login, String name, String password, int age) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.password = password;
        this.age = age;
    }

    public Person(String login, String name, String password, int age) {
        this.login = login;
        this.name = name;
        this.password = password;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
