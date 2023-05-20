package dev.bastida.finca.core.out.persistence;

import dev.bastida.finca.core.application.port.out.RegisterUserPort;
import dev.bastida.finca.core.application.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPersistenceAdapter implements RegisterUserPort {

    private final UserRepository userRepository;

    @Override
    public void registerUser(User user) {
        userRepository.save(user);
    }
}
