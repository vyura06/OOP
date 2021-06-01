package sample.logic;

import javafx.scene.canvas.GraphicsContext;

public class Square extends Figure {
    private int x, y, a;

    public Square() {
    }
    public Square(int x, int y, int a) {
        this.x = x;
        this.y = y;
        this.a = a;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.a = width;
    }
    public void setHeight(int height) {
        this.a = height;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public int getWidth() {
        return a;
    }
    public int getHeight() {
        return a;
    }

    public void draw(GraphicsContext g) {
        g.setFill(getFillColor());
        g.fillRect(x, y, a, a);
    }
    public void drawStroke(GraphicsContext g) {
        g.strokeRect(x, y, a, a);
    }
}

