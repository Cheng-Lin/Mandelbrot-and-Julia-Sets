package Application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.Timer;

public class FreeAnimation implements ActionListener
{
    private final JuliaPanel jPanel;
    private final Timer freeAnimate;
    private ArrayList<Point2D.Double> points;
    private int count = -1;

    public FreeAnimation(final JuliaPanel jPanel)
    {
        this.jPanel = jPanel;
        this.freeAnimate = new Timer(175, this);
    }

    public void setSpeed(final int value)
    {
        this.freeAnimate.setDelay(value);
    }

    public void startAnimate(final ArrayList<Point2D.Double> points)
    {
        this.points = points;
        this.freeAnimate.start();
    }

    @Override
    public void actionPerformed(final ActionEvent e)
    {
        if (this.count < this.points.size() - 2)
            this.count++;
        else
            this.count = 0;

        try
        {
            this.jPanel.generate(this.points.get(this.count).getX(), this.points.get(this.count).getY());
        }
        catch (final IndexOutOfBoundsException ex)
        {}
    }

    public void stopAnimate()
    {
        this.count = -1;
        this.points = null;
        this.freeAnimate.stop();
    }
}
