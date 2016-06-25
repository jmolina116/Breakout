package util;

import java.awt.Color;

/**
 * Class for Tier1 of Bricks object
 *
 * @author JoseMolina
 */
public class BrickTier1 extends BrickConfiguration {

  public BrickTier1() {
    super(Parameters.BRICK_T1_Y_START, Parameters.BRICK_T1_ROWS);
    for (int i = 0; i < numCols; i++) {
      for (int j = 0; j < numRows; j++) {
        bricks[i][j].recolor(Color.ORANGE);
      }
    }
  }

  @Override
  public void checkBrick(final int row, final int col) {
    if (numHits[col][row] == 1) {
      paintBricks[col][row] = false;
    }
  }
}
