package vea.kuc0277.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import vea.kuc0277.project.models.Car;
import vea.kuc0277.project.models.Driver;
import vea.kuc0277.project.services.CarService;
import vea.kuc0277.project.services.DriverService;

@Controller
public class DriverController {
    @Autowired
    CarService carService;

    @Autowired
    DriverService driverService;

    //Adding driver
    @GetMapping("/adddriver")
    @Secured("ADMIN")
    public String showSignUpForm(Driver driver) {
        return "drivers/add";
    }

    @PostMapping("/adddriver")
    @Secured("ADMIN")
    public String addUser(Driver driver, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "drivers/add";
        }
        driverService.insert(driver);
        return "redirect:/";
    }

    //Editing driver
    @GetMapping("/editdriver/{id}")
    @Secured("ADMIN")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        try {
            Driver person = driverService.get(id);
            model.addAttribute("person", person);
        }
        catch (Exception e){
            throw new IllegalArgumentException("Invalid user Id");
        }

        return "drivers/edit";
    }

    @PostMapping("/updatedriver/{id}")
    @Secured("ADMIN")
    public String updateUser(@PathVariable("id") int id, Driver person,
                             BindingResult result, Model model) {
        Driver user = driverService.get(id);
        if (result.hasErrors()) {
            person.setId(id);
            return "drivers/edit";
        }
        user.setAge(person.getAge());
        user.setName(person.getName());
        driverService.update(user);
        return "redirect:/";
    }

    // Deleting user
    @GetMapping("/deletedriver/{id}")
    @Secured("ADMIN")
    public String deleteUser(@PathVariable("id") int id, Model model) {
        try {
            driverService.delete(id);
            return "redirect:/";
        }
        catch (Exception e){
            throw new IllegalArgumentException("Invalid user Id");
        }
    }

}
