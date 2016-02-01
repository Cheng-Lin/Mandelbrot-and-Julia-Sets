package Application;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
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

  public void mousePressed(MouseEvent e){}

  public void mouseReleased(MouseEvent e){}

  public void mouseClicked(MouseEvent e)
  {
    if (clickZoom)
    {
      if (e.getButton() == e.BUTTON1)
      {
        juliaSet.zoomIN(juliaSet.getX(e.getX()), juliaSet.getY(e.getY()));
        repaint();
      }
      else if (e.getButton() == e.BUTTON3)
      {
        juliaSet.zoomOUT(juliaSet.getX(e.getX()), juliaSet.getY(e.getY()));
        repaint();
      }
    }
  }

  public void mouseEntered(MouseEvent e){}

  public void mouseExited(MouseEvent e){}

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

  public void saveImage(int width, int height)
  {
    JFileChooser fc = new JFileChooser();
    int returnVal = fc.showSaveDialog(null);
    if (returnVal == JFileChooser.APPROVE_OPTION)
    {
      try {
        juliaSet.setImgSize(width, height);
        BufferedImage imgJSet = juliaSet.generate();
        ImageIO.write(imgJSet, "jpg", fc.getSelectedFile());
      } catch(IOException e) {}
    }
  }
}