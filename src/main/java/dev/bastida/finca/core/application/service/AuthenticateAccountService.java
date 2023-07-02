package dev.bastida.finca.core.application.service;

import dev.bastida.finca.core.application.port.in.AuthenticateAccountUseCase;
import dev.bastida.finca.core.application.port.out.FindTokenPort;
import dev.bastida.finca.core.application.port.out.SaveTokenPort;
import dev.bastida.finca.core.domain.Account;
import dev.bastida.finca.core.domain.Token;
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
    private final SaveTokenPort saveTokenPort;
    private final FindTokenPort findTokenPort;

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
                .value(jwt)
                .expired(false)
                .revoked(false)
                .build();
        saveTokenPort.save(token);
    }

    private void revokeAllUserTokens(Account account) {
        final List<Token> allValidTokensByUser = findTokenPort.findValidTokensByAccountId(account.getId());
        if (allValidTokensByUser.isEmpty()) {
            return;
        }
        allValidTokensByUser.forEach(token -> {
            token.setRevoked(true);
            token.setExpired(true);
        });
        saveTokenPort.saveAll(allValidTokensByUser);
    }
}
