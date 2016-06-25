package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;

import util.*;


/**
 * Class project using Java Swing made from scratch.
 * Originally made flat with no packages on Sublime text
 * but remade into an IntelliJ project.
 *
 * @author JoseMolina
 */
public class Breakout {

  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setSize(600, 500);
    frame.setTitle("Just Another Brick In The Wall");
    frame.setResizable(false);
    frame.add(new GamePanel());
    frame.setVisible(true);
  }

  private static class GamePanel extends JPanel {

    private final ImageIcon background = new ImageIcon(getClass().getResource(Parameters.BACKGROUND));
    private Ball ball;
    private Paddle paddle;
    private BrickConfiguration bt1;
    private BrickConfiguration bt2;
    private BrickConfiguration bt3;
    private Timer timer;
    private int score;
    private boolean dead;
    private boolean info;

    GamePanel() {
      info = true;
      initializeGameObjects();
      addKeyListener(new KeyMover());
      setFocusable(true);
    }

    private void initializeGameObjects() {
      // instantiate ball, paddle, and brick configuration
      ball = new Ball();
      paddle = new Paddle();
      bt1 = new BrickTier1();
      bt2 = new BrickTier2();
      bt3 = new BrickTier3();

      // default values for dead and score at start of game
      dead = false;
      score = 0;

      // set up timer to run GameMotion() every 10ms
      timer = new Timer(10, new GameMotion());
      timer.start();
    }

    @Override
    public void paintComponent(final Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      g2.drawImage(background.getImage(), 0, 0, null);

      // paints the initial info screen
      if (info) {
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Serif", Font.PLAIN, 50));
        g2.drawString("Welcome to Breakout!", 70, 150);
        g2.setFont(new Font("Serif", Font.PLAIN, 20));
        g2.drawString("Try to break all the bricks by moving the paddle LEFT and RIGHT.", 40, 230);
        g2.drawString("Press A to accelerate, or increase the speed of the ball.", 80, 270);
        g2.drawString("Press D to decrease the speed of the ball.", 80, 290);
        g2.drawString("Press S to return the ball speed to its default.", 80, 310);
        g2.drawString("Press SPACE to play and to restart the game at any time.", 65, 350);
      }
      else {
        // paint ball, paddle, and brick configurations
        paddle.paint(g2);
        ball.paint(g2);
        bt1.paint(g2);
        bt2.paint(g2);
        bt3.paint(g2);

        if (dead) {
          // redraw background to cover the bricks, otherwise they get
          // in the way of the GAME OVER letters and make it hard to read
          g2.drawImage(background.getImage(), 0, 0, null);
          g2.setColor(Color.WHITE);
          g2.setFont(new Font("Serif", Font.PLAIN, 50));
          g2.drawString("Game Over!", 170, 200);
          g2.setFont(new Font("Serif", Font.PLAIN, 30));
          g2.drawString("You died!", 225, 250);
          g2.setFont(new Font("Serif", Font.PLAIN, 15));
          g2.drawString("Press SPACE to play again.", 200, 350);
        }
        else if (score == Parameters.BRICK_TOTAL) {
          g2.setColor(Color.WHITE);
          g2.setFont(new Font("Serif", Font.PLAIN, 50));
          g2.drawString("Congratulations!", 125, 200);
          g2.setFont(new Font("Serif", Font.PLAIN, 30));
          g2.drawString("You destroyed all the bricks!", 125, 250);
          g2.setFont(new Font("Serif", Font.PLAIN, 15));
          g2.drawString("Press SPACE to play again.", 200, 350);
        }

        // painted afterwards so that it will still show up on the game over screen
        // (otherwise the background would cover it)
        Brick scorebar = new Brick(0, 0, Parameters.SCORE_WIDTH, Parameters.SCORE_HEIGHT);
        scorebar.recolor(Color.DARK_GRAY);
        scorebar.paint(g2);
        g2.setColor(Color.YELLOW);
        g2.setFont(new Font("Serif", Font.PLAIN, 20));
        g2.drawString("Bricks Destroyed: " + score, Parameters.BRICK_GAP, 20);
        g2.drawString("Bricks Remaining: " + (Parameters.BRICK_TOTAL - score), 300, 20);
      }
    }

    private class GameMotion implements ActionListener {

      GameMotion() {
      }

      /**
       * does all the self-movement of the game
       *
       * @param evt ActionEvent
       */
      public void actionPerformed(final ActionEvent evt) {
        if (!dead && score != Parameters.BRICK_TOTAL) {
          ball.move();
          paddle.move();
          checkForHit();
          repaint();
        }
      }
    }

    private class KeyMover implements KeyListener {

      /**
       * checks what key is pressed and performs corresponding action
       *
       * @param evt KeyEvent
       */
      @Override
      public void keyPressed(final KeyEvent evt) {
        final int key = evt.getKeyCode();
        // change paddle speeds for left and right key presses
        if (key == KeyEvent.VK_LEFT) {
          paddle.setSpeed(-1 * Parameters.PADDLE_SPEED);
        }
        if (key == KeyEvent.VK_RIGHT) {
          paddle.setSpeed(Parameters.PADDLE_SPEED);
        }

        // restart function by SPACE bar
        if (key == KeyEvent.VK_SPACE) {
          timer.stop();
          initializeGameObjects();
          info = false;
        }

        // change speed of ball
        if (key == KeyEvent.VK_A) {
          ball.setXSpeed(3 * ball.getXSpeed());
          ball.setYSpeed(3 * ball.getYSpeed());
        }
        if (key == KeyEvent.VK_D && Math.abs(ball.getYSpeed()) >= 3) {
          ball.setXSpeed(ball.getXSpeed() / 3);
          ball.setYSpeed(ball.getYSpeed() / 3);
        }
        if (key == KeyEvent.VK_S) {
          ball.setXSpeed(ball.getXSpeed() / Math.abs(ball.getXSpeed()));
          ball.setYSpeed(ball.getYSpeed() / Math.abs(ball.getYSpeed()));
        }
      }

      /**
       * checks once a key is released to stop paddle
       *
       * @param evt KeyEvent
       */
      @Override
      public void keyReleased(final KeyEvent evt) {
        paddle.setSpeed(0);
      }

      @Override
      public void keyTyped(final KeyEvent evt) {
      }
    }

    /**
     * checks for in-game collisions to redirect movement
     * and ends game when ball falls below screen
     */
    private void checkForHit() {

      // change ball speed when ball hits paddle
      if (ball.intersects(paddle)) {
        final int leftSide = paddle.getX();
        final int middleLeft = paddle.getX() + (2 * paddle.getWidth() / 5);
        final int middleRight = paddle.getX() + (3 * paddle.getWidth() / 5);
        final int rightSide = paddle.getX() + paddle.getWidth();

        if ((ball.getX() >= leftSide) && (ball.getX() < middleLeft)) {
          // change ball speed
          if (ball.getXSpeed() == 0) {
            //keep the speed the same magnitude as the y speed before multiplying for the direction
            ball.setXSpeed(ball.getYSpeed());
          }
          // set the direction without changing the speed of 1, 3, 9
          // or whatever the player has increased speed to (pressing A)
          ball.setXSpeed(-1 * Math.abs(ball.getXSpeed()));
          ball.setYSpeed(-1 * Math.abs(ball.getYSpeed()));
        }
        if ((ball.getX() >= middleLeft) && (ball.getX() <= middleRight)) {
          // change ball speed
          ball.setXSpeed(0);
          ball.setYSpeed(-1 * Math.abs(ball.getYSpeed()));
        }
        if ((ball.getX() > middleRight) && (ball.getX() <= rightSide)) {
          // change ball speed
          if (ball.getXSpeed() == 0) {
            ball.setXSpeed(ball.getYSpeed());
          }
          ball.setXSpeed(Math.abs(ball.getXSpeed()));
          ball.setYSpeed(-1 * Math.abs(ball.getYSpeed()));
        }
      }

      // ends game if ball falls below the screen
      if (ball.getY() > 500) {
        dead = true;
      }

      checkForBrickHit(bt1);
      checkForBrickHit(bt2);
      checkForBrickHit(bt3);
    }

    /**
     * checks if Bricks are hit, called by checkForHit
     *
     * @param bc BrickConfiguration (a specified tier)
     */
    private void checkForBrickHit(final BrickConfiguration bc) {

      // change ball speed when ball hits brick
      for (int i = 0; i < bc.getRows(); i++) {
        for (int j = 0; j < bc.getCols(); j++) {
          if (bc.exists(i, j)) {
            if ( ball.intersects(bc.getBrick(i, j)) ) {

              Point ballLeft = new Point(ball.getX(), ball.getY() + (ball.getHeight() / 2));
              Point ballRight = new Point(ball.getX() + ball.getWidth(), ball.getY() + (ball.getHeight() / 2));
              Point ballTop = new Point(ball.getX() + (ball.getWidth() / 2), ball.getY());
              Point ballBottom = new Point(ball.getX() + (ball.getWidth() / 2), ball.getY() + ball.getHeight());

              if (bc.getBrick(i, j).contains(ballLeft)) {
                ball.setXSpeed(Math.abs(ball.getXSpeed()));
              } else if (bc.getBrick(i, j).contains(ballRight)) {
                ball.setXSpeed(-1 * Math.abs(ball.getXSpeed()));
              }
              if (bc.getBrick(i, j).contains(ballTop)) {
                ball.setYSpeed(Math.abs(ball.getYSpeed()));
              } else if (bc.getBrick(i, j).contains(ballBottom)) {
                ball.setYSpeed(-1 * Math.abs(ball.getYSpeed()));
              }

              // increase numHits for this brick + 1 and
              // remove if enough hits for this tier or change color if not
              bc.setNumHits(i, j, bc.getNumHits(i, j) + 1);
              bc.checkBrick(i, j);

              // increase score if the brick is destroyed
              if (!bc.getPaintBricks(i, j)) {
                score++;
              }
            }
          }
        }
      }
    }
  }
}
