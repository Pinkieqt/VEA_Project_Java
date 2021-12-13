package vea.kuc0277.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vea.kuc0277.project.converters.RouteConverter;
import vea.kuc0277.project.models.Admin;
import vea.kuc0277.project.models.Route;
import vea.kuc0277.project.services.AdminService;
import vea.kuc0277.project.services.RouteService;

@Controller
public class RouteController {
    @Autowired
    RouteService routeService;

    @Autowired
    RouteConverter routeConverter;

    //Adding route with converter
    @RequestMapping("/convertroute/{source}")
    public String convertRoute(@PathVariable("source") String source) {
        Route convertedRoute = routeConverter.convert(source);
        routeService.insert(convertedRoute);
        return "redirect:/";
    }

    //Adding route
    @GetMapping("/addroute")
    @Secured("ADMIN")
    public String showSignUpForm(Route route) {
        return "routes/add";
    }

    @PostMapping("/addroute")
    @Secured("ADMIN")
    public String addroute(Route route, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "routes/add";
        }
        routeService.insert(route);
        return "redirect:/";
    }

    //Editing route
    @GetMapping("/editroute/{id}")
    @Secured("ADMIN")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        try {
            Route route = routeService.get(id);
            model.addAttribute("route", route);
        }
        catch (Exception e){
            throw new IllegalArgumentException("Invalid route Id");
        }

        return "routes/edit";
    }

    @PostMapping("/updateroute/{id}")
    @Secured("ADMIN")
    public String updateRoute(@PathVariable("id") int id, Route route,
                             BindingResult result, Model model) {
        Route tmpRoute = routeService.get(id);
        if (result.hasErrors()) {
            tmpRoute.setId(id);
            return "admins/edit";
        }
        tmpRoute.setLength(route.getLength());
        tmpRoute.setName(route.getName());
        routeService.update(tmpRoute);
        return "redirect:/";
    }

    // Deleting route
    @GetMapping("/deleteroute/{id}")
    @Secured("ADMIN")
    public String deleteRoute(@PathVariable("id") int id, Model model) {
        try {
            routeService.delete(id);
            return "redirect:/";
        }
        catch (Exception e){
            throw new IllegalArgumentException("Invalid route Id");
        }
    }

}
