package edu.school21.sockets.repositories;

import edu.school21.sockets.models.Message;

import java.util.Optional;

public interface MessagesRepository extends CrudRepository<Message> {

    Optional<Message> findByAuthorID(Long authorID);

}
