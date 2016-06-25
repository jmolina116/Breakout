package util;

import java.awt.Color;
import static util.Parameters.BRICK_T2_ROWS;
import static util.Parameters.BRICK_T2_Y_START;

public final class BrickTier2 extends BrickConfiguration {

  public BrickTier2() {
    super(BRICK_T2_Y_START, BRICK_T2_ROWS, Color.GREEN);
  }

  @Override
  public void checkBrick(final int row, final int col) {
    if (numHits[col][row] == 1) {
      getBrick(row, col).recolor(new Color(0, 153, 0));
    }
    else if (numHits[col][row] == 2) {
      paintBricks[col][row] = false;
    }
  }
}
