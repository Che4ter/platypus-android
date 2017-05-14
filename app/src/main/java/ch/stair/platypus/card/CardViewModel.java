package ch.stair.platypus.card;

class CardViewModel {
    private final long id;
    private final String name;
    private final String description;

    CardViewModel(final long id, final String name, final String description) {
        this.name = name;
        this.description = description;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    String getName() {
        return name;
    }

    String getDescription() {
        return description;
    }
}
