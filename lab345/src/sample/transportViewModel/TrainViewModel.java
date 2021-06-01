package sample.transportViewModel;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import sample.App;
import transport.Transport;
import transport.land.Train;

public class TrainViewModel implements ViewModel {
    public static TrainViewModel create() {
        return App.create("fxml/transport/train.fxml");
    }

    @FXML
    private GridPane root;
    @FXML
    private TextField width, height, depth, maxSpeed, teamCount, passagesCount, wagonsCount;

    private Train v;

    public GridPane getRoot() {
        return root;
    }

    public Transport getTransport() {
        return v;
    }
    public void setTransport(Transport t) {
        v = ((Train) t);
        getRoot().setDisable(v == null);
        if (v == null)
            return;
        width.setText(String.valueOf(v.getWidth()));
        height.setText(String.valueOf(v.getHeight()));
        depth.setText(String.valueOf(v.getDepth()));
        maxSpeed.setText(String.valueOf(v.getMaxSpeed()));
        teamCount.setText(String.valueOf(v.getTeamCount()));
        passagesCount.setText(String.valueOf(v.getPassagesCount()));
        wagonsCount.setText(String.valueOf(v.getWagonsCount()));
    }

    @FXML
    private void initialize() {
        setOnEnterIntSetter(width, width1 -> v.setWidth(width1));
        setOnEnterIntSetter(height, height1 -> v.setHeight(height1));
        setOnEnterIntSetter(depth, depth1 -> v.setDepth(depth1));
        setOnEnterIntSetter(maxSpeed, maxSpeed1 -> v.setMaxSpeed(maxSpeed1));
        setOnEnterIntSetter(teamCount, teamCount1 -> v.setTeamCount(teamCount1));
        setOnEnterIntSetter(passagesCount, passagesCount1 -> v.setPassagesCount(passagesCount1));
        setOnEnterIntSetter(wagonsCount, wagonsCount1 -> v.setWagonsCount(wagonsCount1));
    }
}