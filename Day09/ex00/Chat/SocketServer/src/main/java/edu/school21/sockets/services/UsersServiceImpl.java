package edu.school21.sockets.services;

import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsersServiceImpl implements UsersService {
    public UsersServiceImpl(UsersRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public boolean signUp(String login, String password) {
        if (repository.findByLogin(login).isPresent()) {
            return false;
        }

        repository.save(new User(0L, login, encoder.encode(password)));
        return true;
    }


    private final UsersRepository repository;
    private final PasswordEncoder encoder;
}
