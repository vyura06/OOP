package transport.land;

import transport.Transport;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class Train extends Transport {
    private int wagonsCount;

    public Train() {
    }
    public Train(int width, int height, int depth,
                 int maxSpeed,
                 int teamCount, int passagesCount,
                 int wagonsCount) {
        super(width, height, depth, maxSpeed, teamCount, passagesCount);
        setWagonsCount(wagonsCount);
    }

    public int getWagonsCount() {
        return wagonsCount;
    }
    public void setWagonsCount(int wagonsCount) {
        checkNegative(wagonsCount);
        this.wagonsCount = wagonsCount;
    }

    @Override
    public void writeProps(XMLStreamWriter w) throws XMLStreamException {
        super.writeProps(w);
        writeElement(w, "WagonsCount", getWagonsCount());
    }

    @Override
    protected boolean readProps(String key, String val) {
        if (super.readProps(key, val))
            return true;
        if ("WagonsCount".equals(key)) {
            setWagonsCount((int) Double.parseDouble(val));
            return true;
        }
        return false;
    }

    public String toString() {
        return "Train{" +
                "width=" + getWidth() +
                ", height=" + getHeight() +
                ", depth=" + getDepth() +
                ", maxSpeed=" + getMaxSpeed() +
                ", teamCount=" + getTeamCount() +
                ", passagesCount=" + getPassagesCount() +
                ", wagonsCount=" + getWagonsCount() +
                '}';
    }
}