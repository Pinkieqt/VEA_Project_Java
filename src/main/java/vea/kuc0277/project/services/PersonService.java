package vea.kuc0277.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import vea.kuc0277.project.JDBC.Mappers.AdminMapper;
import vea.kuc0277.project.JDBC.Mappers.DriverMapper;
import vea.kuc0277.project.models.Person;

import javax.sql.DataSource;
import javax.transaction.Transactional;

@Service
@Transactional
public class PersonService {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Person findByLogin(String login) {
        try {
            return jdbcTemplate.queryForObject("select * from admins where login=?", new Object[] {login} , new AdminMapper());
        } catch (EmptyResultDataAccessException e) {
        }
        try {
            return jdbcTemplate.queryForObject("select * from drivers where login=?", new Object[] {login} , new DriverMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
