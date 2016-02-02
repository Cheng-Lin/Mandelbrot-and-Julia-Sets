package Application;

import javax.swing.JMenuBar;

public class FractalsMenuBar extends JMenuBar
{
    private final Fractals myFractals;
    private final FileMenu file;
    private final FractalsMenu fractals;
    private final HelpMenu help;

    public FractalsMenuBar(final Fractals frac)
    {
        this.myFractals = frac;

        // "File" menu:

        this.file = new FileMenu(this);
        this.add(this.file);

        // "Fractals" menu:

        this.fractals = new FractalsMenu(this);
        this.add(this.fractals);

        // "Help" menu:

        this.help = new HelpMenu();
        this.add(this.help);
    }

    public void saveM(final int width, final int height)
    {
        this.myFractals.saveM(width, height);
    }

    public void saveJ(final int width, final int height)
    {
        this.myFractals.saveJ(width, height);
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

    public void closeApplication()
    {
        this.myFractals.dispose();
    }
}
