package dev.bastida.finca.core.application.port.out;

import dev.bastida.finca.core.application.domain.Account;

public interface RegisterAccountPort {
    Account registerAccount(Account user);
}
