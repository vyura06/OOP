package sample.logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Figure implements Drawable {
    private Color fill;

    public void setFillColor(Color color) {
        this.fill = color;
    }
    public Color getFillColor() {
        return fill;
    }

    public abstract void drawStroke(GraphicsContext g);

    public abstract void setX(int x);
    public abstract void setY(int y);
    public abstract void setWidth(int width);
    public abstract void setHeight(int height);

    public abstract int getX();
    public abstract int getY();
    public abstract int getWidth();
    public abstract int getHeight();

    public int getMinX() {
        return getX();
    }
    public int getMinY() {
        return getY();
    }
    public int getMaxX() {
        return getX() + getWidth();
    }
    public int getMaxY() {
        return getY() + getHeight();
    }

    public boolean contains(int x, int y) {
        return x > getMinX() && x < getMaxX() &&
                y > getMinY() && y < getMaxY();
    }
}