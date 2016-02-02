package Applet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class HelpMenu extends JMenu implements ActionListener
{
    private final JMenuItem mZoom;
    private final JMenuItem jZoom, jGenerate, jAnimation;
    private final JMenuItem reset;
    private final JMenuItem about;

    public HelpMenu()
    {
        super("Help");
        this.setMnemonic('H');

        // Second level menu under "Mandelbrot Set"
        final JMenu mandelbrotSet = new JMenu("Mandelbrot Set");
        mandelbrotSet.setMnemonic('M');
        this.mZoom = new JMenuItem("Zoom");
        this.mZoom.setMnemonic('Z');
        this.mZoom.addActionListener(this);
        mandelbrotSet.add(this.mZoom);

        // Second level menu under "Julia Set"
        final JMenu juliaSet = new JMenu("Julia Set");
        juliaSet.setMnemonic('J');
        final JSetHelp jSet = new JSetHelp();

        this.jZoom = new JMenuItem("Zoom");
        this.jZoom.setMnemonic('Z');
        this.jZoom.addActionListener(jSet);
        juliaSet.add(this.jZoom);

        this.jGenerate = new JMenuItem("Generate");
        this.jGenerate.setMnemonic('G');
        this.jGenerate.addActionListener(jSet);
        juliaSet.add(this.jGenerate);

        this.jAnimation = new JMenuItem("Animation");
        this.jAnimation.setMnemonic('A');
        this.jAnimation.addActionListener(jSet);
        juliaSet.add(this.jAnimation);

        // Second level menu under "Other"
        final JMenu other = new JMenu("Other");
        other.setMnemonic('O');

        this.reset = new JMenuItem("Reset");
        this.reset.setMnemonic('R');
        this.reset.addActionListener(this);

        other.add(this.reset);

        // "About"
        this.about = new JMenuItem("About");
        this.about.setMnemonic('A');
        this.about.addActionListener(this);

        this.add(mandelbrotSet);
        this.add(juliaSet);
        this.add(other);
        this.addSeparator();
        this.add(this.about);
    }

    @Override
    public void actionPerformed(final ActionEvent e)
    {
        final JMenuItem src = (JMenuItem)e.getSource();

        if (src == this.mZoom)
            this.zoomMSet();
        else if (src == this.reset)
            this.howToReset();
        else if (src == this.about) this.showAbout();
    }

    private void zoomMSet()
    {
        JOptionPane.showMessageDialog(null,
                "With \"Zoom\" check box selected, \n\n" + "Left click on the set == Zoom In. \n"
                        + "Right click on the set == Zoom Out. \n",
                "How to Zoom Mandelbrot Set", JOptionPane.PLAIN_MESSAGE);
    }

    private void howToReset()
    {
        JOptionPane.showMessageDialog(null,
                "Under \"Fractals\" menu, there is a \"Reset\" menu, \n"
                        + "choose a set you want to restore to default scale, \n"
                        + "and then the image will to restore to defualt.",
                "How to Reset", JOptionPane.PLAIN_MESSAGE);
    }

    private void showAbout()
    {
        JOptionPane.showMessageDialog(null,
                "This Application is Made by Kebang Huang \n\n" + "Copyright (C) 2009 by Computer Science IV", "About",
                JOptionPane.PLAIN_MESSAGE);
    }

    private class JSetHelp implements ActionListener
    {
        @Override
        public void actionPerformed(final ActionEvent e)
        {
            final JMenuItem src = (JMenuItem)e.getSource();

            if (src == HelpMenu.this.jZoom)
                this.zoomJSet();
            else if (src == HelpMenu.this.jGenerate)
                this.genJSet();
            else if (src == HelpMenu.this.jAnimation) this.howToAnimate();
        }

        private void zoomJSet()
        {
            JOptionPane.showMessageDialog(null,
                    "Left Click on the Set == Zoom In \n" + "Right Click on the Set == Zoom Out \n\n"
                            + "If you're in animation and under \"Line Animate\",\n" + "  the Zoom will be disabled.",
                    "How to Zoom Julia Set", JOptionPane.PLAIN_MESSAGE);
        }

        private void genJSet()
        {
            JOptionPane.showMessageDialog(null,
                    "With \"Julia Set\" check box selected, \n" + "simply click on Mandelbrot Set, \n"
                            + "the application will then automaticly \n" + "generate a corespond Julia Set for you.",
                    "How to Generate Julia Set", JOptionPane.PLAIN_MESSAGE);
        }

        private void howToAnimate()
        {
            JOptionPane.showMessageDialog(null,
                    "With \"Animation\" check box selected, \n" + "pick a way to animate the Julia Set, \n"
                            + "under \"Fractals\" --> \"Animation\" \n\n" + "Free Animation: \n"
                            + "Hold down you mouse button on Mandelbrot Set, \n"
                            + "and drag (more point will selected if you drag slowly).\n"
                            + "After release your mouse button, the animation will start. \n\n" + "Line Animation: \n"
                            + "After click on the Mandelbrot Set, \n" + "the white cross on the Mandelbrot Set \n"
                            + "will move to spot will you click, \n" + "And the animation will start.",
                    "How to Animate Julia Set", JOptionPane.PLAIN_MESSAGE);
        }
    }
}
