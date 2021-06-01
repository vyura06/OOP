package transport.water;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class Ship extends WaterTransport {
    private int sailingRange;

    public Ship() {
    }
    public Ship(int width, int height, int depth,
                int maxSpeed,
                int teamCount, int passagesCount,
                int minDisplacement, int maxDisplacement,
                int sailingRange) {
        super(width, height, depth, maxSpeed, teamCount, passagesCount, minDisplacement, maxDisplacement);
        setSailingRange(sailingRange);
    }

    public int getSailingRange() {
        return sailingRange;
    }
    public void setSailingRange(int sailingRange) {
        checkNegative(sailingRange);
        this.sailingRange = sailingRange;
    }

    @Override
    public void writeProps(XMLStreamWriter w) throws XMLStreamException {
        super.writeProps(w);
        writeElement(w, "SailingRange", getSailingRange());
    }

    @Override
    protected boolean readProps(String key, String val) {
        if (super.readProps(key, val))
            return true;
        if ("SailingRange".equals(key)) {
            setSailingRange((int) Double.parseDouble(val));
            return true;
        }
        return false;
    }

    public String toString() {
        return "Ship{" +
                "width=" + getWidth() +
                ", height=" + getHeight() +
                ", depth=" + getDepth() +
                ", maxSpeed=" + getMaxSpeed() +
                ", teamCount=" + getTeamCount() +
                ", passagesCount=" + getPassagesCount() +
                ", minDisplacement=" + getMinDisplacement() +
                ", maxDisplacement=" + getMaxDisplacement() +
                "sailingRange=" + getSailingRange() +
                '}';
    }
}