package ch.stair.platypus.card;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.view.View;

import ch.stair.platypus.DetailActivity;

class CardViewHolderClickListenerImpl implements CardViewHolderClickListener {
    @Override
    public void itemClicked(View cardView, long id) {
        Context context = cardView.getContext();
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_ID, id);
        context.startActivity(intent);
    }

    @Override
    public void actionButtonClicked(View cardView) {
        Snackbar.make(cardView, "Action is pressed", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void favoriteButtonClicked(View cardView) {
        Snackbar.make(cardView, "Added to Favorite", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void shareButtonClicked(View cardView) {
        Snackbar.make(cardView, "Share article", Snackbar.LENGTH_LONG).show();
    }
}
