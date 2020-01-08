package club.banyuan;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Parabola extends JFrame {

  /**
   * y = ax平方 + bx + c 的三个参数
   */
  static double a = 0.1, b = 0, c = 0;
  /**
   * 坐标轴的宽、高
   */
  static final int AXIS_AREA = 400;

  public Parabola() {

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
      try {
        a = Double.parseDouble(textA.getText());
        b = Double.parseDouble(textB.getText());
        c = Double.parseDouble(textC.getText());
      } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(null, "输入不合法", "错误", JOptionPane.WARNING_MESSAGE);
        return;
      }

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
        // 坐标居中
        g.translate(10, 10);
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
    frame.setLocationRelativeTo(null);//在屏幕中居中显示
    frame.setResizable(false);
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
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

    g.setColor(Color.red);
    for (double x = -(AXIS_AREA / 2.0); x <= (AXIS_AREA / 2.0); x = x + 0.1) {
      double y = a * x * x + b * x + c;
      int X = (int) Math.round(AXIS_AREA / 2.0 + x);
      int Y = (int) Math.round(AXIS_AREA / 2.0 - y);
      if (Y >= 0 && Y <= AXIS_AREA) {
        g.fillOval(X, Y - 2, 1, 4);
      }
    }
  }

  public static void main(String[] args) {
    EventQueue.invokeLater(() -> {
      try {
        new Parabola();
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
  }
}