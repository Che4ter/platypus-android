package ch.stair.platypus.card;

import android.view.View;

interface CardViewHolderClickListener {
    void itemClicked(View cardView, long id);
    void actionButtonClicked(View cardView);
}
