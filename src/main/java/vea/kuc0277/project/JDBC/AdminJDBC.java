package vea.kuc0277.project.JDBC;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import vea.kuc0277.project.DAO.AdminDao;
import vea.kuc0277.project.JDBC.Mappers.AdminMapper;
import vea.kuc0277.project.models.Admin;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@Repository
public class AdminJDBC implements AdminDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @PostConstruct
    public void init() {
        try {
            String dbProducerName;
            try (Connection con = jdbcTemplate.getDataSource().getConnection()) {
                DatabaseMetaData metaData = con.getMetaData();
                dbProducerName = metaData.getDatabaseProductName();
            }
            String sqlCreateTable;
            if ("H2".equals(dbProducerName)) {
                sqlCreateTable = "CREATE TABLE IF NOT EXISTS admins(id INT NOT NULL AUTO_INCREMENT," +
                        " login varchar(255) not null, " +
                        " name varchar(255) not null, " +
                        " password varchar(255) not null, " +
                        " role varchar(255) not null, " +
                        " age INT not null)";
            } else {
                throw new RuntimeException("Unsupported database type");
            }
            jdbcTemplate.update(sqlCreateTable);
        } catch (DataAccessException e) {
            System.out.println("Table already exists.");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Admin save(Admin entity) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("insert into admins (login, name, password, role, age) values (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getLogin());
            ps.setString(2, entity.getName());
            ps.setString(3, encoder.encode(entity.getPassword()));
            ps.setString(4, "ADMIN");
            ps.setInt(5, entity.getAge());
            return ps;
        }, keyHolder);
        entity.setId((int) keyHolder.getKey());
        return entity;
    }

    @Override
    public List<Admin> getAll() {
        List<Admin> admins = jdbcTemplate.query("select * from admins", new AdminMapper());
        return admins;
    }

    @Override
    public Admin get(int id) {
        Admin admin = jdbcTemplate.queryForObject("select * from admins where id=?", new Object[]{id}, new AdminMapper());
        return admin;
    }

    @Override
    public Admin update(Admin entity) {
        jdbcTemplate.update("UPDATE admins SET login = ?, name = ?, password = ?, role = ?, age = ? WHERE id = ?",
                entity.getLogin(), entity.getName(), entity.getPassword(), "ADMIN", entity.getAge(), entity.getId());
        return entity;
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM admins WHERE id = ?", id);
    }
}