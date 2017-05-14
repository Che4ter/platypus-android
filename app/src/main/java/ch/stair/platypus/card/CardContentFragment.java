package ch.stair.platypus.card;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.stream.Collectors;

import ch.stair.platypus.App;
import ch.stair.platypus.R;
import ch.stair.platypus.models.Comments;
import ch.stair.platypus.models.Feedback;

public class CardContentFragment extends Fragment {

    @Override
    public View onCreateView(
            final LayoutInflater inflater,
            final ViewGroup container,
            final Bundle savedInstanceState) {

        final List<Feedback> comments = ((App) getActivity().getApplication())
                .getBoxStore()
                .boxFor(Feedback.class)
                .getAll();
        final List<CardViewModel> cardViewModels = comments
                .stream()
                .map(x ->
                    new CardViewModel(x.getId(), x.getFeedbackText(), x.getCreationDate(), x.getVotesCount()))
                .collect(Collectors.toList());
        final CardAdapter adapter = new CardAdapter(cardViewModels);

        final RecyclerView recyclerView =
                (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        return recyclerView;
    }
}


