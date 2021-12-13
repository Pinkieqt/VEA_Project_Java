/*
package vea.kuc0277.project.JPA;

import org.springframework.stereotype.Repository;
import vea.kuc0277.project.DAO.CarDao;
import vea.kuc0277.project.models.Car;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class CarJPA implements CarDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Car save(Car car) {
        em.persist(car);
        em.flush();
        return car;
    }

    @Override
    public List<Car> getAll() {
        return em.createQuery("select car from Car car", Car.class).getResultList();
    }

    @Override
    public Car get(int id) {
        List<Car> cars = em.createQuery("select car from Car car where car.id = :id", Car.class)
                .setParameter("id", id)
                .getResultList();
        if (cars.isEmpty()) {
            return null;
        }
        return cars.get(0);
    }

    @Override
    public Car update(Car car) {
        em.merge(car);
        em.flush();
        return car;
    }

    @Override
    public void delete(int id) {
        Car car = em.find(Car.class, id);
        em.remove(car);
    }

}
*/
