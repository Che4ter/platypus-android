package ch.stair.platypus.presentation.feedbackcreation;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import javax.inject.Inject;

import ch.stair.platypus.R;
import ch.stair.platypus.di.HasComponent;
import ch.stair.platypus.di.components.CreateFeedbackComponent;
import ch.stair.platypus.domain.CreateFeedbackModel;

public class CreateFeedbackFragment extends Fragment implements CreateFeedbackView {

    @Inject
    CreateFeedbackPresenter feedbackPresenter;
    private EditText text;

    @Override
    public View onCreateView(
            final LayoutInflater inflater,
            final ViewGroup container,
            final Bundle savedInstanceState) {

        final View fragmentView = inflater.inflate(R.layout.fragment_create_feedback, container, false);
        this.text = (EditText) fragmentView.findViewById(R.id.createFeedback_text);
        final Button createFeedbackButton = (Button) fragmentView.findViewById(R.id.createFeedback_button);
        createFeedbackButton.setOnClickListener(v -> this.onCreateFeedbackClick());

        return fragmentView;
    }

    private void onCreateFeedbackClick() {
        CreateFeedbackModel model = new CreateFeedbackModel(this.text.getText().toString());
        this.feedbackPresenter.createFeedback(model);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.feedbackPresenter.setView(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CreateFeedbackComponent.class.cast(((HasComponent<CreateFeedbackComponent>) getActivity())
                .getComponent())
                .inject(this);
    }

    @Override
    public void closeView() {
        this.getActivity().finish();
    }
}


