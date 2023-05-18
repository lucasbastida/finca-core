package dev.bastida.finca.core.out.persistence;

import dev.bastida.finca.core.application.port.out.RegisterUserPort;
import org.springframework.stereotype.Service;

@Service
public class UserPersistenceAdapter implements RegisterUserPort {

    private final UserRepository userRepository;

    public UserPersistenceAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(User user) {
        userRepository.save(user);
    }
}
