package dev.bastida.finca.auth.application.port.out;

import dev.bastida.finca.auth.domain.Account;

public interface RegisterAccountPort {
    Account registerAccount(Account user);
}
