package ch.stair.platypus.domain;

public class CreateFeedbackModel {
    private final String text;

    public CreateFeedbackModel(final String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
