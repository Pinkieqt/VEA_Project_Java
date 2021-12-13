package vea.kuc0277.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import vea.kuc0277.project.models.Admin;
import vea.kuc0277.project.models.Driver;
import vea.kuc0277.project.services.AdminService;
import vea.kuc0277.project.services.CarService;
import vea.kuc0277.project.services.DriverService;

@Controller
public class AdminController {
    @Autowired
    AdminService adminService;

    //Adding admin
    @GetMapping("/addadmin")
    @Secured("ADMIN")
    public String showSignUpForm(Admin admin) {
        return "admins/add";
    }

    @PostMapping("/addadmin")
    @Secured("ADMIN")
    public String addAdmin(Admin admin, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "admins/add";
        }
        adminService.insert(admin);
        return "redirect:/";
    }

    //Editing admin
    @GetMapping("/editadmin/{id}")
    @Secured("ADMIN")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        try {
            Admin person = adminService.get(id);
            model.addAttribute("person", person);
        }
        catch (Exception e){
            throw new IllegalArgumentException("Invalid user Id");
        }

        return "admins/edit";
    }

    @PostMapping("/updateadmin/{id}")
    @Secured("ADMIN")
    public String updateAdmin(@PathVariable("id") int id, Admin person,
                             BindingResult result, Model model) {
        Admin user = adminService.get(id);
        if (result.hasErrors()) {
            person.setId(id);
            return "admins/edit";
        }
        user.setAge(person.getAge());
        user.setName(person.getName());
        adminService.update(user);
        return "redirect:/";
    }

    // Deleting admin
    @GetMapping("/deleteadmin/{id}")
    @Secured("ADMIN")
    public String deleteUser(@PathVariable("id") int id, Model model) {
        try {
            adminService.delete(id);
            return "redirect:/";
        }
        catch (Exception e){
            throw new IllegalArgumentException("Invalid user Id");
        }
    }

}
