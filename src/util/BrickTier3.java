package util;

import java.awt.Color;

/**
 * Class for Tier3 of Bricks object
 *
 * @author JoseMolina
 */
public class BrickTier3 extends BrickConfiguration {

  public BrickTier3() {
    super(Parameters.BRICK_T3_Y_START, Parameters.BRICK_T3_ROWS);
    for (int i = 0; i < numCols; i++) {
      for (int j = 0; j < numRows; j++) {
        bricks[i][j].recolor(Color.WHITE);
      }
    }
  }

  @Override
  public void checkBrick(final int row, final int col) {
    if (numHits[col][row] == 1) {
      getBrick(row, col).recolor(Color.GRAY);
    } else if (numHits[col][row] == 2) {
      getBrick(row, col).recolor(Color.DARK_GRAY);
    } else if (numHits[col][row] == 3) {
      paintBricks[col][row] = false;
    }
  }
}
