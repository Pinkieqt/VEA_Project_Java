package vea.kuc0277.project.JDBC.Mappers;

import org.springframework.jdbc.core.RowMapper;
import vea.kuc0277.project.models.Admin;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminMapper implements RowMapper<Admin> {
    @Override
    public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Admin(rs.getInt("id"),
                rs.getString("login"),
                rs.getString("name"),
                rs.getString("password"),
                rs.getInt("age")
        );
    }
}