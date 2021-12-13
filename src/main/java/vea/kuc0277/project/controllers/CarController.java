package vea.kuc0277.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vea.kuc0277.project.models.Car;
import vea.kuc0277.project.models.Driver;
import vea.kuc0277.project.services.CarService;
import vea.kuc0277.project.services.DriverService;

import java.util.List;

@Controller
public class CarController {
    @Autowired
    CarService carService;
    @Autowired
    DriverService driverService;

    //Adding car
    @GetMapping("/addcar")
    @Secured("ADMIN")
    public String showCarSignForm(Car car, Model model) {
        try {
            List<Driver> persons = driverService.getAll();
            model.addAttribute("persons", persons);
        }
        catch (Exception e){
            throw new IllegalArgumentException("Invalid car Id");
        }

        return "cars/add";
    }

    @PostMapping("/addcar")
    @Secured("ADMIN")
    public String addCar(Car car, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "cars/add";
        }
        if(car.getOwnerId() > 0) {
            carService.insert(car);
        }
        return "redirect:/";
    }

    //Editing car
    @GetMapping("/editcar/{id}")
    @Secured("ADMIN")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        try {
            Car car = carService.get(id);
            model.addAttribute("car", car);
            List<Driver> persons = driverService.getAll();
            model.addAttribute("persons", persons);
        }
        catch (Exception e){
            throw new IllegalArgumentException("Invalid car Id");
        }

        return "cars/edit";
    }

    @PostMapping("/updatecar/{id}")
    @Secured("ADMIN")
    public String updateCar(@PathVariable("id") int id, Car car,
                            BindingResult result, Model model) {
        Car tmpCar = carService.get(id);
        if (result.hasErrors()) {
            return "cars/edit";
        }
        tmpCar.setManufacturer(car.getManufacturer());
        tmpCar.setMileage(car.getMileage());
        tmpCar.setModel(car.getModel());
        tmpCar.setOwner(car.getOwner());
        tmpCar.setOwnerId(car.getOwnerId());
        carService.update(tmpCar);
        return "redirect:/";
    }

    // Deleting car
    @GetMapping("/deletecar/{id}")
    @Secured("ADMIN")
    public String deleteCar(@PathVariable("id") int id, Model model) {
        try {
            carService.delete(id);
            return "redirect:/";
        }
        catch (Exception e){
            throw new IllegalArgumentException("Invalid car Id");
        }
    }

}
