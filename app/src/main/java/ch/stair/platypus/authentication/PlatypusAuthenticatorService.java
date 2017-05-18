package ch.stair.platypus.authentication;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class PlatypusAuthenticatorService extends Service {
    // Instance field that stores the authenticator object
    // Notice, this is the same Authenticator class we defined earlier
    @Override
    public IBinder onBind(Intent intent) {

        PlatypusAuthenticator authenticator = new PlatypusAuthenticator(this);
        return authenticator.getIBinder();
    }
}
