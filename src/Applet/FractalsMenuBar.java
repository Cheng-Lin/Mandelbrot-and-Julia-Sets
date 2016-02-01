package Applet;

import java.awt.*;
import javax.swing.*;

public class FractalsMenuBar extends JMenuBar
{
  private Fractals myFractals;
  private FractalsMenu fractals;
  private HelpMenu help;

  public FractalsMenuBar(Fractals frac)
  {
    myFractals = frac;

    // "Fractals" menu:

    fractals = new FractalsMenu(this);
    add(fractals);

    // "Help" menu:

    help = new HelpMenu();
    add(help);
  }

  public void newGenM()
  { myFractals.newGenM(); }

  public void newGenJ()
  { myFractals.newGenJ(); }

  public void setAnimateType(int type)
  { myFractals.setAnimateType(type); }
}