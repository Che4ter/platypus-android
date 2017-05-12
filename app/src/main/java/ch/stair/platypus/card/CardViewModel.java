package ch.stair.platypus.card;

class CardViewModel {
    private final String name;
    private final String description;

    CardViewModel(final String name, final String description) {
        this.name = name;
        this.description = description;
    }

    String getName() {
        return name;
    }

    String getDescription() {
        return description;
    }
}
