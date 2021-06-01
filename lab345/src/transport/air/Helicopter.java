package transport.air;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class Helicopter extends AirTransport {
    private int rotorDiameter;

    public Helicopter() {
    }
    public Helicopter(int width, int height, int depth,
                      int minWeight, int maxWeight,
                      int maxSpeed,
                      int teamCount, int passagesCount,
                      int maximumFlightAltitude, int rotorDiameter) {
        super(width, height, depth, minWeight, maxWeight, maxSpeed, teamCount, passagesCount, maximumFlightAltitude);
        setRotorDiameter(rotorDiameter);
    }

    public int getRotorDiameter() {
        return rotorDiameter;
    }
    public void setRotorDiameter(int rotorDiameter) {
        checkNegative(rotorDiameter);
        this.rotorDiameter = rotorDiameter;
    }

    @Override
    public void writeProps(XMLStreamWriter w) throws XMLStreamException {
        super.writeProps(w);
        writeElement(w, "RotorDiameter", getRotorDiameter());
    }

    @Override
    protected boolean readProps(String key, String val) {
        if (super.readProps(key, val))
            return true;
        if ("RotorDiameter".equals(key)) {
            set(this::setRotorDiameter, val);
            return true;
        }
        return false;
    }

    public String toString() {
        return "Helicopter{" +
                "width=" + getWidth() +
                ", height=" + getHeight() +
                ", depth=" + getDepth() +
                ", maxSpeed=" + getMaxSpeed() +
                ", teamCount=" + getTeamCount() +
                ", passagesCount=" + getPassagesCount() +
                ", minWeight=" + getMinWeight() +
                ", maxWeight=" + getMaxWeight() +
                ", maximumFlightAltitude=" + getMaxFlightAltitude() +
                ", rotorDiameter=" + getRotorDiameter() +
                '}';
    }
}
