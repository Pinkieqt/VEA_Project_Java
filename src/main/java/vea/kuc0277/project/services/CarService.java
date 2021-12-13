package vea.kuc0277.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vea.kuc0277.project.DAO.CarDao;
import vea.kuc0277.project.models.Car;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CarService {

    @Autowired
    private CarDao carDao;

    public List<Car> getAll() {
        return carDao.getAll();
    }

    public void delete(int id) {
        carDao.delete(id);
    }

    public Car insert(Car car) {

        return carDao.save(car);
    }

    public Car get(int id) {
        return carDao.get(id);
    }

    public void update(Car car) {
        carDao.update(car);
    }
}
