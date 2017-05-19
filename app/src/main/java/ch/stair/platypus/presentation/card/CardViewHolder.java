package ch.stair.platypus.presentation.card;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

import ch.stair.platypus.R;
import ch.stair.platypus.domain.FeedbackModel;

class CardViewHolder extends RecyclerView.ViewHolder {

    final CardView card;
    private final TextView text;
    private final TextView creationDate;
    private final TextView voteCount;
    final ImageButton voteUp;
    final ImageButton voteDown;
    private final ReadableDateConverter readableDateConverter;

    public CardViewHolder(final View cardView, final ReadableDateConverter readableDateConverter) {
        super(cardView);

        this.card = (CardView) cardView.findViewById(R.id.card_view);
        this.text = (TextView) cardView.findViewById(R.id.card_text);
        this.creationDate = (TextView) cardView.findViewById(R.id.card_creationDate);
        this.voteCount = (TextView) cardView.findViewById(R.id.card_voteCount);
        this.voteUp = (ImageButton) cardView.findViewById(R.id.upvote_button);
        this.voteDown = (ImageButton) cardView.findViewById(R.id.downvote_button);
        this.readableDateConverter = readableDateConverter;

        setCardBackgroundToRandomColor(cardView);
    }

    public void bindData(final FeedbackModel viewModel) {
        this.text.setText(viewModel.getText());
        this.creationDate.setText(this.readableDateConverter.convert(viewModel.getCreatedOn()));
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
