package util;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

import static util.Parameters.PADDLE_HEIGHT;
import static util.Parameters.PADDLE_WIDTH;
import static util.Parameters.PADDLE_X;
import static util.Parameters.PADDLE_Y;

public final class Paddle extends ColorShape {

  private static int speed;
  private static int xPos;

  public Paddle() {
    super(new Rectangle2D.Double(PADDLE_X, PADDLE_Y, PADDLE_WIDTH, PADDLE_HEIGHT), Color.WHITE);

    xPos = PADDLE_X;
    speed = 0;
  }

  public void move() {
    xPos += speed;

    if (xPos < 2) {
      xPos = 2;
    }
    if (xPos > 592 - PADDLE_WIDTH) {
      xPos = 592 - PADDLE_WIDTH;
    }

    setLocation(xPos, PADDLE_Y);
  }

  public void setSpeed(final int newSpeed) {
    speed = newSpeed;
  }

  public int getWidth() {
    return PADDLE_WIDTH;
  }

  public int getHeight() {
    return PADDLE_HEIGHT;
  }

  public int getX() {
    return xPos;
  }

  public int getY() {
    return PADDLE_Y;
  }
}
