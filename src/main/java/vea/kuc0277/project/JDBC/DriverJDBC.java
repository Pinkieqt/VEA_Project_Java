package vea.kuc0277.project.JDBC;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import vea.kuc0277.project.DAO.DriverDao;
import vea.kuc0277.project.JDBC.Mappers.CarMapper;
import vea.kuc0277.project.JDBC.Mappers.DriverMapper;
import vea.kuc0277.project.models.Car;
import vea.kuc0277.project.models.Driver;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@Repository
public class DriverJDBC implements DriverDao {
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
                sqlCreateTable = "CREATE TABLE IF NOT EXISTS drivers(id INT NOT NULL AUTO_INCREMENT," +
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
    public Driver save(Driver entity) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("insert into drivers (login, name, password, role, age) values (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getLogin());
            ps.setString(2, entity.getName());
            ps.setString(3, encoder.encode(entity.getPassword()));
            ps.setString(4, "DRIVER");
            ps.setInt(5, entity.getAge());
            return ps;
        }, keyHolder);
        entity.setId((int) keyHolder.getKey());
        return entity;
    }

    @Override
    public List<Driver> getAll() {
        List<Driver> drivers = jdbcTemplate.query("select * from drivers", new DriverMapper());
        for (Driver t : drivers) {
            List<Car> cars = jdbcTemplate.query("select * from cars where ownerId = ?", new Object[]{t.getId()}, new CarMapper());
            cars.forEach(x -> x.setOwner(t));
            t.setCars(cars);
        }
        return drivers;
    }

    @Override
    public Driver get(int id) {
        Driver driver = jdbcTemplate.queryForObject("select * from drivers where id=?", new Object[]{id}, new DriverMapper());
        return driver;
    }

    @Override
    public Driver update(Driver entity) {
        jdbcTemplate.update("UPDATE drivers SET login = ?, name = ?, password = ?, role = ?, age = ? WHERE id = ?",
                entity.getLogin(), entity.getName(), entity.getPassword(), "DRIVER", entity.getAge(), entity.getId());
        return entity;
    }

    @Override
    public void delete(int id) {
        Driver driver = jdbcTemplate.queryForObject("select * from drivers where id=?", new Object[]{id}, new DriverMapper());
        List<Car> cars = jdbcTemplate.query("select * from cars where ownerId = ?", new Object[]{driver.getId()}, new CarMapper());
        if(cars.isEmpty()){
            jdbcTemplate.update("DELETE FROM drivers WHERE id = ?", id);
        }
    }
}