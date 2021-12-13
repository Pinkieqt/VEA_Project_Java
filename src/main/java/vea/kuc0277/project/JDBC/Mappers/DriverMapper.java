package vea.kuc0277.project.JDBC.Mappers;

import org.springframework.jdbc.core.RowMapper;
import vea.kuc0277.project.models.Driver;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DriverMapper implements RowMapper<Driver> {
    @Override
    public Driver mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Driver(rs.getInt("id"),
                rs.getString("login"),
                rs.getString("name"),
                rs.getString("password"),
                rs.getInt("age"),
                null
        );
    }
}