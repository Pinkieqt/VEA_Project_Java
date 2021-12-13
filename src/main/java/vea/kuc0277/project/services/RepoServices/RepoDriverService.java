/*
package vea.kuc0277.project.services.RepoServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vea.kuc0277.project.models.Driver;
import vea.kuc0277.project.repositories.DriverRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RepoDriverService {
    @Autowired
    DriverRepository driverRepository;

    public List<Driver> getAllDrivers(){
        List<Driver> drivers = new ArrayList<>();
        driverRepository.findAll().forEach(driver -> drivers.add(driver));
        return drivers;
    }

    public Driver getDriver(int id){
        return driverRepository.findById(id).get();
    }

    public void saveOrUpdateDriver(Driver driver){
        driverRepository.save(driver);
    }

    public void deleteDriver(int id){
        driverRepository.deleteById(id);
    }
}
*/
