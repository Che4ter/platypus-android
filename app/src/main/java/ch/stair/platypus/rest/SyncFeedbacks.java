package ch.stair.platypus.rest;

import android.util.Log;

import java.util.Date;
import java.util.List;

import ch.stair.platypus.App;
import ch.stair.platypus.models.Feedback;
import ch.stair.platypus.models.FeedbackHashtag;
import ch.stair.platypus.models.FeedbackHashtag_;
import ch.stair.platypus.models.FeedbackPOJO;
import ch.stair.platypus.models.Feedback_;
import ch.stair.platypus.models.Hashtag;
import ch.stair.platypus.models.HashtagPOJO;
import ch.stair.platypus.models.Hashtag_;
import io.objectbox.Box;
import io.objectbox.exception.DbException;
import io.objectbox.query.Query;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SyncFeedbacks {

    final private PlatypusClient client;
    final private Box<Feedback> feedbackBox;
    final private Box<Hashtag> hashtagBox;
    final private Box<FeedbackHashtag> feedbackHashtagBox;

    public SyncFeedbacks(App applicationContext) {

        feedbackBox = applicationContext
                .getBoxStore()
                .boxFor(Feedback.class);

        hashtagBox = applicationContext
                .getBoxStore()
                .boxFor(Hashtag.class);

        feedbackHashtagBox = applicationContext
                .getBoxStore()
                .boxFor(FeedbackHashtag.class);

        client = ServiceGenerator.createService(PlatypusClient.class);
    }

    public void fetchLatestFeedbacksToDB()
    {
        Call<List<FeedbackPOJO>> call = client.getFeedback();
        call.enqueue(new Callback<List<FeedbackPOJO>>() {

            @Override
            public void onResponse(Call<List<FeedbackPOJO>> call, Response<List<FeedbackPOJO>> response) {
                if (response.isSuccessful()) {
                    // tasks available
                    Log.d("Debug", "Response is Successful");
                    if(!response.body().isEmpty())
                    {
                        saveNewFeedbacksToDB(response.body());
                    }

                } else {
                    // error response, no access to resource?
                    Log.d("Error", "Error in Response");

                }
            }

            @Override
            public void onFailure(Call<List<FeedbackPOJO>> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

    private void saveNewFeedbacksToDB(List<FeedbackPOJO> newFeedbacks)
    {
        Query<Feedback> queryFeedback = feedbackBox.query().equal(Feedback_.id, 0).build();
        Query<Hashtag> queryHashtag = hashtagBox.query().equal(Hashtag_.id, 0).build();
        Query<FeedbackHashtag> queryFeedbackHashtag = feedbackHashtagBox.query().equal(FeedbackHashtag_.feedbackId, 0).and().equal(FeedbackHashtag_.hashtagId, 0).build();

        for(FeedbackPOJO updatedFeedback : newFeedbacks)
        {
            try
            {

                Feedback feedbackToModify =  queryFeedback.setParameter(Feedback_.id, updatedFeedback.getId()).findUnique();
                if(feedbackToModify == null)
                {
                    feedbackToModify = new Feedback();
                    feedbackToModify.setId(updatedFeedback.getId());
                }

                feedbackToModify.setFeedbackText(updatedFeedback.getFeedbackText());
                feedbackToModify.setLastModified(updatedFeedback.getLastModified());
                feedbackToModify.setVotesCount(updatedFeedback.getVotesCount());
                feedbackToModify.setCreationDate(updatedFeedback.getCreationDate());
                if(updatedFeedback.getParentId() != null)
                {
                    feedbackToModify.setParentId(updatedFeedback.getParentId());
                }
                feedbackBox.put(feedbackToModify);

                for(HashtagPOJO updatedHashtag : updatedFeedback.getHashtags())
                {
                    Hashtag hashtagToModify =  queryHashtag.setParameter(Hashtag_.id, updatedHashtag.getId()).findUnique();
                    if(hashtagToModify == null)
                    {
                        hashtagToModify = new Hashtag();
                        hashtagToModify.setId(hashtagToModify.getId());
                    }
                    hashtagToModify.setHashText(updatedHashtag.getHashText());
                    hashtagToModify.setHashTypesId(updatedHashtag.getHashTypesId());
                    hashtagBox.put(hashtagToModify);

                    FeedbackHashtag hashtagInFeedback = queryFeedbackHashtag
                            .setParameter(FeedbackHashtag_.feedbackId, updatedFeedback.getId())
                            .setParameter(FeedbackHashtag_.hashtagId, hashtagToModify.getId())
                            .findUnique();
                    if(hashtagInFeedback == null)
                    {
                        FeedbackHashtag newHashtagInFeedback = new FeedbackHashtag();
                        newHashtagInFeedback.setFeedback(feedbackToModify);
                        newHashtagInFeedback.setHashtag(hashtagToModify);
                        newHashtagInFeedback.setLastchanged(new Date());
                        feedbackHashtagBox.put(newHashtagInFeedback);
                    }

                }
            }
            catch(DbException e)
            {
                Log.d("Error","Feedback ID not unique: " + e.getMessage());
            }
            catch (Exception ex)
            {
                Log.d("Error", ex.getMessage());
            }
        }
    }
}
