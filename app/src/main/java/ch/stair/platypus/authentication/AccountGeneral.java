package ch.stair.platypus.authentication;

public class AccountGeneral {

    /**
     * Account type id
     */
    public static final String ACCOUNT_TYPE = "ch.stair.platypus";

    /**
     * Account name
     */
    public static final String ACCOUNT_NAME = "Platypus";

    /**
     * Auth token types
     */
    public static final String AUTHTOKEN_TYPE_STUDENT_ACCESS = "Student access";

    public static final ServerAuthenticate sServerAuthenticate = new ServerAuthentication();

    public static boolean UserLoggedIn;

}
