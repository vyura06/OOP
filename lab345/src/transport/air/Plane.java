package transport.air;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class Plane extends AirTransport {
    private int wingArea;

    public Plane() {
    }
    public Plane(int width, int height, int depth,
                 int minWeight, int maxWeight,
                 int maxSpeed,
                 int teamCount, int passagesCount,
                 int maximumFlightAltitude, int wingArea) {
        super(width, height, depth, maxSpeed, teamCount, passagesCount, minWeight, maxWeight, maximumFlightAltitude);
        setWingArea(wingArea);
    }

    public int getWingArea() {
        return wingArea;
    }
    public void setWingArea(int wingArea) {
        checkNegative(wingArea);
        this.wingArea = wingArea;
    }

    @Override
    public void writeProps(XMLStreamWriter w) throws XMLStreamException {
        super.writeProps(w);
        writeElement(w, "WingArea", getWingArea());
    }

    @Override
    protected boolean readProps(String key, String val) {
        if (super.readProps(key, val))
            return true;
        if ("WingArea".equals(key)) {
            set(this::setWingArea, val);
            return true;
        }
        return false;
    }

    public String toString() {
        return "Plane{" +
                "width=" + getWidth() +
                ", height=" + getHeight() +
                ", depth=" + getDepth() +
                ", maxSpeed=" + getMaxSpeed() +
                ", teamCount=" + getTeamCount() +
                ", passagesCount=" + getPassagesCount() +
                ", minWeight=" + getMinWeight() +
                ", maxWeight=" + getMaxWeight() +
                ", maximumFlightAltitude=" + getMaxFlightAltitude() +
                ", wingArea=" + getWingArea() +
                '}';
    }
}