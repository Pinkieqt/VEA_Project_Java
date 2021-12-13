/*
package vea.kuc0277.project.JPA;

import org.springframework.stereotype.Repository;
import vea.kuc0277.project.DAO.AdminDao;
import vea.kuc0277.project.DAO.CarDao;
import vea.kuc0277.project.models.Admin;
import vea.kuc0277.project.models.Car;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class AdminJPA implements AdminDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Admin save(Admin admin) {
        em.persist(admin);
        em.flush();
        return admin;
    }

    @Override
    public List<Admin> getAll() {
        return em.createQuery("select admin from Admin admin", Admin.class).getResultList();
    }

    @Override
    public Admin get(int id) {
        List<Admin> admins = em.createQuery("select admin from Admin admin where admin.id = :id", Admin.class)
                .setParameter("id", id)
                .getResultList();
        if (admins.isEmpty()) {
            return null;
        }
        return admins.get(0);
    }

    @Override
    public Admin update(Admin admin) {
        em.merge(admin);
        em.flush();
        return admin;
    }

    @Override
    public void delete(int id) {
        Admin admin = em.find(Admin.class, id);
        em.remove(admin);
    }

}
*/
