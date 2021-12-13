package vea.kuc0277.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vea.kuc0277.project.DAO.CarDao;
import vea.kuc0277.project.DAO.DriverDao;
import vea.kuc0277.project.models.Car;
import vea.kuc0277.project.models.Driver;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DriverService {

    @Autowired
    private DriverDao driverDao;

    public List<Driver> getAll() {
        return driverDao.getAll();
    }

    public void delete(int id) {
        driverDao.delete(id);
    }

    public Driver insert(Driver driver) {
        return driverDao.save(driver);
    }

    public Driver get(int id) {
        return driverDao.get(id);
    }

    public void update(Driver driver) {
        driverDao.update(driver);
    }
}
