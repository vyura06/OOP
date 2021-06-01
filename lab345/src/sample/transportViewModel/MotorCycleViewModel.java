package sample.transportViewModel;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import sample.App;
import transport.Transport;
import transport.land.MotorCycle;

public class MotorCycleViewModel implements ViewModel {
    public static MotorCycleViewModel create() {
        return App.create("fxml/transport/motorCycle.fxml");
    }

    @FXML
    private GridPane root;
    @FXML
    private TextField width, height, depth, maxSpeed, teamCount, passagesCount,
            minWeight, maxWeight, accelerationTimeTo100, mark;

    private MotorCycle v;

    public GridPane getRoot() {
        return root;
    }

    public Transport getTransport() {
        return v;
    }
    public void setTransport(Transport t) {
        v = ((MotorCycle) t);
        getRoot().setDisable(v == null);
        if (v == null)
            return;
        width.setText(String.valueOf(v.getWidth()));
        height.setText(String.valueOf(v.getHeight()));
        depth.setText(String.valueOf(v.getDepth()));
        maxSpeed.setText(String.valueOf(v.getMaxSpeed()));
        teamCount.setText(String.valueOf(v.getTeamCount()));
        passagesCount.setText(String.valueOf(v.getPassagesCount()));
        minWeight.setText(String.valueOf(v.getMinWeight()));
        maxWeight.setText(String.valueOf(v.getMaxWeight()));
        accelerationTimeTo100.setText(String.valueOf(v.getAccelerationTimeTo100()));
        mark.setText(String.valueOf(v.getMark()));
    }

    @FXML
    private void initialize() {
        setOnEnterIntSetter(width, width1 -> v.setWidth(width1));
        setOnEnterIntSetter(height, height1 -> v.setHeight(height1));
        setOnEnterIntSetter(depth, depth1 -> v.setDepth(depth1));
        setOnEnterIntSetter(maxSpeed, maxSpeed1 -> v.setMaxSpeed(maxSpeed1));
        setOnEnterIntSetter(teamCount, teamCount1 -> v.setTeamCount(teamCount1));
        setOnEnterIntSetter(passagesCount, passagesCount1 -> v.setPassagesCount(passagesCount1));
        setOnEnterIntSetter(minWeight, minWeight1 -> v.setMinWeight(minWeight1));
        setOnEnterIntSetter(maxWeight, maxWeight1 -> v.setMaxWeight(maxWeight1));
        setOnEnterDoubleSetter(accelerationTimeTo100, accelerationTimeTo1001 -> v.setAccelerationTimeTo100(accelerationTimeTo1001));
        setOnEnterStringSetter(mark, mark1 -> v.setMark(mark1));
    }
}