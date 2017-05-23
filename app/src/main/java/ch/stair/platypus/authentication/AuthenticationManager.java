package ch.stair.platypus.authentication;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;

import java.io.IOException;

import javax.inject.Inject;

public class AuthenticationManager {
    private final AccountManager accountManager;

    @Inject
    public AuthenticationManager(final AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    public String getToken() throws AuthenticatorException, OperationCanceledException, IOException {
        final Account[] accounts = this.accountManager.getAccountsByType(AccountGeneral.ACCOUNT_TYPE);
        if (accounts.length != 1) {
            return null;
        }
        final boolean doNotNotifyAuthFailure = false;
        return this.accountManager.blockingGetAuthToken(
                accounts[0],
                AccountGeneral.AUTHTOKEN_TYPE_STUDENT_ACCESS,
                doNotNotifyAuthFailure);
    }
}
