package vea.kuc0277.project.JDBC.Mappers;

import org.springframework.jdbc.core.RowMapper;
import vea.kuc0277.project.models.Route;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RouteMapper implements RowMapper<Route> {
    @Override
    public Route mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Route(rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("length")
        );
    }
}