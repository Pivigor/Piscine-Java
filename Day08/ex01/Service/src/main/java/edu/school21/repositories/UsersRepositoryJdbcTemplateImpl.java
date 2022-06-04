package edu.school21.repositories;

import edu.school21.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {
    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);

    }

    @Override
    public User findById(Long id) {
        return jdbcTemplate.queryForObject("select * from chat.users where id=?", new UserRowMapper(), id);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from chat.users", new UserRowMapper());
    }

    @Override
    public void save(User entity) {
        jdbcTemplate.update("insert into chat.users (email) VALUES (?)", entity.getEmail());
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.update("update chat.users set email = ? where id=?", entity.getEmail(), entity.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("delete from chat.users where id=?", id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        List<User> query = jdbcTemplate.query("select * from chat.users where email=?", new UserRowMapper(), email);

        if (query.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(query.get(0));
    }

    public static class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            return new User(resultSet.getLong(1), resultSet.getString(2));
        }
    }

    JdbcTemplate jdbcTemplate;
}
