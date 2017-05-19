package ch.stair.platypus.rest.model;

import com.google.gson.annotations.SerializedName;

public class RegistrationLoginPOJO {

    @SerializedName("mailaddress")
    private String emailAddress;

    @SerializedName("password")
    private String password;

    public RegistrationLoginPOJO(String emailAddress, String password) {
        this.emailAddress = emailAddress;
        this.password = password;
    }
}
