package edu.school21.sockets.repositories;

import edu.school21.sockets.models.Message;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class MessagesRepositoryImpl implements MessagesRepository {
    public MessagesRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Message findById(Long id) {
        return jdbcTemplate.queryForObject("select * from chat.msgs where id=?", new MessageRowMapper(), id);
    }

    @Override
    public List<Message> findAll() {
        return jdbcTemplate.query("select * from chat.msgs", new MessageRowMapper());
    }

    @Override
    public void save(Message entity) {
        jdbcTemplate.update("insert into chat.msgs (authorID, message, datetime) VALUES (?, ?, ?)",
                entity.getAuthorID(), entity.getText(), entity.getDateTime());
    }

    @Override
    public void update(Message entity) {
        jdbcTemplate.update("update chat.msgs set authorID = ?, message = ?, datetime = ? where id=?",
                entity.getAuthorID(), entity.getText(), entity.getDateTime(), entity.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("delete from chat.msgs where id=?", id);
    }

    @Override
    public Optional<Message> findByAuthorID(Long authorID) {
        List<Message> query = jdbcTemplate.query("select * from chat.msgs where authorID=?", new MessageRowMapper(), authorID);

        if (query.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(query.get(0));
    }

    public static class MessageRowMapper implements RowMapper<Message> {

        @Override
        public Message mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Message(resultSet.getLong(1), resultSet.getLong(2), resultSet.getString(3), resultSet.getTimestamp(4).toLocalDateTime());
        }
    }

    private final JdbcTemplate jdbcTemplate;
}
