package transport.water;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.function.IntConsumer;

public class Submarine extends WaterTransport {
    private int defaultImmersionDepth, maxImmersionDepth;
    private int surfaceSpeed;

    public Submarine() {
    }
    public Submarine(int width, int height, int depth,
                     int underwaterSpeed,
                     int teamCount, int passagesCount,
                     int minDisplacement, int maxDisplacement,
                     int defaultImmersionDepth, int maxImmersionDepth,
                     int surfaceSpeed) {
        super(width, height, depth, underwaterSpeed, teamCount, passagesCount, minDisplacement, maxDisplacement);
        setDefaultImmersionDepth(defaultImmersionDepth);
        setMaxImmersionDepth(maxImmersionDepth);
        setSurfaceSpeed(surfaceSpeed);
    }

    public int getDefaultImmersionDepth() {
        return defaultImmersionDepth;
    }
    public void setDefaultImmersionDepth(int defaultImmersionDepth) {
        checkNegative(defaultImmersionDepth);
        this.defaultImmersionDepth = defaultImmersionDepth;
    }

    public int getMaxImmersionDepth() {
        return maxImmersionDepth;
    }
    public void setMaxImmersionDepth(int maxImmersionDepth) {
        checkNegative(maxImmersionDepth);
        this.maxImmersionDepth = maxImmersionDepth;
    }

    public int getSurfaceSpeed() {
        return surfaceSpeed;
    }
    public void setSurfaceSpeed(int surfaceSpeed) {
        this.surfaceSpeed = surfaceSpeed;
    }

    @Override
    public void writeProps(XMLStreamWriter w) throws XMLStreamException {
        super.writeProps(w);
        writeElement(w, "DefaultImmersionDepth", getDefaultImmersionDepth());
        writeElement(w, "MaxImmersionDepth", getMaxImmersionDepth());
        writeElement(w, "SurfaceSpeed", getSurfaceSpeed());
    }

    @Override
    protected boolean readProps(String key, String val) {
        if (super.readProps(key, val))
            return true;
        IntConsumer c;
        switch (key) {
            case "DefaultImmersionDepth":
                c = this::setDefaultImmersionDepth;
                break;
            case "MaxImmersionDepth":
                c = this::setMaxImmersionDepth;
                break;
            case "SurfaceSpeed":
                c = this::setSurfaceSpeed;
                break;
            default:
                return false;
        }
        set(c, val);
        return true;
    }

    public String toString() {
        return "Submarine{" +
                "width=" + getWidth() +
                ", height=" + getHeight() +
                ", depth=" + getDepth() +
                ", maxSpeed=" + getMaxSpeed() +
                ", teamCount=" + getTeamCount() +
                ", passagesCount=" + getPassagesCount() +
                ", minDisplacement=" + getMinDisplacement() +
                ", maxDisplacement=" + getMaxDisplacement() +
                ", defaultImmersionDepth=" + getDefaultImmersionDepth() +
                ", maxImmersionDepth=" + getMaxImmersionDepth() +
                ", surfaceSpeed=" + getSurfaceSpeed() +
                '}';
    }
}