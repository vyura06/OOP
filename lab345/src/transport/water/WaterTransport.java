package transport.water;

import transport.Transport;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.function.IntConsumer;

public class WaterTransport extends Transport {
    private int minDisplacement, maxDisplacement;

    public WaterTransport() {
    }
    public WaterTransport(int width, int height, int depth,
                          int maxSpeed,
                          int teamCount, int passagesCount,
                          int minDisplacement, int maxDisplacement) {
        super(width, height, depth, maxSpeed, teamCount, passagesCount);
        setMinDisplacement(minDisplacement);
        setMaxDisplacement(maxDisplacement);
    }

    public int getMinDisplacement() {
        return minDisplacement;
    }
    public void setMinDisplacement(int minDisplacement) {
        checkNegative(minDisplacement);
        this.minDisplacement = minDisplacement;
    }

    public int getMaxDisplacement() {
        return maxDisplacement;
    }
    public void setMaxDisplacement(int maxDisplacement) {
        checkNegative(maxDisplacement);
        this.maxDisplacement = maxDisplacement;
    }

    @Override
    public void writeProps(XMLStreamWriter w) throws XMLStreamException {
        super.writeProps(w);
        writeElement(w, "MinDisplacement", getMinDisplacement());
        writeElement(w, "MaxDisplacement", getMaxDisplacement());
    }

    @Override
    protected boolean readProps(String key, String val) {
        if (super.readProps(key, val))
            return true;
        IntConsumer c;
        switch (key) {
            case "MinDisplacement":
                c = this::setMinDisplacement;
                break;
            case "MaxDisplacement":
                c = this::setMaxDisplacement;
                break;
            default:
                return false;
        }
        set(c, val);
        return true;
    }
}
