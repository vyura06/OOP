package sample.logic;

import javafx.scene.canvas.GraphicsContext;

public class Ellipse extends Circle {
    private int width, height;

    public Ellipse() {
        super();
    }
    public Ellipse(int x, int y, int width, int height) {
        super(x, y, Math.min(width, height));
        this.width = width;
        this.height = height;
    }

    public void setWidth(int width) {
        super.setWidth(Math.min(width, height));
        this.width = width;
    }
    public void setHeight(int height) {
        super.setHeight(Math.min(width, height));
        this.height = height;
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    public void draw(GraphicsContext g) {
        g.setFill(getFillColor());
        g.fillOval(getX(), getY(), width, height);
    }
    public void drawStroke(GraphicsContext g) {
        g.strokeOval(getX(), getY(), width, height);
    }
}