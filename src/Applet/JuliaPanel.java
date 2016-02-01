package Applet;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class JuliaPanel extends JPanel
  implements MouseListener
{
  private JuliaSet juliaSet;
  private BufferedImage juliaImage;
  private boolean clickZoom = true;

  public JuliaPanel()
  {
    setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    setBackground(Color.white);

    addMouseListener(this);
  }

  public void setClickZoom(boolean clickZoom)
  { this.clickZoom = clickZoom; }

  public void reset()
  {
    juliaSet.reset();

    repaint();
  }

  public void generate(double kReal, double kImage)
  {
    juliaSet.setK(kReal, kImage);

    repaint();
  }

  @Override
public void mousePressed(MouseEvent e){}

  @Override
public void mouseReleased(MouseEvent e){}

  @Override
public void mouseClicked(MouseEvent e)
  {
    if (clickZoom)
    {
      if (e.getButton() == MouseEvent.BUTTON1)
      {
        juliaSet.zoomIN(juliaSet.getX(e.getX()), juliaSet.getY(e.getY()));
        repaint();
      }
      else if (e.getButton() == MouseEvent.BUTTON3)
      {
        juliaSet.zoomOUT(juliaSet.getX(e.getX()), juliaSet.getY(e.getY()));
        repaint();
      }
    }
  }

  @Override
public void mouseEntered(MouseEvent e){}

  @Override
public void mouseExited(MouseEvent e){}

  @Override
public void paint(Graphics g)
  {
    super.paint(g);

    if (juliaSet == null)
      juliaSet = new JuliaSet(getWidth() - 4, getHeight() - 4);
    else
    {
      if (juliaSet.getWidth() != getWidth() - 4 || 
	  juliaSet.getHeight() != getHeight() - 4)
        juliaSet.setImgSize(getWidth() - 4, getHeight() - 4);
    }
    juliaImage = juliaSet.generate();

    try {
      g.drawImage(juliaImage, 2, 2, null);
    } catch (Exception e) {}
  }
}