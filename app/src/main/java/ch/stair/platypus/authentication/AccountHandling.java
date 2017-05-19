package ch.stair.platypus.authentication;

import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

public class AccountHandling {
    private AccountManager mAccountManager;
    public AccountHandling(Context context){
        mAccountManager = AccountManager.get(context);
    }

    /**
     * Get an auth token for the account.
     * If not exist - add it and then return its auth token.
     * If one exist - return its auth token.
     * If more than one exists - show a picker and return the select account's auth token.
     * @param accountType
     * @param authTokenType
     */
    public void getTokenForAccountCreateIfNeeded(String accountType, String authTokenType, Activity test,View v) {
        final AccountManagerFuture<Bundle> future = mAccountManager.getAuthTokenByFeatures(accountType, authTokenType, null, test, null, null,
                new AccountManagerCallback<Bundle>() {
                    @Override
                    public void run(AccountManagerFuture<Bundle> future) {
                        Bundle bnd = null;
                        try {
                            bnd = future.getResult();
                            final String authtoken = bnd.getString(AccountManager.KEY_AUTHTOKEN);
                            Log.d("platypus", "GetTokenForAccount Bundle is " + bnd);
                            Snackbar.make(v, "Logged in", Snackbar.LENGTH_LONG).show();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                , null);
    }

    public static boolean isLoggedIn;

}
