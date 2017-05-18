package ch.stair.platypus.authentication;

public interface ServerAuthenticate {
    String userSignUp(final String email, final String pass) throws Exception;
    String userSignIn(final String email, final String pass) throws Exception;
}
