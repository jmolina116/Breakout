package util;

import java.awt.Color;

import static util.Parameters.BRICK_T1_ROWS;
import static util.Parameters.BRICK_T1_Y_START;

public final class BrickTier1 extends BrickConfiguration {

  public BrickTier1() {
    super(BRICK_T1_Y_START, BRICK_T1_ROWS, Color.ORANGE);
  }

  @Override
  public void checkBrick(final int row, final int col) {
    if (numHits[col][row] == 1) {
      paintBricks[col][row] = false;
    }
  }
}
