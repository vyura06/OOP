package transport.air;

import transport.Transport;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.function.IntConsumer;

public class AirTransport extends Transport {
    private int minWeight, maxWeight;
    private int maxFlightAltitude;

    public AirTransport() {
    }
    public AirTransport(int width, int height, int depth,
                        int maxSpeed,
                        int teamCount, int passagesCount,
                        int minWeight, int maxWeight,
                        int maxFlightAltitude) {
        super(width, height, depth, maxSpeed, teamCount, passagesCount);
        setMinWeight(minWeight);
        setMaxWeight(maxWeight);
        setMaxFlightAltitude(maxFlightAltitude);
    }

    public int getMinWeight() {
        return minWeight;
    }
    public void setMinWeight(int minWeight) {
        checkNegative(minWeight);
        this.minWeight = minWeight;
    }

    public int getMaxWeight() {
        return maxWeight;
    }
    public void setMaxWeight(int maxWeight) {
        checkNegative(maxWeight);
        this.maxWeight = maxWeight;
    }

    public int getMaxFlightAltitude() {
        return maxFlightAltitude;
    }
    public void setMaxFlightAltitude(int maxFlightAltitude) {
        checkNegative(maxFlightAltitude);
        this.maxFlightAltitude = maxFlightAltitude;
    }


    @Override
    public void writeProps(XMLStreamWriter w) throws XMLStreamException {
        super.writeProps(w);
        writeElement(w, "MinWeight", getMinWeight());
        writeElement(w, "MaxWeight", getMaxWeight());
        writeElement(w, "MaxFlightAltitude", getMaxFlightAltitude());
    }

    @Override
    protected boolean readProps(String key, String val) {
        if (super.readProps(key, val))
            return true;
        IntConsumer c;
        switch (key) {
            case "MinWeight":
                c = this::setMinWeight;
                break;
            case "MaxWeight":
                c = this::setMaxWeight;
                break;
            case "MaxFlightAltitude":
                c = this::setMaxFlightAltitude;
                break;
            default:
                return false;
        }
        set(c, val);
        return true;
    }
}