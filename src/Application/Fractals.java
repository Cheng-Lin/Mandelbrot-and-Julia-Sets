package Application;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Fractals extends JFrame
{
    private final FractalsMenuBar menuBar;
    private final MandelbrotPanel mandelbrotSet;
    private final JuliaPanel juliaSet;
    private final ControlPanel control;

    public Fractals()
    {
        super("Mandelbrot & Julia Sets");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

    public void saveM(final int width, final int height)
    {
        this.mandelbrotSet.saveImage(width, height);
    }

    public void saveJ(final int width, final int height)
    {
        this.juliaSet.saveImage(width, height);
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

    public static void main(final String[] args)
    {
        final Fractals windows = new Fractals();
        windows.setSize(608, 387);
        windows.setVisible(true);
    }
}
