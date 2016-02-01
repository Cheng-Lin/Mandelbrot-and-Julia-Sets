package Application;
import java.awt.*;
import javax.swing.*;

public class FractalsMenuBar extends JMenuBar
{
  private Fractals myFractals;
  private FileMenu file;
  private FractalsMenu fractals;
  private HelpMenu help;

  public FractalsMenuBar(Fractals frac)
  {
    myFractals = frac;

    // "File" menu:

    file = new FileMenu(this);    
    add(file);

    // "Fractals" menu:

    fractals = new FractalsMenu(this);
    add(fractals);

    // "Help" menu:

    help = new HelpMenu();
    add(help);
  }

  public void saveM(int width, int height)
  { myFractals.saveM(width, height); }

  public void saveJ(int width, int height)
  { myFractals.saveJ(width, height); }

  public void newGenM()
  { myFractals.newGenM(); }

  public void newGenJ()
  { myFractals.newGenJ(); }

  public void setAnimateType(int type)
  { myFractals.setAnimateType(type); }

  public void closeApplication()
  { myFractals.dispose(); }
}