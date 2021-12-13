/*
package vea.kuc0277.project.services.RepoServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vea.kuc0277.project.models.Car;
import vea.kuc0277.project.repositories.CarRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RepoCarService {
    @Autowired
    CarRepository carRepository;

    public List<Car> getAllCars(){
        List<Car> cars = new ArrayList<>();
        carRepository.findAll().forEach(car -> cars.add(car));
        return cars;
    }

    public Car getCar(int id){
        return carRepository.findById(id).get();
    }

    public void saveOrUpdateCar(Car car){
        carRepository.save(car);
    }

    public void deleteCar(int id){
        carRepository.deleteById(id);
    }
}
*/
