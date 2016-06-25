package util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

public abstract class ColorShape {
  private final RectangularShape shape;
  private Color color;

  protected ColorShape(final RectangularShape shape, final Color color) {
    this.shape = shape;
    this.color = color;
  }

  private Rectangle2D getFrame() {
    return shape.getFrame();
  }

  public boolean contains(final Point point) {
    return shape.contains(point);
  }

  public boolean intersects(final ColorShape other) {
    return shape.intersects(other.getFrame());
  }

  public void setLocation(final int x, final int y) {
    shape.setFrame(x, y, shape.getWidth(), shape.getHeight());
  }

  public void recolor(final Color color) {
    this.color = color;
  }

  public void paint(final Graphics2D brush) {
    brush.setColor(color);
    brush.draw(shape);
    brush.fill(shape);
  }
}
