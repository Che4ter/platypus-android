package ch.stair.platypus.presentation.card;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import ch.stair.platypus.R;
import ch.stair.platypus.di.HasComponent;
import ch.stair.platypus.di.components.FeedbackComponent;
import ch.stair.platypus.domain.FeedbackModel;

public class CardContentFragment extends Fragment implements FeedbackCardView {

    private RecyclerView recyclerView;

    @Inject
    FeedbackPresenter feedbackPresenter;

    @Inject
    CardAdapter cardAdapter;

    @Override
    public View onCreateView(
            final LayoutInflater inflater,
            final ViewGroup container,
            final Bundle savedInstanceState) {

        this.cardAdapter.setListener(this.cardViewListener);

        this.recyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.recyclerView.setAdapter(this.cardAdapter);

        return this.recyclerView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.feedbackPresenter.setView(this);
        this.feedbackPresenter.showFeedbacks();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FeedbackComponent.class.cast(((HasComponent<FeedbackComponent>) getActivity())
                .getComponent())
                .inject(this);
        this.feedbackPresenter.initialize();
    }

    @Override
    public void showTestSnackbar(FeedbackModel feedbackModel) {
        String s = String.format(
                Locale.getDefault(),
                "id: %d, text: %s",
                feedbackModel.getId(),
                feedbackModel.getText());
        Snackbar.make(this.recyclerView, s, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showTestSnackbarForVoteUp(FeedbackModel feedbackModel) {
        String text = String.format(
                Locale.getDefault(),
                "up vote on '%s' was pressed. VoteCount: %s",
                feedbackModel.getText(),
                feedbackModel.getVoteCount());
        Snackbar.make(this.recyclerView, text, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showTestSnackbarForVoteDown(FeedbackModel feedbackModel) {
        String text = String.format(
                Locale.getDefault(),
                "down vote on '%s' was pressed. VoteCount: %s",
                feedbackModel.getText(),
                feedbackModel.getVoteCount());
        Snackbar.make(this.recyclerView, text, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void renderFeedbackModels(List<FeedbackModel> feedbackModelList) {
        this.cardAdapter.setViewModels(feedbackModelList);
    }

    private final CardViewListener cardViewListener =
            new CardViewListener() {
                @Override
                public void onCardClicked(final FeedbackModel feedbackModel) {
                    if (CardContentFragment.this.feedbackPresenter != null && feedbackModel != null) {
                        CardContentFragment.this.feedbackPresenter.onCardViewClicked(feedbackModel);
                    }
                }

                @Override
                public void voteUpClicked(final FeedbackModel feedbackModel) {
                    if (CardContentFragment.this.feedbackPresenter != null && feedbackModel != null) {
                        CardContentFragment.this.feedbackPresenter.onVoteUpClicked(feedbackModel);
                    }
                }

                @Override
                public void voteDownClicked(final FeedbackModel feedbackModel) {
                    if (CardContentFragment.this.feedbackPresenter != null && feedbackModel != null) {
                        CardContentFragment.this.feedbackPresenter.onVoteDownClicked(feedbackModel);
                    }
                }
            };
}


