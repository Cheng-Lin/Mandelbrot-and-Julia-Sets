package Applet;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JApplet;
import javax.swing.JPanel;

public class Fractals extends JApplet
{
    private FractalsMenuBar menuBar;
    private MandelbrotPanel mandelbrotSet;
    private JuliaPanel juliaSet;
    private ControlPanel control;

    @Override
    public void init()
    {
        // Menubar
        this.menuBar = new FractalsMenuBar(this);
        this.setJMenuBar(this.menuBar);

        // Main Panel
        final JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new GridLayout(1, 2));

        // Julia Set Panel
        this.juliaSet = new JuliaPanel();

        // Mandelbrot Set Panel
        this.mandelbrotSet = new MandelbrotPanel(this.juliaSet);

        // add to main panel
        imagePanel.add(this.mandelbrotSet);
        imagePanel.add(this.juliaSet);

        // Control Panel
        this.control = new ControlPanel(this);

        final Container c = this.getContentPane();
        c.add(imagePanel, BorderLayout.CENTER);
        c.add(this.control, BorderLayout.SOUTH);
    }

    public void newGenM()
    {
        this.mandelbrotSet.reset();
    }

    public void newGenJ()
    {
        this.juliaSet.reset();
    }

    public void setClickZoom(final boolean clickZoom)
    {
        this.mandelbrotSet.setClickZoom(clickZoom);
    }

    public void setClickGen(final boolean clickGen)
    {
        this.mandelbrotSet.setClickGen(clickGen);
    }

    public void setAnimation(final boolean animation)
    {
        this.mandelbrotSet.setAnimation(animation);
    }

    public void setAnimateSpeed(final int value)
    {
        this.mandelbrotSet.setAnimateSpeed(value);
    }

    public void setAnimateType(final int type)
    {
        this.mandelbrotSet.setAnimateType(type);
    }
}
