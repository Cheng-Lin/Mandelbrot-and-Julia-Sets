package Application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

public class FractalsMenu extends JMenu implements ActionListener
{
    private final FractalsMenuBar menuBar;
    private final JMenuItem newGenM, newGenJ;
    private final JRadioButtonMenuItem freeAnimation, lineAnimation;

    public static final int FREE_ANIMATE = 1, LINE_ANIMATE = 2;

    public FractalsMenu(final FractalsMenuBar menuBar)
    {
        super("Fractals");
        this.setMnemonic('r');

        this.menuBar = menuBar;

        // Second level menu under "Reset":
        final JMenu reset = new JMenu("Reset");
        reset.setMnemonic('R');

        this.newGenM = new JMenuItem("Mandelbrot Set");
        this.newGenM.setMnemonic('M');
        this.newGenM.addActionListener(this);

        this.newGenJ = new JMenuItem("Julia Set");
        this.newGenJ.setMnemonic('J');
        this.newGenJ.addActionListener(this);

        reset.add(this.newGenM);
        reset.add(this.newGenJ);

        // Second level menu under "Animation" :
        final JMenu animation = new JMenu("Animation");
        animation.setMnemonic('A');

        this.freeAnimation = new JRadioButtonMenuItem("Free Animation", true);
        this.freeAnimation.setMnemonic('F');
        this.freeAnimation.addActionListener(this);

        this.lineAnimation = new JRadioButtonMenuItem("Line Animation", false);
        this.lineAnimation.setMnemonic('L');
        this.lineAnimation.addActionListener(this);

        final ButtonGroup gr = new ButtonGroup();
        gr.add(this.freeAnimation);
        gr.add(this.lineAnimation);
        animation.add(this.freeAnimation);
        animation.add(this.lineAnimation);

        this.add(animation);
        this.add(reset);
    }

    @Override
    public void actionPerformed(final ActionEvent e)
    {
        final Object src = e.getSource();

        if (src == this.freeAnimation)
            this.menuBar.setAnimateType(FractalsMenu.FREE_ANIMATE);
        else if (src == this.lineAnimation)
            this.menuBar.setAnimateType(FractalsMenu.LINE_ANIMATE);
        else if (src == this.newGenM)
            this.menuBar.newGenM();
        else if (src == this.newGenJ) this.menuBar.newGenJ();
    }
}
