package club.banyuan;

/**
 * 命令行画法
 */
public class Main {


  public static final int A = 5;
  public static final int B = 0;
  public static final int C = 0;

  public static final char BLANK = '*';
  public static final char POINT = ' ';

  /**
   * 需要将数组下标转换为实际的x和y变量，带入公式
   */
  private static class AxisPoint {

    int x;
    int y;

    static final AxisPoint INSTANCE = new AxisPoint();

    @Override
    public String toString() {
      return "AxisPoint{" +
          "x=" + x +
          ", y=" + y +
          '}';
    }

    public static AxisPoint transformAxisPos(int axisX, int axisY) {
      // 第二象限，都是正数
      INSTANCE.x = Math.abs(axisX - ORIGIN);
      INSTANCE.y = Math.abs(axisY - ORIGIN);
      // 第一象限
      if (axisX <= ORIGIN && axisY >= ORIGIN) {
        INSTANCE.x = -INSTANCE.x;
        // 第三象限
      } else if (axisX >= ORIGIN && axisY <= ORIGIN) {
        INSTANCE.y = -INSTANCE.y;
        // 第四象限
      } else if (axisX <= ORIGIN && axisY <= ORIGIN) {
        INSTANCE.x = -INSTANCE.x;
        INSTANCE.y = -INSTANCE.y;
      }
      return INSTANCE;
    }
  }

  // 横纵坐标长度101的话，每个象限宽度是50
  public static final int AXIS_LEN = 101;
  // 原点
  public static final int ORIGIN = AXIS_LEN / 2;

  public static char[][] axis = new char[AXIS_LEN][AXIS_LEN];

  public static void main(String[] args) {

    for (int i = 0; i < AXIS_LEN; i++) {
      for (int j = 0; j < AXIS_LEN; j++) {
        AxisPoint axisPoint = AxisPoint.transformAxisPos(i, j);
        System.out.println(axisPoint);
        if (axisPoint.y == parabolaCal(axisPoint.x)) {
          axis[i][j] = POINT;
        } else {
          axis[i][j] = BLANK;
        }
      }
    }

    for (int i = 0; i < AXIS_LEN; i++) {
      for (int j = 0; j < AXIS_LEN; j++) {
        if (j == ORIGIN) {
          if (BLANK == axis[j][i]) {
            System.out.print("|");
            continue;
          }
        }
        if (i == ORIGIN) {
          if (BLANK == axis[j][i]) {
            System.out.print("-");
          } else {
            System.out.print(axis[j][i]);
          }
        } else {
          System.out.print(axis[j][i]);
        }
      }
      System.out.println();
    }

  }

  public static int parabolaCal(int x) {
    int rlt = (int) (A * Math.pow(x, 2) + B * x + C);
    System.out.println(rlt);
    return rlt;
  }
}
