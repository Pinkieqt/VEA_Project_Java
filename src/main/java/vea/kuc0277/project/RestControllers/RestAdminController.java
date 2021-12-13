package vea.kuc0277.project.RestControllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vea.kuc0277.project.models.Admin;
import vea.kuc0277.project.models.Driver;
import vea.kuc0277.project.services.AdminService;
import vea.kuc0277.project.services.DriverService;

import java.util.List;

@RestController
public class RestAdminController {
    @Autowired
    AdminService adminService;

    @GetMapping("/api/getAdmins")
    private List<Admin> getAllAdmins()
    {
        return adminService.getAll();
    }

    @GetMapping("/api/getAdmin/{id}")
    private Admin getAdmin(@PathVariable("id") int id)
    {
        return adminService.get(id);
    }

    @DeleteMapping("/api/deleteAdmin/{id}")
    private void deleteAdmin(@PathVariable("id") int id)
    {
        adminService.delete(id);
    }

    @PostMapping("/api/addAdmin")
    private int addAdmin(@RequestBody Admin admin)
    {
        if(admin.getName() != null && admin.getLogin() != null && admin.getPassword() != null && admin.getAge() > 0) {
            adminService.insert(admin);
            return admin.getId();
        }
        return 0;
    }


}
