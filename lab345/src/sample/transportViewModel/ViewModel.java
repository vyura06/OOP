package sample.transportViewModel;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import sample.App;
import sample.Controller;
import transport.Transport;

import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;

public interface ViewModel extends Controller {
    Transport getTransport();
    void setTransport(Transport t);

    default void setOnEnterIntSetter(TextField f, IntConsumer setter) {
        f.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                try {
                    setter.accept(Integer.parseInt(f.getText().trim()));
                    App.getMain().refresh();
                } catch (Exception ex) {
                    App.getMain().handle(ex);
                }
            }
        });
    }
    default void setOnEnterDoubleSetter(TextField f, DoubleConsumer setter) {
        f.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                try {
                    setter.accept(Double.parseDouble(f.getText().trim()));
                    App.getMain().refresh();
                } catch (Exception ex) {
                    App.getMain().handle(ex);
                }
            }
        });
    }
    default void setOnEnterStringSetter(TextField f, Consumer<String> setter) {
        f.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                try {
                    setter.accept(f.getText().trim());
                    App.getMain().refresh();
                } catch (Exception ex) {
                    App.getMain().handle(ex);
                }
            }
        });
    }
}