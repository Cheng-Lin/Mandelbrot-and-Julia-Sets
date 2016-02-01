package Application;

import java.awt.event.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import javax.swing.*;

public class FreeAnimation
  implements ActionListener
{
  private JuliaPanel jPanel;
  private Timer freeAnimate;
  private ArrayList<Point2D.Double> points;
  private int count = -1;

  public FreeAnimation(JuliaPanel jPanel)
  {
    this.jPanel = jPanel;
    freeAnimate = new Timer(175, this);
  }

  public void setSpeed(int value)
  {
    freeAnimate.setDelay(value);
  }
  
  public void startAnimate(ArrayList<Point2D.Double> points)
  {
    this.points = points;
    freeAnimate.start();
  }

  public void actionPerformed(ActionEvent e)
  {
    if (count < points.size() - 2)
      count++;
    else
      count = 0;

    try {
      jPanel.generate(points.get(count).getX(), points.get(count).getY());
    } catch (IndexOutOfBoundsException ex) {}
  }

  public void stopAnimate()
  {
    count = -1;
    points = null;
    freeAnimate.stop();
  }
}