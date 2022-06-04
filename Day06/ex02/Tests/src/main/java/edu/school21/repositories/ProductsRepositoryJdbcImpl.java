package edu.school21.repositories;

import edu.school21.models.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {
    public ProductsRepositoryJdbcImpl(DataSource dataSource) throws SQLException {
        this.connection = dataSource.getConnection();
    }

    @Override
    public List<Product> findAll() {
        List<Product> list = new LinkedList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from product");

            while (result.next()) {
                list.add(new Product(
                        result.getLong(1),
                        result.getString(2),
                        result.getDouble(3)));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    @Override
    public Optional<Product> findById(Long id) {
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(String.format("select * from product where id=%d", id));

            if (result.next()) {
                return Optional.of(new Product(id,
                        result.getString(2),
                        result.getDouble(3)));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    @Override
    public void update(Product product) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update product set name = ?, price = ? where id=?");

            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setLong(3, product.getId());

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Product product) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into product (name, price) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());

            preparedStatement.execute();

            ResultSet result = preparedStatement.getGeneratedKeys();
            if (result.next()) {
                product.setId(result.getLong(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Statement statement = connection.createStatement();
            statement.execute(String.format("delete from product where id=%d", id));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    Connection connection;
}
