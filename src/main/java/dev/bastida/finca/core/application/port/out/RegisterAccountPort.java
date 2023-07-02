package dev.bastida.finca.core.application.port.out;

import dev.bastida.finca.core.domain.Account;

public interface RegisterAccountPort {
    Account registerAccount(Account user);
}
