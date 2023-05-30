package dev.bastida.finca.auth.application.service;

import dev.bastida.finca.auth.application.port.out.FindAccountByEmailPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailUserDetailService implements UserDetailsService {

    private final FindAccountByEmailPort findAccountByEmailPort;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findAccountByEmailPort.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
