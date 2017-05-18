package ch.stair.platypus.card;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Locale;

import ch.stair.platypus.ABC;
import ch.stair.platypus.App;
import ch.stair.platypus.ObjectBoxRepository;
import ch.stair.platypus.ObjectBoxStoreProvider;
import ch.stair.platypus.Presenter;
import ch.stair.platypus.R;
import ch.stair.platypus.domain.CardViewModel;
import ch.stair.platypus.domain.FeedbackInteractor;
import ch.stair.platypus.domain.ICardView;
import ch.stair.platypus.models.MyObjectBox;
import io.objectbox.BoxStore;

public class CardContentFragment extends Fragment implements ICardView {

    private final Presenter presenter;
    private final CardAdapter cardAdapter = null;
    private RecyclerView recyclerView;

    public CardContentFragment() {
//        final Context context = ABC.get();
//        this.cardAdapter = new CardAdapter(context);

        final BoxStore boxStore = ObjectBoxStoreProvider.provide();
        final ObjectBoxRepository objectBoxRepository = new ObjectBoxRepository(boxStore);
        final FeedbackInteractor feedbackInteractor = new FeedbackInteractor(objectBoxRepository);
        this.presenter = new Presenter(feedbackInteractor);
    }

    @Override
    public View onCreateView(
            final LayoutInflater inflater,
            final ViewGroup container,
            final Bundle savedInstanceState) {

        this.cardAdapter.setListener(cardViewModel -> {
            if (this.presenter != null && cardViewModel != null) {
                this.presenter.onCardViewClicked(cardViewModel);
            }
        });

        this.recyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.recyclerView.setAdapter(this.cardAdapter);

        return this.recyclerView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.presenter.setView(this);
        this.presenter.initialize();
    }

    @Override
    public void showTestSnackbar(CardViewModel cardViewModel) {
        String s = String.format(Locale.getDefault(), "id: %d, text: %s", cardViewModel.getId(), cardViewModel.getText());
        Snackbar.make(this.recyclerView, s, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void renderCardViewModels(List<CardViewModel> cardViewModels) {
        this.cardAdapter.setViewModels(cardViewModels);
    }
}


