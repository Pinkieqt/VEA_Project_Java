package vea.kuc0277.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vea.kuc0277.project.DAO.AdminDao;
import vea.kuc0277.project.DAO.DriverDao;
import vea.kuc0277.project.models.Admin;
import vea.kuc0277.project.models.Driver;

import javax.transaction.Transactional;
import java.util.List;

import vea.kuc0277.project.models.Person;
import vea.kuc0277.project.utilities.PasswordEncryptor.*;

@Service
@Transactional
public class AdminService implements UserDetailsService {

    @Autowired
    private AdminDao adminDao;

    @Autowired
    PersonService personService;

    public List<Admin> getAll() {
        return adminDao.getAll();
    }

    public void delete(int id) {
        adminDao.delete(id);
    }

    public Admin insert(Admin admin) {
        return adminDao.save(admin);
    }

    public Admin get(int id) {
        return adminDao.get(id);
    }

    public void update(Admin admin) {
        adminDao.update(admin);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personService.findByLogin(username);

        if (person == null) {
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }
        String role = "DRIVER";
        if (person instanceof Admin){
            role = "ADMIN";
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return User.withUsername(person.getLogin())
                .password(person.getPassword())
                .roles(role)
                .build();
    }
}
