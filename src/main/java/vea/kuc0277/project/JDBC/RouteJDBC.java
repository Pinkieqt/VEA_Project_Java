package vea.kuc0277.project.JDBC;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import vea.kuc0277.project.JDBC.Mappers.RouteMapper;
import vea.kuc0277.project.DAO.RouteDao;
import vea.kuc0277.project.models.Route;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@Repository
public class RouteJDBC implements RouteDao {
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
                sqlCreateTable = "CREATE TABLE IF NOT EXISTS routes(id INT NOT NULL AUTO_INCREMENT," +
                        " name varchar(255) not null, " +
                        " length INT not null)";
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
    public Route save(Route entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("insert into routes (name, length) values (?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getName());
            ps.setInt(2, entity.getLength());
            return ps;
        }, keyHolder);
        entity.setId((int) keyHolder.getKey());
        return entity;
    }

    @Override
    public List<Route> getAll() {
        List<Route> routes = jdbcTemplate.query("select * from routes", new RouteMapper());
        return routes;
    }

    @Override
    public Route get(int id) {
        Route route = jdbcTemplate.queryForObject("select * from routes where id=?", new Object[]{id}, new RouteMapper());
        return route;
    }

    @Override
    public Route update(Route entity) {
        jdbcTemplate.update("UPDATE routes SET name = ?, length = ? WHERE id = ?",
                entity.getName(), entity.getLength(), entity.getId());
        return entity;
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM routes WHERE id = ?", id);
    }
}