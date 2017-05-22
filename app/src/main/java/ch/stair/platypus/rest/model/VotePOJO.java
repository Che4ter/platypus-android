package ch.stair.platypus.rest.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class VotePOJO {

    @SerializedName("direction")
    private long direction;


    public VotePOJO(Integer direction) {
        this.direction = direction;
    }

}
