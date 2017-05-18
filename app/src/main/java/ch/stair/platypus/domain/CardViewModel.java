package ch.stair.platypus.domain;

public class CardViewModel {
    private final long id;
    private final String createdOn;
    private final String text;
    private final int voteCount;

    public CardViewModel(final long id, final String text, final String createdOn, final int voteCount) {
        this.id = id;
        this.text = text;
        this.createdOn = createdOn;
        this.voteCount = voteCount;
    }

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public int getVoteCount() {
        return voteCount;
    }
}
