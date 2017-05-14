package ch.stair.platypus.card;

class CardViewModel {
    private final long id;
    private final String createdOn;
    private final String text;
    private final int voteCount;

    CardViewModel(final long id, final String text, final String createdOn, final int voteCount) {
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

    String getCreatedOn() {
        return createdOn;
    }

    int getVoteCount() {
        return voteCount;
    }
}
