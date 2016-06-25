package util;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

/**
 * Class for Ball object
 *
 * @author JoseMolina
 */
public class Ball extends ColorShape {

  private int xPos = Parameters.BALL_X_AND_Y;
  private int yPos = Parameters.BALL_X_AND_Y;
  private int xSpeed = Parameters.BALL_SPEED;
  private int ySpeed = Parameters.BALL_SPEED;

  public Ball() {
    super(new Ellipse2D.Double(0, 0, Parameters.BALL_SIZE, Parameters.BALL_SIZE), Color.RED);
  }

  /**
   * moves the Ball, bounces from frame edges
   */
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
    return Parameters.BALL_SIZE;
  }

  public int getWidth() {
    return Parameters.BALL_SIZE;
  }

}
