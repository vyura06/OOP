package transport;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import transport.air.Helicopter;
import transport.air.Plane;
import transport.land.Auto;
import transport.land.MotorCycle;
import transport.land.Train;
import transport.water.Ship;
import transport.water.Submarine;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.Serializable;
import java.util.List;
import java.util.function.IntConsumer;

public class Transport implements Serializable {
    private int width, height, depth;
    private int maxSpeed;
    private int teamCount, passagesCount;

    public Transport() {
    }
    public Transport(int width, int height, int depth,
                     int maxSpeed,
                     int teamCount, int passagesCount) {
        setWidth(width);
        setHeight(height);
        setDepth(depth);
        setMaxSpeed(maxSpeed);
        setTeamCount(teamCount);
        setPassagesCount(passagesCount);
    }

    protected static void checkNegative(int value) {
        if (value < 0)
            throw new IllegalArgumentException("Negative value");
    }
    protected static void checkNegative(double value) {
        if (value < 0.0)
            throw new IllegalArgumentException("Negative value");
    }

    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        checkNegative(width);
        this.width = width;
    }

    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        checkNegative(height);
        this.height = height;
    }

    public int getDepth() {
        return depth;
    }
    public void setDepth(int depth) {
        checkNegative(depth);
        this.depth = depth;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }
    public void setMaxSpeed(int maxSpeed) {
        checkNegative(maxSpeed);
        this.maxSpeed = maxSpeed;
    }

    public int getTeamCount() {
        return teamCount;
    }
    public void setTeamCount(int teamCount) {
        checkNegative(teamCount);
        this.teamCount = teamCount;
    }

    public int getPassagesCount() {
        return passagesCount;
    }
    public void setPassagesCount(int passagesCount) {
        checkNegative(passagesCount);
        this.passagesCount = passagesCount;
    }


    protected static void writeElement(XMLStreamWriter w, String key, Object o) throws XMLStreamException {
        w.writeStartElement(key);
        w.writeCharacters(o.toString());
        w.writeEndElement();
    }
    protected void writeProps(XMLStreamWriter w) throws XMLStreamException {
        writeElement(w, "Width", getWidth());
        writeElement(w, "Height", getHeight());
        writeElement(w, "Depth", getDepth());
        writeElement(w, "MaxSpeed", getMaxSpeed());
        writeElement(w, "TeamCount", getTeamCount());
        writeElement(w, "PassagesCount", getPassagesCount());
    }
    public final void write(XMLStreamWriter w) throws XMLStreamException {
        w.writeStartElement(getClass().getName());
        writeProps(w);
        w.writeEndElement();
    }

    protected static void set(IntConsumer setter, String val) {
        setter.accept((int) Double.parseDouble(val));
    }
    protected boolean readProps(String key, String val) {
        IntConsumer c;
        switch (key) {
            case "Width":
                c = this::setWidth;
                break;
            case "Height":
                c = this::setHeight;
                break;
            case "Depth":
                c = this::setDepth;
                break;
            case "MaxSpeed":
                c = this::setMaxSpeed;
                break;
            case "TeamCount":
                c = this::setTeamCount;
                break;
            case "PassagesCount":
                c = this::setPassagesCount;
                break;
            default:
                return false;
        }
        set(c, val);
        return true;
    }
    final void read0(Node node) {
        NodeList props = node.getChildNodes();
        for (int i = 0; i < props.getLength(); i++) {
            Node prop = props.item(i);
            if (prop.getNodeType() != Node.TEXT_NODE) {
                readProps(prop.getNodeName(), prop.getChildNodes().item(0).getTextContent());
            }
        }
    }

    public static final List<Class<? extends Transport>> list = List.of(
            Plane.class,
            Helicopter.class,
            Auto.class,
            MotorCycle.class,
            Train.class,
            Ship.class,
            Submarine.class
    );
    public static Transport read(Node node) throws Exception {
        String name = node.getNodeName();
        for (Class<? extends Transport> cls : list) {
            if (cls.getName().equals(name)) {
                Transport t = cls.getConstructor().newInstance();
                t.read0(node);
                return t;
            }
        }
        return null;
    }
}