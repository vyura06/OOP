package sample.logic;

import javafx.scene.canvas.GraphicsContext;

public class Circle extends Figure {
    private int x, y, radius;

    public Circle() {
    }
    public Circle(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setWidth(int width) {
        this.radius = width;
    }
    public void setHeight(int height) {
        this.radius = height;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getWidth() {
        return radius;
    }
    public int getHeight() {
        return radius;
    }

    public void draw(GraphicsContext g) {
        g.setFill(getFillColor());
        g.fillOval(x, y, radius, radius);
    }
    public void drawStroke(GraphicsContext g) {
        g.strokeOval(x, y, radius, radius);
    }
}