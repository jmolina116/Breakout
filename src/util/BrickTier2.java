package util;

import java.awt.Color;

/**
 * Class for Tier2 of Bricks object
 *
 * @author JoseMolina
 */
public class BrickTier2 extends BrickConfiguration {

  public BrickTier2() {
    super(Parameters.BRICK_T2_Y_START, Parameters.BRICK_T2_ROWS);
    for (int i = 0; i < numCols; i++) {
      for (int j = 0; j < numRows; j++) {
        bricks[i][j].recolor(Color.GREEN);
      }
    }
  }

  @Override
  public void checkBrick(final int row, final int col) {
    if (numHits[col][row] == 1) {
      getBrick(row, col).recolor(new Color(0, 153, 0));
    } else if (numHits[col][row] == 2) {
      paintBricks[col][row] = false;
    }
  }
}
