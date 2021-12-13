/*
package vea.kuc0277.project.JPA;

import org.springframework.stereotype.Repository;
import vea.kuc0277.project.DAO.DriverDao;
import vea.kuc0277.project.DAO.RouteDao;
import vea.kuc0277.project.models.Driver;
import vea.kuc0277.project.models.Route;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class RouteJPA implements RouteDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Route save(Route route) {
        em.persist(route);
        em.flush();
        return route;
    }

    @Override
    public List<Route> getAll() {
        return em.createQuery("select r from Route r", Route.class).getResultList();
    }

    @Override
    public Route get(int id) {
        List<Route> routes = em.createQuery("select r from Route r where r.id = :id", Route.class)
                .setParameter("id", id)
                .getResultList();
        if (routes.isEmpty()) {
            return null;
        }
        return routes.get(0);
    }

    @Override
    public Route update(Route route) {
        em.merge(route);
        em.flush();
        return route;
    }

    @Override
    public void delete(int id) {
        Route route = em.find(Route.class, id);
        em.remove(route);
    }

}
*/
