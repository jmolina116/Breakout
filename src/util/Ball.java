package util;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

import static util.Parameters.BALL_SIZE;
import static util.Parameters.BALL_SPEED;
import static util.Parameters.BALL_X_AND_Y;

public final class Ball extends ColorShape {

  private int xPos = BALL_X_AND_Y;
  private int yPos = BALL_X_AND_Y;
  private int xSpeed = BALL_SPEED;
  private int ySpeed = BALL_SPEED;

  public Ball() {
    super(new Ellipse2D.Double(0, 0, BALL_SIZE, BALL_SIZE), Color.RED);
  }

  public void move() {
    // move ball
    xPos += xSpeed;
    yPos += ySpeed;

    // detect if ball should bounce off an edge
    if (getX() < 0 || getX() > 580) {
      xSpeed = -1 * xSpeed;
    }
    if (getY() < Parameters.SCORE_HEIGHT) {
      ySpeed = -1 * ySpeed;
    }

    // update location from movement
    setLocation(xPos, yPos);
  }

  public void setXSpeed(final int newSpeed) {
    xSpeed = newSpeed;
  }

  public void setYSpeed(final int newSpeed) {
    ySpeed = newSpeed;
  }

  public int getXSpeed() {
    return xSpeed;
  }

  public int getYSpeed() {
    return ySpeed;
  }

  public int getX() {
    return xPos;
  }

  public int getY() {
    return yPos;
  }

  public int getHeight() {
    return BALL_SIZE;
  }

  public int getWidth() {
    return BALL_SIZE;
  }

}
