package Application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class LineAnimation implements ActionListener
{
    private final MandelbrotPanel mPanel;
    private final Timer lineAnimate;
    private double xBegin, yBegin, xEnd, yEnd;
    private double xIncrease, yIncrease;
    private int count = 0, steps = 0;;

    public LineAnimation(final MandelbrotPanel mPanel)
    {
        this.mPanel = mPanel;
        this.lineAnimate = new Timer(175, this);
    }

    public void setSpeed(final int value)
    {
        this.lineAnimate.setDelay(value);
    }

    public void startAnimate()
    {
        this.xBegin = this.mPanel.getWidth() / 2;
        this.yBegin = this.mPanel.getHeight() / 2;
        this.xEnd = this.xBegin;
        this.yEnd = this.yBegin;
        this.xIncrease = 0.0;
        this.yIncrease = 0.0;

        this.lineAnimate.start();
    }

    public void setIncrease(final int xEnd, final int yEnd)
    {
        this.xEnd = xEnd;
        this.yEnd = yEnd;

        final double xSlope = this.xEnd - (int)this.xBegin, ySlope = this.yEnd - (int)this.yBegin;

        this.count = 0;
        this.steps = (int)(Math.max(Math.abs(xSlope), Math.abs(ySlope)) + 0.5);

        this.xIncrease = xSlope / this.steps;
        this.yIncrease = ySlope / this.steps;
    }

    @Override
    public void actionPerformed(final ActionEvent e)
    {
        if (this.count < this.steps)
        {
            this.xBegin += this.xIncrease;
            this.yBegin += this.yIncrease;
            this.count++;
        }

        this.mPanel.juliaGen(this.xBegin, this.yBegin);
        this.mPanel.repaint();
    }

    public void stopAnimate()
    {
        this.lineAnimate.stop();
    }

    public double getXBegin()
    {
        return this.xBegin;
    }

    public double getYBegin()
    {
        return this.yBegin;
    }

    public boolean isRunning()
    {
        return this.lineAnimate.isRunning();
    }
}
