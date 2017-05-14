package ch.stair.platypus.card;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.view.View;

import java.util.Locale;

import ch.stair.platypus.DetailActivity;

class CardViewHolderClickListenerImpl implements CardViewHolderClickListener {
    @Override
    public void itemClicked(final View cardView, final long id) {
        final Context context = cardView.getContext();
        final Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_ID, id);
        context.startActivity(intent);
    }

    @Override
    public void actionButtonClicked(final View cardView) {
        Snackbar.make(cardView, "Action is pressed", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void upVoteClicked(final View cardView, final long id) {
        String text = String.format(Locale.getDefault(), "up vote on id ' %d' is pressed", id);
        Snackbar.make(cardView, text, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void downVoteClicked(final View cardView, final long id) {
        String text = String.format(Locale.getDefault(), "down vote on id '%d' is pressed", id);
        Snackbar.make(cardView, text, Snackbar.LENGTH_LONG).show();
    }
}
