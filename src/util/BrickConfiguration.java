package util;

import java.awt.Graphics2D;

/**
 * BrickConfiguration class, an arrangement of several Bricks
 * -- the abstract superclass for all brick tiers
 *
 * @author JoseMolina
 */
public abstract class BrickConfiguration {

  final int numCols = Parameters.BRICK_COLS;
  final int numRows;
  Brick[][] bricks;
  boolean[][] paintBricks;
  int[][] numHits;

  BrickConfiguration(final int y, final int r) {

    final int x = Parameters.BRICK_GAP;
    numRows = r;
    bricks = new Brick[numCols][numRows];
    paintBricks = new boolean[numCols][numRows];
    numHits = new int[numCols][numRows];

    // create new bricks and store them in bricks array
    for (int i = 0; i < numCols; i++) {
      for (int j = 0; j < numRows; j++) {
        // initialize paintBricks[i][j]
        paintBricks[i][j] = true;
        numHits[i][j] = 0;

        // initialize bricks[i][j]
        bricks[i][j] = new Brick(
            x + (i * (Parameters.BRICK_WIDTH + x)),
            y + (j * (Parameters.BRICK_HEIGHT + x)),
            Parameters.BRICK_WIDTH,
            Parameters.BRICK_HEIGHT
        );
      }
    }
  }

  public void paint(final Graphics2D brush) {
    for (int i = 0; i < numCols; i++) {
      for (int j = 0; j < numRows; j++) {
        // determine if brick should be painted
        // if so, call paintBrick()
        if (paintBricks[i][j]) {
          paintBrick(bricks[i][j], brush);
        }
      }
    }
  }

  public void paintBrick(final Brick brick, final Graphics2D brush) {
    brick.paint(brush);
  }

  /**
   * getter method for paintBricks for a Brick at a specified row and column
   *
   * @param row row of Brick
   * @param col column of Brick
   * @return true if Brick is to be painted, false if not
   */
  public boolean getPaintBricks(final int row, final int col) {
    return paintBricks[col][row];
  }

  public void setNumHits(final int row, final int col, final int hit) {
    numHits[col][row] = hit;
  }

  public int getNumHits(final int row, final int col) {
    return numHits[col][row];
  }

  public Brick getBrick(final int row, final int col) {
    return bricks[col][row];
  }

  /**
   * checks if a Brick at a specified location is there or has been destroyed
   *
   * @param row row of Brick
   * @param col column of Brick
   * @return true if Brick is still there, false if it has been destoryed
   */
  public boolean exists(final int row, final int col) {
    return paintBricks[col][row];
  }

  public int getRows() {
    return numRows;
  }

  public int getCols() {
    return numCols;
  }

  /**
   * to be implemented by specific tiers of Bricks to take care of changing color for higher tiers
   * when hit and of deleting bricks after numHits is at the necessary amount for that tier
   */
  abstract public void checkBrick(final int row, final int col);
}
