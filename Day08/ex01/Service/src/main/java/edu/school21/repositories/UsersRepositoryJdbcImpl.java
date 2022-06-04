package edu.school21.repositories;

import edu.school21.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {
    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        try {
            this.connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findById(Long id) {
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(String.format("select * from chat.users where id=%d", id));

            if (result.next()) {
                return new User(id, result.getString(2));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> list = new LinkedList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from chat.users");

            while (result.next()) {
                list.add(new User(
                        result.getLong(1),
                        result.getString(2)));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    @Override
    public void save(User entity) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into chat.users (email) VALUES (?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, entity.getEmail());

            preparedStatement.execute();

            ResultSet result = preparedStatement.getGeneratedKeys();
            if (result.next()) {
                entity.setId(result.getLong(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User entity) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update chat.users set email = ? where id=?");

            preparedStatement.setString(1, entity.getEmail());
            preparedStatement.setLong(2, entity.getId());

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Statement statement = connection.createStatement();
            statement.execute(String.format("delete from chat.users where id=%d", id));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(String.format("select * from chat.users where email=%s", email));

            if (result.next()) {
                return Optional.of(new User(result.getLong(1), email));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    private final Connection connection;
}
