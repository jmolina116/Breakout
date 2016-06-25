package util;

public final class Parameters {

  private Parameters() {
    throw new UnsupportedOperationException();
  }

  // dimensions of the scorebar
  public static final int SCORE_HEIGHT = 25;
  public static final int SCORE_WIDTH = 600;

  // ball parameters and initial values
  static final int BALL_X_AND_Y = 300;
  public static final int BALL_SPEED = 1;
  static final int BALL_SIZE = 15;

  // paddle parameters and initial values
  static final int PADDLE_X = 275;
  static final int PADDLE_Y = 455;
  static final int PADDLE_HEIGHT = 5;
  static final int PADDLE_WIDTH = 50;
  public static final int PADDLE_SPEED = 5;

  // brick and brick configuration parameters
  static final int BRICK_HEIGHT = 15;
  static final int BRICK_WIDTH = 42;
  static final int BRICK_COLS = 11;
  public static final int BRICK_GAP = 11;
  public static final int BRICK_TOTAL = 99; // (BRICK_T1_ROWS * BRICK_COLS) + (BRICK_T2_ROWS * BRICK_COLS) + (BRICK_T3_ROWS * BRICK_COLS)

  // brick tier specific parameters
  static final int BRICK_T3_ROWS = 1;
  static final int BRICK_T2_ROWS = 3;
  static final int BRICK_T1_ROWS = 5;
  static final int BRICK_T3_Y_START = 37; // SCORE_HEIGHT + BRICK_GAP
  static final int BRICK_T2_Y_START = 63; // BRICK_T3_Y_START + (BRICK_T3_ROWS * (BRICK_HEIGHT + BRICK_GAP))
  static final int BRICK_T1_Y_START = 141; // BRICK_T2_Y_START + (BRICK_T2_ROWS * (BRICK_HEIGHT + BRICK_GAP))

  public static final String BACKGROUND = "/resources/brickBackground.jpg";
}
