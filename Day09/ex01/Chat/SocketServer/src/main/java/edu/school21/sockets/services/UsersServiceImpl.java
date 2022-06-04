package edu.school21.sockets.services;

import edu.school21.sockets.models.Message;
import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.MessagesRepository;
import edu.school21.sockets.repositories.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class UsersServiceImpl implements UsersService {
    public UsersServiceImpl(UsersRepository usersRepository, PasswordEncoder encoder, MessagesRepository messagesRepository) {
        this.usersRepository = usersRepository;
        this.encoder = encoder;
        this.messagesRepository = messagesRepository;
        this.messages = new LinkedList<>();
    }

    @Override
    public Long signUp(String login, String password) {
        if (usersRepository.findByLogin(login).isPresent()) {
            return 0L;
        }

        User user = new User(0L, login, encoder.encode(password));
        usersRepository.save(user);
        return user.getId();
    }

    @Override
    public Long signIn(String login, String password) {
        Optional<User> userOptional = usersRepository.findByLogin(login);
        if (userOptional.isPresent()) {
            return userOptional.get().getId();
        }

        return 0L;
    }

    @Override
    public synchronized boolean sendMessage(Long authorID, String message, LocalDateTime dateTime) {
        messagesRepository.save(new Message(0L, authorID, message, dateTime));
        User user = usersRepository.findById(authorID);

        if (messages.size() > 10000) {
            messages.clear();
        }
        messages.add(String.format("%s: %s", user.getLogin(), message));

        return true;
    }

    @Override
    public List<String> getMessages() {
        return messages;
    }


    private final UsersRepository usersRepository;
    private final PasswordEncoder encoder;
    private final MessagesRepository messagesRepository;
    public final List<String> messages;
}
