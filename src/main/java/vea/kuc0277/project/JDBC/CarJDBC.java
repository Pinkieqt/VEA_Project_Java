package vea.kuc0277.project.JDBC;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import vea.kuc0277.project.DAO.CarDao;
import vea.kuc0277.project.JDBC.Mappers.CarMapper;
import vea.kuc0277.project.JDBC.Mappers.DriverMapper;
import vea.kuc0277.project.models.Car;
import vea.kuc0277.project.models.Driver;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@Repository
public class CarJDBC implements CarDao {
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
                sqlCreateTable = "CREATE TABLE IF NOT EXISTS cars(id INT NOT NULL AUTO_INCREMENT," +
                        " manufacturer varchar(255) not null, " +
                        " model varchar(255) not null, " +
                        " mileage INT not null, " +
                        " ownerId INT not null)";
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
    public Car save(Car entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("insert into cars (manufacturer, model, mileage, ownerId) values (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getManufacturer());
            ps.setString(2, entity.getModel());
            ps.setInt(3, entity.getMileage());
            ps.setInt(4, entity.getOwnerId());
            return ps;
        }, keyHolder);
        entity.setId((int) keyHolder.getKey());
        return entity;
    }

    @Override
    public List<Car> getAll() {
        List<Car> cars = jdbcTemplate.query("select * from cars", new CarMapper());
        for (Car t : cars) {
            if (t.getOwnerId() != 0) {
                Driver driver = jdbcTemplate.queryForObject("select * from drivers where id = ?", new Object[]{t.getOwnerId()}, new DriverMapper());
                t.setOwner(driver);
            }
        }
        return cars;
    }

    @Override
    public Car get(int id) {
        try {
            Car car = jdbcTemplate.queryForObject("select * from cars where id=?", new Object[]{id}, new CarMapper());
            return car;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Car update(Car entity) {
        jdbcTemplate.update("UPDATE cars SET manufacturer = ?, model = ?, mileage = ?, ownerId = ? WHERE id = ?",
                entity.getManufacturer(), entity.getModel(), entity.getMileage(), entity.getOwnerId(), entity.getId());
        return entity;
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM cars WHERE id = ?", id);
    }
}