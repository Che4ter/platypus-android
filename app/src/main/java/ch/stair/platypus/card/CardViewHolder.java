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
    private final TextView text;
    private final TextView creationDate;
    private final TextView voteCount;
    private final CardViewHolderClickListener cardViewHolderClickListener;
    private long id = -1;

    public CardViewHolder(final View cardView, final CardViewHolderClickListener cardViewHolderClickListener) {
        super(cardView);

        this.card = (CardView) cardView.findViewById(R.id.card_view);
        this.text = (TextView) cardView.findViewById(R.id.card_text);
        this.creationDate = (TextView) cardView.findViewById(R.id.card_creationDate);
        this.voteCount = (TextView) cardView.findViewById(R.id.card_voteCount);
        this.cardViewHolderClickListener = cardViewHolderClickListener;

        cardView.setOnClickListener(v -> this.cardViewHolderClickListener.itemClicked(v, this.id));
        Button actionButton = (Button) cardView.findViewById(R.id.action_button);
        actionButton.setOnClickListener(this.cardViewHolderClickListener::actionButtonClicked);
        ImageButton upVoteButton = (ImageButton) cardView.findViewById(R.id.upvote_button);
        upVoteButton.setOnClickListener(v -> this.cardViewHolderClickListener.upVoteClicked(v, this.id));
        ImageButton downVoteButton = (ImageButton) cardView.findViewById(R.id.downvote_button);
        downVoteButton.setOnClickListener(v -> this.cardViewHolderClickListener.downVoteClicked(v, this.id));

        setCardBackgroundToRandomColor(cardView);
    }

    public void bindData(final CardViewModel viewModel) {
        this.id = viewModel.getId();
        this.text.setText(viewModel.getText());
        this.creationDate.setText(viewModel.getCreatedOn().toString());
        this.voteCount.setText(String.valueOf(viewModel.getVoteCount()));
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
