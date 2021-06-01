package sample.cipherViewModel;

import cipher.RSACipher;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import sample.App;

import java.math.BigInteger;
import java.util.function.Consumer;

public class RSAViewModel implements CipherViewModel {
    public static RSAViewModel create() {
        return App.create("fxml/cipher/rsa.fxml");
    }

    private static EventHandler<KeyEvent> create(TextField f, Consumer<BigInteger> setter) {
        return keyEvent -> {
            try {
                setter.accept(new BigInteger(f.getText().trim()));
            } catch (Exception ignored) {
                setter.accept(BigInteger.ZERO);
            }
        };
    }

    @FXML
    private VBox root;
    @FXML
    private TextField pTF, qTF, eTF;

    private BigInteger p, q, e;
    private RSACipher cipher;

    public RSACipher get() {
        if (cipher == null)
            cipher = new RSACipher(p, q, e);
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
        p = new BigInteger(pTF.getText().trim());
        q = new BigInteger(qTF.getText().trim());
        e = new BigInteger(eTF.getText().trim());

        pTF.setOnKeyReleased(create(pTF, bi -> {
            p = bi;
            invalidate();
        }));
        qTF.setOnKeyReleased(create(qTF, bi -> {
            q = bi;
            invalidate();
        }));
        eTF.setOnKeyReleased(create(eTF, bi -> {
            e = bi;
            invalidate();
        }));
    }
}