package util;

import java.awt.Color;

import static util.Parameters.BRICK_T3_ROWS;
import static util.Parameters.BRICK_T3_Y_START;

public final class BrickTier3 extends BrickConfiguration {

  public BrickTier3() {
    super(BRICK_T3_Y_START, BRICK_T3_ROWS, Color.WHITE);
  }

  @Override
  public void checkBrick(final int row, final int col) {
    if (numHits[col][row] == 1) {
      getBrick(row, col).recolor(Color.GRAY);
    }
    else if (numHits[col][row] == 2) {
      getBrick(row, col).recolor(Color.DARK_GRAY);
    }
    else if (numHits[col][row] == 3) {
      paintBricks[col][row] = false;
    }
  }
}
