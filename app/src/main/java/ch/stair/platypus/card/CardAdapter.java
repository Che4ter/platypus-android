package ch.stair.platypus.card;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import ch.stair.platypus.R;
import ch.stair.platypus.domain.FeedbackModel;

class CardAdapter extends RecyclerView.Adapter<CardViewHolder> {
    private List<FeedbackModel> comments;
    private CardViewListener listener;

    @Inject CardAdapter() {
        this.comments = Collections.emptyList();
    }

    void setViewModels(final List<FeedbackModel> comments) {
        this.comments = comments;
    }

    @Override
    public CardViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater
                .from(parent.getContext())
                .inflate(viewType, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CardViewHolder holder, final int position) {
        FeedbackModel viewModel = this.comments.get(position);
        holder.bindData(viewModel);

        holder.card.setOnClickListener(v -> {
            if (this.listener != null) {
                this.listener.onCardClicked(viewModel);
            }
        });
        holder.voteUp.setOnClickListener(v -> {
            if (this.listener != null) {
                this.listener.voteUpClicked(v, viewModel);
            }
        });
        holder.voteDown.setOnClickListener(v -> {
            if (this.listener != null) {
                this.listener.voteDownClicked(v, viewModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.comments.size();
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.item_card;
    }

    void setListener(CardViewListener listener) {
        this.listener = listener;
    }
}
