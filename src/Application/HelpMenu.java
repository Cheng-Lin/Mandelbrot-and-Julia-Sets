package Application;

import java.awt.event.*;
import javax.swing.*;

public class HelpMenu extends JMenu
  implements ActionListener
{
  private JMenuItem mZoom;
  private JMenuItem jZoom, jGenerate, jAnimation;
  private JMenuItem reset, saveImg;
  private JMenuItem about;

  public HelpMenu()
  {
    super("Help");
    setMnemonic('H');

    // Second level menu under "Mandelbrot Set"
    JMenu mandelbrotSet = new JMenu("Mandelbrot Set");
    mandelbrotSet.setMnemonic('M');
    mZoom = new JMenuItem("Zoom");
    mZoom.setMnemonic('Z');
    mZoom.addActionListener(this);
    mandelbrotSet.add(mZoom);

    // Second level menu under "Julia Set"
    JMenu juliaSet = new JMenu("Julia Set");
    juliaSet.setMnemonic('J');
    JSetHelp jSet = new JSetHelp();
    
    jZoom = new JMenuItem("Zoom");
    jZoom.setMnemonic('Z');
    jZoom.addActionListener(jSet);
    juliaSet.add(jZoom);

    jGenerate = new JMenuItem("Generate");
    jGenerate.setMnemonic('G');
    jGenerate.addActionListener(jSet);
    juliaSet.add(jGenerate);

    jAnimation = new JMenuItem("Animation");
    jAnimation.setMnemonic('A');
    jAnimation.addActionListener(jSet);
    juliaSet.add(jAnimation);

    // Second level menu under "Other"
    JMenu other = new JMenu("Other");
    other.setMnemonic('O');

    reset = new JMenuItem("Reset");
    reset.setMnemonic('R');
    reset.addActionListener(this);

    saveImg = new JMenuItem("Save Image");
    saveImg.setMnemonic('S');
    saveImg.addActionListener(this);

    other.add(reset);
    other.add(saveImg);

    // "About"
    about = new JMenuItem("About");
    about.setMnemonic('A');
    about.addActionListener(this);
    
    add(mandelbrotSet);
    add(juliaSet);
    add(other);
    addSeparator();
    add(about);
  }

  public void actionPerformed(ActionEvent e)
  {
    JMenuItem src = (JMenuItem)e.getSource();

    if (src == mZoom)
      zoomMSet();
    else if (src == reset)
      howToReset();
    else if (src == saveImg)
      howToSave();
    else if (src == about)
      showAbout();
  }

  private void zoomMSet()
  {
    JOptionPane.showMessageDialog(null,
	"With \"Zoom\" check box selected, \n\n" +
        "Left click on the set == Zoom In. \n" +
        "Right click on the set == Zoom Out. \n",
        "How to Zoom Mandelbrot Set",
	JOptionPane.PLAIN_MESSAGE);
  }

  private void howToReset()
  {
    JOptionPane.showMessageDialog(null,
	"Under \"Fractals\" menu, there is a \"Reset\" menu, \n" +
        "choose a set you want to restore to default scale, \n" +
	"and then the image will to restore to defualt.",
	"How to Reset", JOptionPane.PLAIN_MESSAGE);
  }

  private void howToSave()
  {
    JOptionPane.showMessageDialog(null,
	"Under \"File\" menu, there is a \"Save Image\" menu, \n" +
	"choose a set inside the \"Save Image\" menu, \n" +
	"and select a size you want to save the set.\n" +
	"You can define your own size by click on \"Custom\", \n" +
	"then a input box will pup up for you to enter the size, \n" +
	"enter the width and the height you want to save the set. \n\n" +
	"After the process, a save file dialog will pop up, \n" +
	"choose the direction and input the file name of it, \n" +
        "click save when you we're done, the Image will then be \n" +
	"save to the direction you picked. \n\n" +
	"Make sure put the extension \".jpg\" after the file name.",
	"How to Save Image", JOptionPane.PLAIN_MESSAGE);
  }

  private void showAbout()
  {
    JOptionPane.showMessageDialog(null,
	"This Application is Made by Kebang Huang \n\n" +
	"Copyright (C) 2009 by Computer Science IV",
	"About", JOptionPane.PLAIN_MESSAGE);
  }

  private class JSetHelp
    implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      JMenuItem src = (JMenuItem)e.getSource();

      if (src == jZoom)
        zoomJSet();
      else if (src == jGenerate)
        genJSet();
      else if (src == jAnimation)
        howToAnimate();
    }

    private void zoomJSet()
    {
      JOptionPane.showMessageDialog(null,
	"Left Click on the Set == Zoom In \n" +
	"Right Click on the Set == Zoom Out \n\n" +
	"If you're in animation and under \"Line Animate\",\n" +
	"  the Zoom will be disabled.",
	"How to Zoom Julia Set", JOptionPane.PLAIN_MESSAGE);
    }

    private void genJSet()
    {
      JOptionPane.showMessageDialog(null,
	"With \"Julia Set\" check box selected, \n" +
	"simply click on Mandelbrot Set, \n" +
	"the application will then automaticly \n" +
	"generate a corespond Julia Set for you.",
	"How to Generate Julia Set",
	JOptionPane.PLAIN_MESSAGE);
    }

    private void howToAnimate()
    {
      JOptionPane.showMessageDialog(null,
	"With \"Animation\" check box selected, \n" +
	"pick a way to animate the Julia Set, \n" +
	"under \"Fractals\" --> \"Animation\" \n\n"+
	"Free Animation: \n" +
	"Hold down you mouse button on Mandelbrot Set, \n" +
	"and drag (more point will selected if you drag slowly).\n" +
	"After release your mouse button, the animation will start. \n\n" +
	"Line Animation: \n" +
	"After click on the Mandelbrot Set, \n" +
	"the white cross on the Mandelbrot Set \n" +
	"will move to spot will you click, \n" +
	"And the animation will start.",
	"How to Animate Julia Set", JOptionPane.PLAIN_MESSAGE);
    }
  }
}