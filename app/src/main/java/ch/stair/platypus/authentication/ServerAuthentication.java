package ch.stair.platypus.authentication;

import ch.stair.platypus.rest.UserHandling;

public class ServerAuthentication implements ServerAuthenticate {
    @Override
    public String userSignUp(String email, String pass) throws Exception {
        String token = null;
        UserHandling userHandling = new UserHandling();
        if(userHandling.createNewUserBlocking(email,pass)){
            token = userSignIn(email,pass);
        }
        return token;
    }

    @Override
    public String userSignIn(String email, String pass) throws Exception {
        UserHandling userHandling = new UserHandling();
        return userHandling.loginUserBLOCKING(email,pass);
    }
}
