package ch.stair.platypus;

import java.util.Date;
import java.util.Random;

import ch.stair.platypus.models.Comments;
import io.objectbox.Box;

/**
 * Created by philipp on 10.05.17.
 */

public class InsertDummyData {

    private Box<Comments> mCommentsBox;

    private final String dummyText = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.";

    public InsertDummyData(Box<Comments> commentsBox) {
        mCommentsBox = commentsBox;
    }

    public void InsertComments(Integer count)
    {
        Random rand = new Random();

        int  length = rand.nextInt(dummyText.length()) + 15;
        for(int i=0; i<count;i++)
        {
            Comments newComment = new Comments();
            newComment.setComment_text(dummyText.substring(0,length));
            newComment.setCreated(new Date());
            newComment.setDeleted(false);
            mCommentsBox.put(newComment);
            length = rand.nextInt(dummyText.length()) + 15;
        }

    }
}
