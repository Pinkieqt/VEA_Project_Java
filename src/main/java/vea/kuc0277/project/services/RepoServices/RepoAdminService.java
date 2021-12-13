/*
package vea.kuc0277.project.services.RepoServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vea.kuc0277.project.models.Admin;
import vea.kuc0277.project.repositories.AdminRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RepoAdminService {
    @Autowired
    AdminRepository adminRepository;

    public List<Admin> getAllAdmins(){
        List<Admin> admins = new ArrayList<>();
        adminRepository.findAll().forEach(admin -> admins.add(admin));
        return admins;
    }

    public Admin getAdmin(int id){
        return adminRepository.findById(id).get();
    }

    public void saveOrUpdateAdmin(Admin admin){
        adminRepository.save(admin);
    }

    public void deleteAdmin(int id){
        adminRepository.deleteById(id);
    }
}
*/
