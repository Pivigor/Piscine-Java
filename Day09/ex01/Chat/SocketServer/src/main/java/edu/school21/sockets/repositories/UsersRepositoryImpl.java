package edu.school21.sockets.repositories;

import edu.school21.sockets.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Component
public class UsersRepositoryImpl implements UsersRepository {
    public UsersRepositoryImpl(DataSource dataSource) {
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
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into chat.users (login, passwd) VALUES (?, ?)", new String[] { "id" });
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getHashPassword());

            return preparedStatement;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            entity.setId(keyHolder.getKey().longValue());
        }
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.update("update chat.users set login = ?, passwd = ? where id=?", entity.getLogin(), entity.getHashPassword(), entity.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("delete from chat.users where id=?", id);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        List<User> query = jdbcTemplate.query("select * from chat.users where login=?", new UserRowMapper(), login);

        if (query.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(query.get(0));
    }

    public static class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            return new User(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3));
        }
    }

    private final JdbcTemplate jdbcTemplate;
}
