/*
package vea.kuc0277.project.JPA;

import org.springframework.stereotype.Repository;
import vea.kuc0277.project.DAO.DriverDao;
import vea.kuc0277.project.models.Driver;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class DriverJPA implements DriverDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Driver save(Driver driver) {
        em.persist(driver);
        em.flush();
        return driver;
    }

    @Override
    public List<Driver> getAll() {
        return em.createQuery("select d from Driver d", Driver.class).getResultList();
    }

    @Override
    public Driver get(int id) {
        List<Driver> drivers = em.createQuery("select d from Driver d where d.id = :id", Driver.class)
                .setParameter("id", id)
                .getResultList();
        if (drivers.isEmpty()) {
            return null;
        }
        return drivers.get(0);
    }

    @Override
    public Driver update(Driver driver) {
        em.merge(driver);
        em.flush();
        return driver;
    }

    @Override
    public void delete(int id) {
        Driver driver = em.find(Driver.class, id);
        em.remove(driver);
    }

}
*/
