package sample.cipherViewModel;

import cipher.BitCipher;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import sample.App;

public class BitViewModel implements CipherViewModel {
    public static BitViewModel create() {
        return App.create("fxml/cipher/bit.fxml");
    }

    @FXML
    private VBox root;
    @FXML
    private TextField keyTF;

    private BitCipher cipher;

    public BitCipher get() {
        if (cipher == null) {
            String s = keyTF.getText().trim();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (Character.isDigit(c))
                    sb.append(c);
            }
            s = sb.toString();
            keyTF.setText(s);
            cipher = new BitCipher(Integer.parseInt(s));
        }
        return cipher;
    }

    public Pane getRoot() {
        return root;
    }

    private void invalidate() {
        cipher = null;
    }

    @FXML
    private void initialize() {
        keyTF.setOnKeyPressed(event -> invalidate());
    }
}