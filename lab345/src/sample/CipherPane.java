package sample;

import javafx.scene.control.TitledPane;
import sample.cipherViewModel.CipherViewModel;

import java.util.Objects;

class CipherPane extends TitledPane {
    private final CipherViewModel cipherViewModel;

    CipherPane(String title, CipherViewModel viewModel) {
        this.cipherViewModel = Objects.requireNonNull(viewModel);
        setContent(viewModel.getRoot());
        setText(title);
    }

    public CipherViewModel getCipherViewModel() {
        return cipherViewModel;
    }
}
