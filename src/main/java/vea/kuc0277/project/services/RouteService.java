package vea.kuc0277.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vea.kuc0277.project.DAO.AdminDao;
import vea.kuc0277.project.DAO.RouteDao;
import vea.kuc0277.project.models.Admin;
import vea.kuc0277.project.models.Route;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RouteService {

    @Autowired
    private RouteDao routeDao;

    public List<Route> getAll() {
        return routeDao.getAll();
    }

    public void delete(int id) {
        routeDao.delete(id);
    }

    public Route insert(Route route) {
        return routeDao.save(route);
    }

    public Route get(int id) {
        return routeDao.get(id);
    }

    public void update(Route route) {
        routeDao.update(route);
    }
}
