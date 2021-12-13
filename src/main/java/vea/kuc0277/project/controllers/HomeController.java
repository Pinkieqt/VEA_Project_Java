package vea.kuc0277.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vea.kuc0277.project.models.*;
import vea.kuc0277.project.services.*;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CarService carService;
    @Autowired
    DriverService driverService;
    @Autowired
    AdminService adminService;
    @Autowired
    RouteService routeService;
    @Autowired
    PersonService personService;

    @GetMapping("/")
    public String homepage(Model model, Principal principal) {
        Person person = personService.findByLogin(principal.getName());
        String role = "DRIVER";
        if (person instanceof Admin){
            role = "ADMIN";
        }

        List<Car> cars = carService.getAll();
        List<Driver> drivers = driverService.getAll();
        List<Admin> admins = adminService.getAll();
        List<Route> routes = routeService.getAll();

        switch (role) {
        case "ADMIN":
            //Admin Dashboard
            model.addAttribute("cars", cars);
            model.addAttribute("drivers", drivers);
            model.addAttribute("admins", admins);
            model.addAttribute("routes", routes);
            return "dashboard/admindash";
        case "DRIVER":
            //Driver Dashboard
            model.addAttribute("cars", cars);
            model.addAttribute("drivers", drivers);
            model.addAttribute("routes", routes);
            return "dashboard/driverdash";
        }



        return "dashboard/admindash";
    }

}
