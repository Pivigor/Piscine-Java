package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

public class Program {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "12345678";

    public static void main(String[] args) {
        try (HikariDataSource dataSource = hikariDataSource()) {
            //Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(dataSource);
            Message message = messagesRepository.findById(25L);
            message.setText("Bye");
            message.setDateTime(null);
            messagesRepository.update(message);

        } catch (MessagesRepositoryJdbcImpl.NotSavedSubEntityException e) {
            throw e;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static HikariDataSource hikariDataSource() {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(DB_URL);
        config.setUsername(DB_USERNAME);
        config.setPassword(DB_PASSWORD);

        return new HikariDataSource(config);
    }
}
