package Applet;

import javax.swing.JMenuBar;

public class FractalsMenuBar extends JMenuBar
{
    private final Fractals myFractals;
    private final FractalsMenu fractals;
    private final HelpMenu help;

    public FractalsMenuBar(final Fractals frac)
    {
        this.myFractals = frac;

        // "Fractals" menu:

        this.fractals = new FractalsMenu(this);
        this.add(this.fractals);

        // "Help" menu:

        this.help = new HelpMenu();
        this.add(this.help);
    }

    public void newGenM()
    {
        this.myFractals.newGenM();
    }

    public void newGenJ()
    {
        this.myFractals.newGenJ();
    }

    public void setAnimateType(final int type)
    {
        this.myFractals.setAnimateType(type);
    }
}
