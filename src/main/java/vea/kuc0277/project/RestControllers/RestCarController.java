package vea.kuc0277.project.RestControllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vea.kuc0277.project.models.Car;
import vea.kuc0277.project.services.CarService;

import java.util.List;

@RestController
public class RestCarController {
    @Autowired
    CarService carService;

    @GetMapping("/api/getCars")
    private List<Car> getAllCars()
    {
        return carService.getAll();
    }

    @GetMapping("/api/getCar/{id}")
    private Car getCar(@PathVariable("id") int id)
    {
        return carService.get(id);
    }

    @DeleteMapping("/api/deleteCar/{id}")
    private void deleteCar(@PathVariable("id") int id)
    {
        carService.delete(id);
    }

    @PostMapping(path="/api/addCar", consumes={"application/json"})
    private int addCar(@RequestBody Car car)
    {
        if(car.getManufacturer() != null && car.getModel() != null && car.getMileage() > 0 && car.getOwnerId() > 0) {
            carService.insert(car);
            return car.getId();
        }
        return 0;
    }


}
