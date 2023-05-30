package dev.bastida.finca.auth.application.service;

import dev.bastida.finca.auth.adapter.in.filter.JwtService;
import dev.bastida.finca.auth.adapter.out.persistence.TokenRepository;
import dev.bastida.finca.auth.domain.Account;
import dev.bastida.finca.auth.domain.Token;
import dev.bastida.finca.auth.application.port.in.AuthenticateAccountCommand;
import dev.bastida.finca.auth.application.port.in.AuthenticateAccountResponse;
import dev.bastida.finca.auth.application.port.in.AuthenticateAccountUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticateAccountService implements AuthenticateAccountUseCase {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;

    @Override
    public AuthenticateAccountResponse authenticate(AuthenticateAccountCommand command) {
        final Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        command.email(),
                        command.password()
                )
        );
        final Account principal = (Account) authenticate.getPrincipal();
        final String token = jwtService.generateToken(principal);
        final String refreshToken = jwtService.generateRefreshToken(principal);
        revokeAllUserTokens(principal);
        saveUserToken(principal, token);
        return new AuthenticateAccountResponse(
                token,
                refreshToken
        );
    }

    private void saveUserToken(Account account, String jwt) {
        final Token token = Token.builder()
                .account(account)
                .token(jwt)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);

    }

    private void revokeAllUserTokens(Account account) {
        final List<Token> allValidTokensByUser = tokenRepository.findAllValidTokensByUser(account.getId());
        if (allValidTokensByUser.isEmpty()) {
            return;
        }
        allValidTokensByUser.forEach(token -> {
            token.setRevoked(true);
            token.setExpired(true);
        });
        tokenRepository.saveAll(allValidTokensByUser);
    }
}
