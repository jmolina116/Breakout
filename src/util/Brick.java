package util;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

public final class Brick extends ColorShape {

  public Brick(final int x, final int y, final int width, final int height, final Color color) {
    super(new Rectangle2D.Double(x, y, width, height), color);
  }

}
