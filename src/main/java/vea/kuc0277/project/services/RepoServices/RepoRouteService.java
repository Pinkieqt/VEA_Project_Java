/*
package vea.kuc0277.project.services.RepoServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vea.kuc0277.project.models.Route;
import vea.kuc0277.project.repositories.RouteRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RepoRouteService {
    @Autowired
    RouteRepository routeRepository;

    public List<Route> getAllRoutes(){
        List<Route> routes = new ArrayList<>();
        routeRepository.findAll().forEach(route -> routes.add(route));
        return routes;
    }

    public Route getRoute(int id){
        return routeRepository.findById(id).get();
    }

    public void saveOrUpdateRoute(Route route){
        routeRepository.save(route);
    }

    public void deleteRoute(int id){
        routeRepository.deleteById(id);
    }
}
*/
