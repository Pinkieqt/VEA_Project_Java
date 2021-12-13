package vea.kuc0277.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vea.kuc0277.project.models.Admin;
import vea.kuc0277.project.models.Car;
import vea.kuc0277.project.models.Driver;
import vea.kuc0277.project.models.Route;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

@Transactional
@Service
public class Init {
    @Autowired
    AdminService adminService;
    @Autowired
    CarService carService;
    @Autowired
    DriverService driverService;
    @Autowired
    RouteService routeService;

    @PostConstruct
    public void init() {
        //Insert Admins
        adminService.insert(new Admin("dav", "David Plicnar", "qwe", 53));
        adminService.insert(new Admin("tom01", "Tomáš Ertl", "qwe", 34));
        adminService.insert(new Admin("tom02", "Tomáš Hujer", "qwe", 44));
        adminService.insert(new Admin("jos01", "Josef Hrab", "qwe", 21));

        //Drivers
        Driver driver = new Driver("jan", "Jan Plicnar", "qwe", 33);
        Driver driver2 = new Driver("jan02", "Jan Kolečko", "qwe", 23);
        Driver driver3 = new Driver("luk01", "Lukáš Hen", "qwe", 64);
        Driver driver4 = new Driver("voj01", "Vojta Jarmil", "qwe", 22);
        Driver driver5 = new Driver("huj01", "Hujer Hej", "qwe", 32);
        driverService.insert(driver);
        driverService.insert(driver2);
        driverService.insert(driver3);
        driverService.insert(driver4);
        driverService.insert(driver5);

        //Routes
        routeService.insert(new Route("Ostrava - Fulnek", 43));
        routeService.insert(new Route("Fulnek - Ostrava", 39));
        routeService.insert(new Route("Ostrava - Bohumín", 74));
        routeService.insert(new Route("Orlová - Bohumín", 47));
        routeService.insert(new Route("Klimkovice - Ostrava", 21));
        routeService.insert(new Route("Slatina - Ostrava", 38));
        routeService.insert(new Route("Ves - Fulnek", 92));

        //Cars
        carService.insert(new Car("Mercedes", "Vito", 76453, driver));
        carService.insert(new Car("Fiat", "Punto", 92834, driver));
        carService.insert(new Car("Škoda", "Octavia", 109523, driver2));
        carService.insert(new Car("Citroen", "C5", 36472, driver2));
        carService.insert(new Car("VW", "Passat", 259123, driver3));
        carService.insert(new Car("Mercedes", "Sprinter", 34612, driver5));
    }
}
