package Application;

import java.awt.event.*;
import javax.swing.*;

public class FileMenu extends JMenu
  implements ActionListener
{
  FractalsMenuBar menuBar;
  private JMenuItem mSmall, mMedium, mLarge, mCustom;
  private JMenuItem jSmall, jMedium, jLarge, jCustom;
  private JMenuItem exit;

  public FileMenu(FractalsMenuBar menuBar)
  {
    super("File");
    setMnemonic('F');

    this.menuBar = menuBar;
    
    // Second level menu under "Save Image"
    JMenu saveImage = new JMenu("Save Image");
    saveImage.setMnemonic('I');
    MSetListener mSet = new MSetListener();
    
    // Third level menu under "Mandlebrot Set"
    JMenu mandelbrotSet = new JMenu("Mandelbrot Set");
    mandelbrotSet.setMnemonic('M');
    
    mSmall = new JMenuItem("600 x 600");
    mSmall.addActionListener(mSet);
    mandelbrotSet.add(mSmall);

    mMedium = new JMenuItem("1800 x 1800");
    mMedium.addActionListener(mSet);
    mandelbrotSet.add(mMedium);

    mLarge = new JMenuItem("3000 x 3000");
    mLarge.addActionListener(mSet);
    mandelbrotSet.add(mLarge);

    mCustom = new JMenuItem("Customize");
    mCustom.setMnemonic('C');
    mCustom.addActionListener(mSet);
    mandelbrotSet.addSeparator();
    mandelbrotSet.add(mCustom);

    //Third level menu under "Julia Set"
    JMenu juliaSet = new JMenu("Julia Set");
    juliaSet.setMnemonic('J');
    JSetListener jSet = new JSetListener();

    jSmall = new JMenuItem("600 x 600");
    jSmall.addActionListener(jSet);
    juliaSet.add(jSmall);

    jMedium = new JMenuItem("1800 x 1800");
    jMedium.addActionListener(jSet);
    juliaSet.add(jMedium);

    jLarge = new JMenuItem("3000 x 3000");
    jLarge.addActionListener(jSet);
    juliaSet.add(jLarge);

    jCustom = new JMenuItem("Customize");
    jCustom.setMnemonic('C');
    jCustom.addActionListener(jSet);
    juliaSet.addSeparator();
    juliaSet.add(jCustom);

    saveImage.add(mandelbrotSet);
    saveImage.add(juliaSet);

    exit = new JMenuItem("Exit");
    exit.setMnemonic('E');
    exit.addActionListener(this);

    add(saveImage);
    addSeparator();
    add(exit);
  }

  public void actionPerformed(ActionEvent e)
  {
    menuBar.closeApplication();
  }

  private class MSetListener
    implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      JMenuItem src = (JMenuItem)e.getSource();

      if (src == mSmall)
        menuBar.saveM(600, 600);
      else if (src == mMedium)
        menuBar.saveM(1800, 1800);
      else if (src == mLarge)
        menuBar.saveM(3000, 3000);
      else if (src == mCustom)
        saveM();
    }

    public void saveM()
    {
      // User Input Width
      String strWidth = JOptionPane.showInputDialog(
		"Please input the width of the image.");
      int intWidth = 0;

      // Catch Invalid Input
      try {
        intWidth = Integer.parseInt(strWidth);
      } 
      catch (NumberFormatException ex) 
      {
        JOptionPane.showMessageDialog(menuBar, "Invalid Number!",
		"Input Error", JOptionPane.ERROR_MESSAGE);
        return;
      }
      if (intWidth <= 0)
      {
        JOptionPane.showMessageDialog(menuBar, "Invalid Number!",
		"Input Error", JOptionPane.ERROR_MESSAGE);
        return;
      }

      // User Input Height
      String strHeight = JOptionPane.showInputDialog(
		"Please input the height of the image.");
      int intHeight = 0;

      // Catch Invalid Input
      try {
        intHeight = Integer.parseInt(strHeight);
      } 
      catch (NumberFormatException ex) 
      {
        JOptionPane.showMessageDialog(menuBar, "Invalid Number!",
		"Input Error", JOptionPane.ERROR_MESSAGE);
        return;
      }
      if (intHeight <= 0)
      {
        JOptionPane.showMessageDialog(menuBar, "Invalid Number!",
		"Input Error", JOptionPane.ERROR_MESSAGE);
        return;
      }

      // Save Image
      menuBar.saveM(intWidth, intHeight);      
    }
  }

  private class JSetListener
    implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      JMenuItem src = (JMenuItem)e.getSource();

      if (src == jSmall)
        menuBar.saveJ(600, 600);
      else if (src == jMedium)
        menuBar.saveJ(1800, 1800);
      else if (src == jLarge)
        menuBar.saveJ(3000, 3000);
      else if (src == jCustom)
        saveJ();
    }

    public void saveJ()
    {
      // User Input Width
      String strWidth = JOptionPane.showInputDialog(
		"Please input the width of the image.");
      int intWidth = 0;

      // Catch Invalid Input
      try {
        intWidth = Integer.parseInt(strWidth);
      } 
      catch (NumberFormatException ex) 
      {
        JOptionPane.showMessageDialog(menuBar, "Invalid Number!",
		"Input Error", JOptionPane.ERROR_MESSAGE);
        return;
      }
      if (intWidth <= 0)
      {
        JOptionPane.showMessageDialog(menuBar, "Invalid Number!",
		"Input Error", JOptionPane.ERROR_MESSAGE);
        return;
      }

      // User Input Height
      String strHeight = JOptionPane.showInputDialog(
		"Please input the height of the image.");
      int intHeight = 0;

      // Catch Invalid Input
      try {
        intHeight = Integer.parseInt(strHeight);
      } 
      catch (NumberFormatException ex) 
      {
        JOptionPane.showMessageDialog(menuBar, "Invalid Number!",
		"Input Error", JOptionPane.ERROR_MESSAGE);
        return;
      }
      if (intHeight <= 0)
      {
        JOptionPane.showMessageDialog(menuBar, "Invalid Number!",
		"Input Error", JOptionPane.ERROR_MESSAGE);
        return;
      }

      // Save Image
      menuBar.saveJ(intWidth, intHeight);   
    }
  }
}


