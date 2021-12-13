package vea.kuc0277.project.JDBC.Mappers;

import org.springframework.jdbc.core.RowMapper;
import vea.kuc0277.project.models.Car;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarMapper implements RowMapper<Car> {
    @Override
    public Car mapRow(ResultSet rs, int rowNum) throws SQLException {

        Car car = new Car(rs.getInt("id"),
                rs.getString("manufacturer"),
                rs.getString("model"),
                rs.getInt("mileage"),
                null
        );
        car.setOwnerId(rs.getInt("ownerId"));
        return car;
    }
}