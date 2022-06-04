package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImplTest {
    ProductsRepository repository;
    EmbeddedDatabase dataSource;

    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = new LinkedList<>(Arrays.asList(
            new Product(1L, "product1", 0.1),
            new Product(2L, "product2", 10.25),
            new Product(3L, "product3", 170.5),
            new Product(4L, "product4", 200.0),
            new Product(5L, "product1", 1000.55)));

    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(2L, "product2", 10.25);
    final Product EXPECTED_UPDATED_PRODUCT = new Product(2L, "new product", 1000.0);
    final Product EXPECTED_SAVED_PRODUCT = new Product(6L, "product6", 600.0);

    @BeforeEach
    public void init() {
        dataSource = new EmbeddedDatabaseBuilder()
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();

        try {
            repository = new ProductsRepositoryJdbcImpl(dataSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void connectionDbTest() throws SQLException {
        Assertions.assertNotNull(dataSource.getConnection());
    }

    @Test
    void findAllTest() {
        Assertions.assertEquals(EXPECTED_FIND_ALL_PRODUCTS, repository.findAll());
    }

    @Test
    void findAllTestThrow() {
        dataSource.shutdown();
        Assertions.assertThrows(RuntimeException.class, () -> repository.findAll());
    }

    @Test
    void findByIdTest() {
        Assertions.assertTrue(repository.findById(2L).isPresent());
        Assertions.assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, repository.findById(2L).get());
    }

    @Test
    void findByIdTestThrow() {
        dataSource.shutdown();
        Assertions.assertThrows(RuntimeException.class, () -> repository.findById(2L));
    }

    @Test
    void updateTest() {
        repository.update(EXPECTED_UPDATED_PRODUCT);
        Assertions.assertTrue(repository.findById(2L).isPresent());
        Assertions.assertEquals(EXPECTED_UPDATED_PRODUCT, repository.findById(2L).get());
    }

    @Test
    void updateTestThrow() {
        dataSource.shutdown();
        Assertions.assertThrows(RuntimeException.class, () -> repository.update(EXPECTED_UPDATED_PRODUCT));
    }

    @Test
    void saveTest() {
        repository.save(EXPECTED_SAVED_PRODUCT);
        Assertions.assertTrue(repository.findById(6L).isPresent());
        Assertions.assertEquals(EXPECTED_SAVED_PRODUCT, repository.findById(6L).get());
    }

    @Test
    void saveTestThrow() {
        dataSource.shutdown();
        Assertions.assertThrows(RuntimeException.class, () -> repository.save(EXPECTED_SAVED_PRODUCT));
    }

    @Test
    void deleteTest() {
        repository.delete(2L);
        Assertions.assertEquals(Optional.empty(), repository.findById(2L));
    }

    @Test
    void deleteTestThrow() {
        dataSource.shutdown();
        Assertions.assertThrows(RuntimeException.class, () -> repository.delete(2L));
    }

    @AfterEach
    void close() {
        dataSource.shutdown();
    }
}
