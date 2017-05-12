package ch.stair.platypus.card;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ch.stair.platypus.R;

class CardAdapter extends RecyclerView.Adapter<CardViewHolder> {

    final private List<CardViewModel> comments;

    public CardAdapter(final List<CardViewModel> comments) {
        super();
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
        holder.bindData(this.comments.get(position));
    }

    @Override
    public int getItemCount() {
        return this.comments.size();
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.item_card;
    }
}
