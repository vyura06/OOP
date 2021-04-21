package sample.logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Line extends Figure {
    private int x1, y1, x2, y2;
    private int lw = 1;

    public void setX(int x) {
        this.x2 += x - this.x1;
        this.x1 = x;
    }
    public void setY(int y) {
        this.y2 += y - this.y1;
        this.y1 = y;
    }
    public void setWidth(int width) {
        this.x2 = this.x1 + width;
        this.y2 = this.y1 + width;
    }
    public void setHeight(int lw) {
        if (lw < 0)
            throw new IllegalArgumentException();
        this.lw = lw;
    }

    public int getX() {
        return x1;
    }
    public int getY() {
        return y1;
    }

    public int getMinX() {
        return x1;
    }
    public int getMinY() {
        return y1;
    }

    public int getMaxX() {
        return x2;
    }
    public int getMaxY() {
        return y2;
    }

    public int getWidth() {
        int dx = x2 - x1;
        int dy = y2 - y1;
        return (int) Math.sqrt(dx * dx + dy * dy);
    }
    public int getHeight() {
        return lw;
    }

    public boolean contains(int x, int y) {
        return (y - y1) / (y2 - y1) ==
                (x - x1) / (x2 - x1);
    }

    public void draw(GraphicsContext g) {
        Paint sC = g.getStroke();
        double sLW = g.getLineWidth();

        g.setStroke(getFillColor());
        g.setLineWidth(lw);
        g.strokeLine(x1, y1, x2, y2);

        g.setLineWidth(sLW);
        g.setStroke(sC);
    }
    public void drawStroke(GraphicsContext g) {
        double sLW = g.getLineWidth();

        g.setLineWidth(lw);
        g.strokeLine(x1, y1, x2, y2);

        g.setLineWidth(sLW);
    }
}