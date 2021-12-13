package vea.kuc0277.project.RestControllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vea.kuc0277.project.models.Car;
import vea.kuc0277.project.models.Route;
import vea.kuc0277.project.services.CarService;
import vea.kuc0277.project.services.RouteService;

import java.util.List;

@RestController
public class RestRouteController {
    @Autowired
    RouteService routeService;

    @GetMapping("/api/getRoutes")
    private List<Route> getAllRoutes()
    {
        return routeService.getAll();
    }

    @GetMapping("/api/getRoute/{id}")
    private Route getRoute(@PathVariable("id") int id)
    {
        return routeService.get(id);
    }

    @DeleteMapping("/api/deleteRoute/{id}")
    private void deleteRoute(@PathVariable("id") int id)
    {
        routeService.delete(id);
    }

    @PostMapping("/api/addRoute")
    private int addRoute(@RequestBody Route route)
    {
        if(route.getName() != null && route.getLength() > 0) {
            routeService.insert(route);
            return route.getId();
        }
        return 0;
    }


}
