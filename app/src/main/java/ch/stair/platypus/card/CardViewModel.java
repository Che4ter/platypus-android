package ch.stair.platypus.card;

import java.util.Date;

class CardViewModel {
    private final long id;
    private final Date createdOn;
    private final String text;
    private final int voteCount;

    CardViewModel(final long id, final String text, final Date createdOn, final int voteCount) {
        this.id = id;
        this.text = text;
        this.createdOn = createdOn;
        this.voteCount = voteCount;
    }

    long getId() {
        return id;
    }

    String getText() {
        return text;
    }

    Date getCreatedOn() {
        return createdOn;
    }

    int getVoteCount() {
        return voteCount;
    }
}
