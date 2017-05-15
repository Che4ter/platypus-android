package ch.stair.platypus.mapper;

import ch.stair.platypus.models.Hashtag;
import ch.stair.platypus.models.HashtagPOJO;

public final class HashtagMapper {
    private HashtagMapper(){}

    public static Hashtag mapHashtagPOJOToHashtag(HashtagPOJO hashtagPOJO){
        return new Hashtag(hashtagPOJO.getId(),
                hashtagPOJO.getHashText(),
                hashtagPOJO.getHashTypesId(),
                hashtagPOJO.getLastModified());
    }
}
