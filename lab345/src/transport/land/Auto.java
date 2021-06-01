package transport.land;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class Auto extends LandTransport {
    private float accelerationTimeTo100;
    private String mark, transmissionType;

    public Auto() {
    }
    public Auto(int width, int height, int depth,
                int maxSpeed,
                int teamCount, int passagesCount,
                int minWeight, int maxWeight,
                double accelerationTimeTo100, String mark, String transmissionType) {
        super(width, height, depth, maxSpeed, teamCount, passagesCount, minWeight, maxWeight);
        setAccelerationTimeTo100(accelerationTimeTo100);
        setMark(mark);
        setTransmissionType(transmissionType);
    }

    public double getAccelerationTimeTo100() {
        return accelerationTimeTo100;
    }
    public void setAccelerationTimeTo100(double accelerationTimeTo100) {
        if (accelerationTimeTo100 < 0.0)
            throw new IllegalArgumentException("Negative value");
        this.accelerationTimeTo100 = (float) accelerationTimeTo100;
    }

    public String getMark() {
        return mark;
    }
    public void setMark(String mark) {
        if (mark == null)
            throw new NullPointerException();
        this.mark = mark;
    }

    public String getTransmissionType() {
        return transmissionType;
    }
    public void setTransmissionType(String transmissionType) {
        if (transmissionType == null)
            throw new NullPointerException();
        this.transmissionType = transmissionType;
    }

    @Override
    public void writeProps(XMLStreamWriter w) throws XMLStreamException {
        super.writeProps(w);
        writeElement(w, "AccelerationTimeTo100", getAccelerationTimeTo100());
        writeElement(w, "Mark", getMark());
        writeElement(w, "TransmissionType", getTransmissionType());
    }

    @Override
    protected boolean readProps(String key, String val) {
        if (super.readProps(key, val))
            return true;
        switch (key) {
            case "AccelerationTimeTo100":
                setAccelerationTimeTo100(Double.parseDouble(val));
                break;
            case "Mark":
                setMark(val);
                break;
            case "TransmissionType":
                setTransmissionType(val);
                break;
            default:
                return false;
        }
        return true;
    }

    public String toString() {
        return "Auto{" +
                "width=" + getWidth() +
                ", height=" + getHeight() +
                ", depth=" + getDepth() +
                ", maxSpeed=" + getMaxSpeed() +
                ", teamCount=" + getTeamCount() +
                ", passagesCount=" + getPassagesCount() +
                ", minWeight=" + getMinWeight() +
                ", maxWeight=" + getMaxWeight() +
                ", accelerationTimeTo100=" + getAccelerationTimeTo100() +
                ", mark='" + getMark() + '\'' +
                ", transmissionType='" + getTransmissionType() + '\'' +
                '}';
    }
}