package ch.stair.platypus;

import java.util.Date;
import java.util.Random;

import ch.stair.platypus.models.Comments;
import io.objectbox.Box;

class InsertDummyData {

    final private Box<Comments> commentsBox;

    InsertDummyData(final Box<Comments> commentsBox) {
        this.commentsBox = commentsBox;
    }

    void insertComments() {
        final Integer count = 14;

        final String dummyText = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, " +
                "sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, " +
                "sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. " +
                "Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. " +
                "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, " +
                "sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, " +
                "sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. " +
                "Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.";

        final Random rand = new Random();

        for(int i=0; i<count;i++)
        {
            final int idSoDbWillAssignANewId = 0;
            final int substringEnd = rand.nextInt(dummyText.length());
            final String text = dummyText.substring(0, substringEnd);
            final Date now = new Date();
            final Comments newComment = new Comments(idSoDbWillAssignANewId, text, now);
            commentsBox.put(newComment);
        }
    }
}
