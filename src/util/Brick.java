package util;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

/**
 * Class for Brick object
 *
 * @author JoseMolina
 */
public class Brick extends ColorShape {

  /**
   * constructor for Brick
   *
   * @param x x-axis coordinate
   * @param y y-axis coordinate
   * @param width
   * @param height
   */
  public Brick(final int x, final int y, final int width, final int height) {
    super(new Rectangle2D.Double(x, y, width, height), Color.GRAY);
  }

}
