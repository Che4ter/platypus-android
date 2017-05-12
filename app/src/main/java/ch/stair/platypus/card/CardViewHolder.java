package ch.stair.platypus.card;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

import ch.stair.platypus.DetailActivity;
import ch.stair.platypus.R;

class CardViewHolder extends RecyclerView.ViewHolder {

    final private CardView card;
    final private TextView name;
    final private TextView description;

    public CardViewHolder(final View itemView) {
        super(itemView);

        this.name = (TextView) itemView.findViewById(R.id.card_title);
        this.description = (TextView) itemView.findViewById(R.id.card_text);
        this.card = (CardView) itemView.findViewById(R.id.card_view);

        setupOnClickForExampleButtons(itemView);
        setCardBackgroundToRandomColor(itemView);
    }

    public void bindData(final CardViewModel viewModel) {
        this.name.setText(viewModel.getName());
        this.description.setText(viewModel.getDescription());
    }

    private void setupOnClickForExampleButtons(final View itemView) {
        itemView.setOnClickListener(v -> {
            Context context = v.getContext();
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_POSITION, getAdapterPosition());
            context.startActivity(intent);
        });

        Button button = (Button)itemView.findViewById(R.id.action_button);
        button.setOnClickListener(v ->
                Snackbar.make(v, "Action is pressed", Snackbar.LENGTH_LONG).show());

        ImageButton favoriteImageButton = (ImageButton) itemView.findViewById(R.id.favorite_button);
        favoriteImageButton.setOnClickListener(v ->
                Snackbar.make(v, "Added to Favorite", Snackbar.LENGTH_LONG).show());

        ImageButton shareImageButton = (ImageButton) itemView.findViewById(R.id.reply_button);
        shareImageButton.setOnClickListener(v ->
                Snackbar.make(v, "Share article", Snackbar.LENGTH_LONG).show());
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
