package Applet;

import java.awt.event.*;
import javax.swing.*;

public class LineAnimation
  implements ActionListener
{
  private MandelbrotPanel mPanel;
  private Timer lineAnimate;
  private double xBegin, yBegin, xEnd, yEnd;
  private double xIncrease, yIncrease;
  private int count = 0, steps = 0;;

  public LineAnimation(MandelbrotPanel mPanel)
  {
    this.mPanel = mPanel;
    lineAnimate = new Timer(175, this);
  }

  public void setSpeed(int value)
  {
    lineAnimate.setDelay(value);
  }
  
  public void startAnimate()
  {
    xBegin = mPanel.getWidth() / 2;
    yBegin = mPanel.getHeight() / 2;
    xEnd = xBegin;
    yEnd = yBegin;
    xIncrease = 0.0;
    yIncrease = 0.0;

    lineAnimate.start();
  }

  public void setIncrease(int xEnd, int yEnd)
  {
    this.xEnd = xEnd;
    this.yEnd = yEnd;
    
    int xSlope = xEnd - (int)xBegin,
        ySlope = yEnd - (int)yBegin;

    count = 0;
    steps = Math.max(Math.abs(xSlope), Math.abs(ySlope));

    xIncrease = (double)xSlope / steps;
    yIncrease = (double)ySlope / steps;
  }

  @Override
public void actionPerformed(ActionEvent e)
  {
    if (count < steps)
    {
      xBegin += xIncrease;
      yBegin += yIncrease;
      count++;
    }

    mPanel.juliaGen(xBegin, yBegin);
    mPanel.repaint();
  }

  public void stopAnimate()
  { lineAnimate.stop(); }

  public double getXBegin()
  { return xBegin; }

  public double getYBegin()
  { return yBegin; }

  public boolean isRunning()
  { return lineAnimate.isRunning(); }
}