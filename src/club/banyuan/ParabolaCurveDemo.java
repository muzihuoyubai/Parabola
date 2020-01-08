package club.banyuan;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 使用曲线画法，演示
 */
public class ParabolaCurveDemo extends JFrame {

  /**
   * y = ax平方 + bx + c 的三个参数
   */
  static double a = 1, b = 0, c = 0;
  /**
   * 坐标轴的宽、高
   */
  static final int AXIS_AREA = 400;

  public ParabolaCurveDemo() {

    JFrame frame = new JFrame("抛物线");

    JLabel labelA = new JLabel();
    labelA.setText("a");
    JTextField textA = new JTextField(String.valueOf(a), 3);
    JLabel labelB = new JLabel();
    labelB.setText("b");
    JTextField textB = new JTextField(String.valueOf(b), 3);
    JLabel labelC = new JLabel();
    labelC.setText("c");
    JTextField textC = new JTextField(String.valueOf(c), 3);

    JButton draw = new JButton();
    draw.setText("Draw");
    draw.addActionListener(e -> {
      a = Double.parseDouble(textA.getText());
      b = Double.parseDouble(textB.getText());
      c = Double.parseDouble(textC.getText());

      repaint();
      frame.repaint();
    });

    JPanel inputPanel = new JPanel();
    inputPanel.setPreferredSize(new Dimension(50, 50));
    inputPanel.add(labelA);
    inputPanel.add(textA);
    inputPanel.add(labelB);
    inputPanel.add(textB);
    inputPanel.add(labelC);
    inputPanel.add(textC);
    inputPanel.add(draw);

    JPanel parabolaPanel = new JPanel() {
      public void paint(Graphics g) {
        super.paint(g);
        grid(g);
        drawParabola(g);
      }
    };

    parabolaPanel.setBackground(Color.WHITE);

    frame.getContentPane().setLayout(new BorderLayout());
    frame.getContentPane().add(inputPanel, BorderLayout.PAGE_START);
    frame.getContentPane().add(parabolaPanel, BorderLayout.CENTER);
    frame.setVisible(true);
    frame.setSize(AXIS_AREA + 20, AXIS_AREA + 100);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  public static void main(String[] args) {
    EventQueue.invokeLater(() -> {
      try {
        new ParabolaCurveDemo();
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
  }

  /**
   * 画坐标轴
   *
   * @param g
   */
  public static void grid(Graphics g) {
    int gridStep = AXIS_AREA / 10;
    int gridLen = 5;
    g.setColor(Color.blue);
    g.drawLine(AXIS_AREA / 2, 0, AXIS_AREA / 2, AXIS_AREA);
    g.drawLine(0, AXIS_AREA / 2, AXIS_AREA, AXIS_AREA / 2);

    // 画坐标轴上面的短线
    for (int x = 0; x <= AXIS_AREA; x = x + gridStep) {
      g.drawLine(x, AXIS_AREA / 2 - gridLen, x, AXIS_AREA / 2 + gridLen);
    }
    for (int y = 0; y <= AXIS_AREA; y = y + gridStep) {
      g.drawLine(AXIS_AREA / 2 - gridLen, y, AXIS_AREA / 2 + gridLen, y);
    }
  }

  /**
   * 画抛物线
   *
   * @param g
   */
  public static void drawParabola(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2d.setStroke(new BasicStroke(1));
    // g2d.translate(40, 40);

    // Graphics2D gg = (Graphics2D) g;
    //
    // /* Enable anti-aliasing and pure stroke */
    // gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    // gg.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
    //
    // /* Construct a shape and draw it */
    //
    // // gg.draw(shape);
    g2d.setColor(Color.red);
    // for (double x = -200; x <= 0; x = x + 0.1) {
    //   double y = a * x * x + b * x + c;
    //   int X = (int) Math.round(AXIS_AREA / 2.0 + x);
    //   int Y = (int) Math.round(AXIS_AREA / 2.0 - y);
    //   // int Y = 0;
    //   // 左上角的坐标是0,0，右下角坐标是 AXIS_AREA,AXIS_AREA
    //   Ellipse2D.Double shape = new Ellipse2D.Double(x, y, 4, 4);
    //   gg.draw(shape);
    // }
    GeneralPath path = new GeneralPath();
    double step = 0.01;
    boolean first = true;
    for (double x = -(AXIS_AREA / 4.0); x <= (AXIS_AREA / 4.0); x = x + step) {

      // 左上角的坐标是0,0，右下角坐标是 AXIS_AREA,AXIS_AREA
      // g.fillOval(X - 2, Y - 2, 4, 4);
      if (first) {
        double y = a * x * x + b * x + c;
        double X = AXIS_AREA / 2.0 + x;
        double Y = AXIS_AREA / 2.0 - y;
        path.moveTo(X, Y);
        first = false;

        x += step;
        double y1 = a * x * x + b * x + c;
        double X1 = AXIS_AREA / 2.0 + x;
        double Y1 = AXIS_AREA / 2.0 - y1;
        x += step;
        double y2 = a * x * x + b * x + c;
        double X2 = AXIS_AREA / 2.0 + x;
        double Y2 = AXIS_AREA / 2.0 - y2;
        x += step;
        double y3 = a * x * x + b * x + c;
        double X3 = AXIS_AREA / 2.0 + x;
        double Y3 = AXIS_AREA / 2.0 - y3;
        path.curveTo(X1, Y1, X2, Y2, X3, Y3);
      } else {
        double y1 = a * x * x + b * x + c;
        double X1 = AXIS_AREA / 2.0 + x;
        double Y1 = AXIS_AREA / 2.0 - y1;
        x += step;
        double y2 = a * x * x + b * x + c;
        double X2 = AXIS_AREA / 2.0 + x;
        double Y2 = AXIS_AREA / 2.0 - y2;
        x += step;
        double y3 = a * x * x + b * x + c;
        double X3 = AXIS_AREA / 2.0 + x;
        double Y3 = AXIS_AREA / 2.0 - y3;
        path.curveTo(X1, Y1, X2, Y2, X3, Y3);
      }

    }

    // path.moveTo(10,10);
    g2d.draw(path);
    // g2d.fillOval(0, 0, 4, 4);

    // for (double x = -(AXIS_AREA / 4.0); x <= (AXIS_AREA / 4.0); x = x + 0.1) {
    //   double y = a * x * x + b * x + c;
    //   int X = (int) Math.round(AXIS_AREA / 2.0 + x);
    //   int Y = (int) Math.round(AXIS_AREA / 2.0 - y);
    //   // 左上角的坐标是0,0，右下角坐标是 AXIS_AREA,AXIS_AREA
    //   g.fillOval(X - 2, Y - 2, 4, 4);
    // }

    // g.fillOval(AXIS_AREA, AXIS_AREA, 4, 4);
  }
}