package Application;

import java.awt.event.*;
import javax.swing.*;

public class FractalsMenu extends JMenu
  implements ActionListener
{
  private FractalsMenuBar menuBar;
  private JMenuItem newGenM, newGenJ;
  private JRadioButtonMenuItem freeAnimation, lineAnimation;

  public static final int FREE_ANIMATE = 1, LINE_ANIMATE = 2;

  public FractalsMenu(FractalsMenuBar menuBar)
  {
    super("Fractals");
    setMnemonic('r');

    this.menuBar = menuBar;

    // Second level menu under "Reset":
    JMenu reset = new JMenu("Reset");
    reset.setMnemonic('R');    

    newGenM = new JMenuItem("Mandelbrot Set");
    newGenM.setMnemonic('M');
    newGenM.addActionListener(this);
    
    newGenJ = new JMenuItem("Julia Set");
    newGenJ.setMnemonic('J');
    newGenJ.addActionListener(this);

    reset.add(newGenM);
    reset.add(newGenJ);

    //Second level menu under "Animation" :
    JMenu animation = new JMenu("Animation");
    animation.setMnemonic('A');

    freeAnimation = new JRadioButtonMenuItem("Free Animation", true);
    freeAnimation.setMnemonic('F');
    freeAnimation.addActionListener(this);

    lineAnimation = new JRadioButtonMenuItem("Line Animation", false);
    lineAnimation.setMnemonic('L');
    lineAnimation.addActionListener(this);

    ButtonGroup gr = new ButtonGroup();
    gr.add(freeAnimation);
    gr.add(lineAnimation);
    animation.add(freeAnimation);
    animation.add(lineAnimation);

    add(animation);
    add(reset);
  }

  public void actionPerformed(ActionEvent e)
  {
    Object src = e.getSource();

    if (src == freeAnimation)
      menuBar.setAnimateType(FREE_ANIMATE);
    else if (src == lineAnimation)
      menuBar.setAnimateType(LINE_ANIMATE);
    else if (src == newGenM)
      menuBar.newGenM();
    else if (src == newGenJ)
      menuBar.newGenJ();
  }
}