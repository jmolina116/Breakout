package util;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

/**
 * Class for the user-controlled Paddle object
 *
 * @author JoseMolina
 */
public class Paddle extends ColorShape {

  private static int speed;
  private static int xPos;
  private static final int yPos = Parameters.PADDLE_Y;
  private static final int width = Parameters.PADDLE_WIDTH;
  private static final int height = Parameters.PADDLE_HEIGHT;

  private static final Rectangle2D.Double shape = new Rectangle2D.Double(Parameters.PADDLE_X, yPos, width, height);

  public Paddle() {
    super(shape, Color.WHITE);

    // set paddle position and speed
    xPos = Parameters.PADDLE_X;
    speed = 0;
  }

  public void move() {
    // move paddle
    xPos += speed;

    // stop the paddle from moving at the edges
    if (xPos < 2) {
      xPos = 2;
    } // number 592 reached after a few tries of experimentation, 600 or 598 still went over
    if (xPos > 592 - width) {
      xPos = 592 - width;
    }

    // update shape
    shape.setRect(xPos, yPos, width, height);
  }

  public void setSpeed(final int newSpeed) {
    speed = newSpeed;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public int getX() {
    return xPos;
  }

  public int getY() {
    return yPos;
  }
}
