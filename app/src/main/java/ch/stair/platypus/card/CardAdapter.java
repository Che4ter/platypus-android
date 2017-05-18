package ch.stair.platypus.card;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import ch.stair.platypus.R;
import ch.stair.platypus.domain.CardViewModel;

class CardAdapter extends RecyclerView.Adapter<CardViewHolder> {
    private List<CardViewModel> comments;
    private CardViewListener listener;

    CardAdapter(final Context context) {
        this.comments = Collections.emptyList();
    }

    void setViewModels(final List<CardViewModel> comments) {
        this.comments = comments;
    }

    @Override
    public CardViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final CardViewHolderClickListener cardViewHolderClickListener = new CardViewHolderClickListenerImpl();
        final View view = LayoutInflater
                .from(parent.getContext())
                .inflate(viewType, parent, false);
        return new CardViewHolder(view, cardViewHolderClickListener);
    }

    @Override
    public void onBindViewHolder(final CardViewHolder holder, final int position) {
        CardViewModel viewModel = this.comments.get(position);
        holder.bindData(viewModel);
        holder.card.setOnClickListener(v -> {
            if (this.listener != null) {
                this.listener.onCardClicked(viewModel);
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
