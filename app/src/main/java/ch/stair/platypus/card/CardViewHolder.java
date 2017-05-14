package ch.stair.platypus.card;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

import ch.stair.platypus.R;

class CardViewHolder extends RecyclerView.ViewHolder {

    private final CardView card;
    private final TextView name;
    private final TextView description;
    private final CardViewHolderClickListener cardViewHolderClickListener;
    private long id = -1;

    public CardViewHolder(final View cardView, final CardViewHolderClickListener cardViewHolderClickListener) {
        super(cardView);

        this.name = (TextView) cardView.findViewById(R.id.card_title);
        this.description = (TextView) cardView.findViewById(R.id.card_text);
        this.card = (CardView) cardView.findViewById(R.id.card_view);
        this.cardViewHolderClickListener = cardViewHolderClickListener;

        setupOnClickForExampleButtons(cardView);
        setCardBackgroundToRandomColor(cardView);
    }

    public void bindData(final CardViewModel viewModel) {
        this.id = viewModel.getId();
        this.name.setText(viewModel.getName());
        this.description.setText(viewModel.getDescription());
    }

    private void setupOnClickForExampleButtons(final View cardView) {
        cardView.setOnClickListener(v -> this.cardViewHolderClickListener.itemClicked(v, this.id));

        Button button = (Button)cardView.findViewById(R.id.action_button);
        button.setOnClickListener(this.cardViewHolderClickListener::actionButtonClicked);

        ImageButton favoriteImageButton = (ImageButton) cardView.findViewById(R.id.favorite_button);
        favoriteImageButton.setOnClickListener(this.cardViewHolderClickListener::favoriteButtonClicked);

        ImageButton shareImageButton = (ImageButton) cardView.findViewById(R.id.reply_button);
        shareImageButton.setOnClickListener(this.cardViewHolderClickListener::shareButtonClicked);
    }

    private void setCardBackgroundToRandomColor(final View itemView) {
        final int[] colors = itemView
                .getContext()
                .getResources()
                .getIntArray(R.array.card_backgrounds);
        final int randomColor = getRandomItem(colors);
        card.setCardBackgroundColor(randomColor);
    }

    private static int getRandomItem(final int[] item) {
        final int i = new Random().nextInt(item.length - 1);
        return item[i];
    }
}
