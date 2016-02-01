package Application;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class MandelbrotPanel extends JPanel
{
  private MandelbrotSet mandelbrotSet;
  private JuliaPanel juliaPanel;
  private FreeAnimation freeAnimate;
  private LineAnimation lineAnimate;
  private BufferedImage mandelbrotImage;

  private boolean clickZoom = false, clickGen = true, animation = false;
  private int animateType = FractalsMenu.FREE_ANIMATE;

  public MandelbrotPanel(JuliaPanel juliaPanel)
  {
    this.juliaPanel = juliaPanel;

    freeAnimate = new FreeAnimation(juliaPanel);
    lineAnimate = new LineAnimation(this);

    setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    setBackground(Color.white);

    MyOwnMouseListener mouse = new MyOwnMouseListener();
    addMouseListener(mouse);
    addMouseMotionListener(mouse);
  }

  public void reset()
  {
    mandelbrotSet.reset();

    repaint();
  }

  public void setClickZoom(boolean clickZoom)
  { this.clickZoom = clickZoom; }

  public void setClickGen(boolean clickGen)
  { this.clickGen = clickGen; }

  public void setAnimation(boolean animation)
  { 
    this.animation = animation;
    if (!animation)
    {
      freeAnimate.stopAnimate();
      lineAnimate.stopAnimate();
      juliaPanel.setClickZoom(true);
    }
    else
    {
      if (animateType == FractalsMenu.LINE_ANIMATE)
      {
        juliaPanel.setClickZoom(false);
        juliaPanel.reset();
        lineAnimate.startAnimate();
      }
    }
  }

  public void setAnimateSpeed(int value)
  {
    freeAnimate.setSpeed(value);
    lineAnimate.setSpeed(value);
  }

  public void setAnimateType(int type)
  {
    animateType = type;
    if (animation)
    {
      if (animateType == FractalsMenu.FREE_ANIMATE)
      {
        lineAnimate.stopAnimate();
        juliaPanel.setClickZoom(true);
      }
      else if (animateType == FractalsMenu.LINE_ANIMATE)
      {
        freeAnimate.stopAnimate();
        juliaPanel.setClickZoom(false);
        juliaPanel.reset();
        lineAnimate.startAnimate();
      }
    }
  }

  public void juliaGen(double x, double y)
  {
    juliaPanel.generate(mandelbrotSet.getCReal(x), 
			mandelbrotSet.getCImage(y));
  }

  public void paint(Graphics g)
  {
    super.paint(g);

    if (mandelbrotSet == null)
    {
      mandelbrotSet = new MandelbrotSet(getWidth() - 4, getHeight() - 4);
      mandelbrotImage = mandelbrotSet.generate();
    }
    else
    {
      if (mandelbrotSet.getWidth() != getWidth() - 4 || 
	  mandelbrotSet.getHeight() != getHeight() - 4)
        mandelbrotSet.setImgSize(getWidth() - 4, getHeight() - 4);
    }
    if (clickZoom)
      mandelbrotImage = mandelbrotSet.generate();

    try {
      g.drawImage(mandelbrotImage, 2, 2, null);
    } catch (Exception e) {}

    if (animation && animateType == FractalsMenu.LINE_ANIMATE)
    {
      Graphics2D g2 = (Graphics2D)g;

      double x = lineAnimate.getXBegin(), y = lineAnimate.getYBegin();
      Line2D.Double line1 = new Line2D.Double(x - 2, y, x + 2, y);
      Line2D.Double line2 = new Line2D.Double(x, y - 2, x, y+2);

      g2.setColor(Color.white);
      g2.draw(line1);
      g2.draw(line2);
    }
  }

  public void saveImage(int width, int height)
  {
    JFileChooser fc = new JFileChooser();
    int returnVal = fc.showSaveDialog(null);
    if (returnVal == JFileChooser.APPROVE_OPTION)
    {
      try {
        mandelbrotSet.setImgSize(width, height);
        BufferedImage imgMSet = mandelbrotSet.generate();
        ImageIO.write(imgMSet, "jpg", fc.getSelectedFile());
      } catch(IOException e) {}
    }
  }

  private class MyOwnMouseListener
    implements MouseListener, MouseMotionListener
  {
    private ArrayList<Point2D.Double> juliaList;

    public void mousePressed(MouseEvent e)
    {
      if (animation && animateType == FractalsMenu.FREE_ANIMATE)
        juliaList = new ArrayList<Point2D.Double>();
    }

    public void mouseReleased(MouseEvent e)
    {
      if (animation && animateType == FractalsMenu.FREE_ANIMATE)
      {
        juliaPanel.reset();
        for (Point2D.Double p : juliaList)
        {
          double x = mandelbrotSet.getCReal(p.getX()),
                 y = mandelbrotSet.getCImage(p.getY());
          p.setLocation(x, y);
        }
        freeAnimate.startAnimate(juliaList);
      }
    }

    public void mouseClicked(MouseEvent e)
    {
      double x = mandelbrotSet.getCReal(e.getX()),
             y = mandelbrotSet.getCImage(e.getY());

      if (clickZoom)
      {
        if (e.getButton() == e.BUTTON1)
        { 
          mandelbrotSet.zoomIN(x, y);
          repaint();
        }
        else if (e.getButton() == e.BUTTON3)
        {
          mandelbrotSet.zoomOUT(x, y);
          repaint();
        }
      }

      if (clickGen)
      {
        juliaPanel.reset();
        juliaPanel.generate(x, y);
      }

      if (lineAnimate.isRunning())
      {
        lineAnimate.setIncrease(e.getX(), e.getY());
      }
    }

    public void mouseEntered(MouseEvent e){}

    public void mouseExited(MouseEvent e){}

    public void mouseDragged(MouseEvent e)
    {
      if (animation && animateType == FractalsMenu.FREE_ANIMATE)
        juliaList.add(new Point2D.Double(e.getX(), e.getY()));
    }

    public void mouseMoved(MouseEvent e){}
  }
}