package edu.school21.sockets.services;

import java.time.LocalDateTime;
import java.util.List;

public interface UsersService {

    Long signUp(String login, String password);

    Long signIn(String login, String password);

    boolean sendMessage(Long authorID, String message, LocalDateTime dateTime);

    List<String> getMessages();

}
