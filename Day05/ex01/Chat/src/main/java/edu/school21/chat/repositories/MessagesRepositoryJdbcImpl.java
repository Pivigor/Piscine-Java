package edu.school21.chat.repositories;

import edu.school21.chat.models.Message;

import javax.sql.DataSource;
import java.sql.*;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        try {
            this.connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Message findById(Long id) {
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(String.format("select * from chat.msgs where id=%d", id));

            if (result.next()) {
                return new Message(id,
                        null,
                        null,
                        result.getString(4),
                        result.getTimestamp(5) == null ? null : result.getTimestamp(5).toLocalDateTime());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    private final Connection connection;
}
