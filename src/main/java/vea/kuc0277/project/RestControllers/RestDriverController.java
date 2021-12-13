package vea.kuc0277.project.RestControllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vea.kuc0277.project.models.Car;
import vea.kuc0277.project.models.Driver;
import vea.kuc0277.project.services.CarService;
import vea.kuc0277.project.services.DriverService;

import java.util.List;

@RestController
public class RestDriverController {
    @Autowired
    DriverService driverService;

    @GetMapping("/api/getDrivers")
    private List<Driver> getAllDrivers()
    {
        return driverService.getAll();
    }

    @GetMapping("/api/getDriver/{id}")
    private Driver getDriver(@PathVariable("id") int id)
    {
        return driverService.get(id);
    }

    @DeleteMapping("/api/deleteDriver/{id}")
    private void deleteDriver(@PathVariable("id") int id)
    {
        driverService.delete(id);
    }

    @PostMapping("/api/addDriver")
    private int addDriver(@RequestBody Driver driver)
    {
        if(driver.getName() != null && driver.getLogin() != null && driver.getPassword() != null && driver.getAge() > 0) {
            driverService.insert(driver);
            return driver.getId();
        }
        return 0;
    }


}
