package Applet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class MandelbrotPanel extends JPanel
{
    private MandelbrotSet mandelbrotSet;
    private final JuliaPanel juliaPanel;
    private final FreeAnimation freeAnimate;
    private final LineAnimation lineAnimate;
    private BufferedImage mandelbrotImage;

    private boolean clickZoom = false, clickGen = true, animation = false;
    private int animateType = FractalsMenu.FREE_ANIMATE;

    public MandelbrotPanel(final JuliaPanel juliaPanel)
    {
        this.juliaPanel = juliaPanel;

        this.freeAnimate = new FreeAnimation(juliaPanel);
        this.lineAnimate = new LineAnimation(this);

        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        this.setBackground(Color.white);

        final MyOwnMouseListener mouse = new MyOwnMouseListener();
        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);
    }

    public void reset()
    {
        this.mandelbrotSet.reset();

        this.repaint();
    }

    public void setClickZoom(final boolean clickZoom)
    {
        this.clickZoom = clickZoom;
    }

    public void setClickGen(final boolean clickGen)
    {
        this.clickGen = clickGen;
    }

    public void setAnimation(final boolean animation)
    {
        this.animation = animation;
        if (!animation)
        {
            this.freeAnimate.stopAnimate();
            this.lineAnimate.stopAnimate();
            this.juliaPanel.setClickZoom(true);
        }
        else
        {
            if (this.animateType == FractalsMenu.LINE_ANIMATE)
            {
                this.juliaPanel.setClickZoom(false);
                this.juliaPanel.reset();
                this.lineAnimate.startAnimate();
            }
        }
    }

    public void setAnimateSpeed(final int value)
    {
        this.freeAnimate.setSpeed(value);
        this.lineAnimate.setSpeed(value);
    }

    public void setAnimateType(final int type)
    {
        this.animateType = type;
        if (this.animation)
        {
            if (this.animateType == FractalsMenu.FREE_ANIMATE)
            {
                this.lineAnimate.stopAnimate();
                this.juliaPanel.setClickZoom(true);
            }
            else if (this.animateType == FractalsMenu.LINE_ANIMATE)
            {
                this.freeAnimate.stopAnimate();
                this.juliaPanel.setClickZoom(false);
                this.juliaPanel.reset();
                this.lineAnimate.startAnimate();
            }
        }
    }

    public void juliaGen(final double x, final double y)
    {
        this.juliaPanel.generate(this.mandelbrotSet.getCReal(x), this.mandelbrotSet.getCImage(y));
    }

    @Override
    public void paint(final Graphics g)
    {
        super.paint(g);

        if (this.mandelbrotSet == null)
        {
            this.mandelbrotSet = new MandelbrotSet(this.getWidth() - 4, this.getHeight() - 4);
            this.mandelbrotImage = this.mandelbrotSet.generate();
        }
        else
        {
            if (this.mandelbrotSet.getWidth() != this.getWidth() - 4
                    || this.mandelbrotSet.getHeight() != this.getHeight() - 4)
                this.mandelbrotSet.setImgSize(this.getWidth() - 4, this.getHeight() - 4);
        }
        if (this.clickZoom) this.mandelbrotImage = this.mandelbrotSet.generate();

        try
        {
            g.drawImage(this.mandelbrotImage, 2, 2, null);
        }
        catch (final Exception e)
        {}

        if (this.animation && this.animateType == FractalsMenu.LINE_ANIMATE)
        {
            final Graphics2D g2 = (Graphics2D)g;

            final double x = this.lineAnimate.getXBegin(), y = this.lineAnimate.getYBegin();
            final Line2D.Double line1 = new Line2D.Double(x - 2, y, x + 2, y);
            final Line2D.Double line2 = new Line2D.Double(x, y - 2, x, y + 2);

            g2.setColor(Color.white);
            g2.draw(line1);
            g2.draw(line2);
        }
    }

    private class MyOwnMouseListener implements MouseListener, MouseMotionListener
    {
        private ArrayList<Point2D.Double> juliaList;

        @Override
        public void mousePressed(final MouseEvent e)
        {
            if (MandelbrotPanel.this.animation && MandelbrotPanel.this.animateType == FractalsMenu.FREE_ANIMATE)
                this.juliaList = new ArrayList<Point2D.Double>();
        }

        @Override
        public void mouseReleased(final MouseEvent e)
        {
            if (MandelbrotPanel.this.animation && MandelbrotPanel.this.animateType == FractalsMenu.FREE_ANIMATE)
            {
                MandelbrotPanel.this.juliaPanel.reset();
                for (final Point2D.Double p : this.juliaList)
                {
                    final double x = MandelbrotPanel.this.mandelbrotSet.getCReal(p.getX()),
                            y = MandelbrotPanel.this.mandelbrotSet.getCImage(p.getY());
                    p.setLocation(x, y);
                }
                MandelbrotPanel.this.freeAnimate.startAnimate(this.juliaList);
            }
        }

        @Override
        public void mouseClicked(final MouseEvent e)
        {
            final double x = MandelbrotPanel.this.mandelbrotSet.getCReal(e.getX()),
                    y = MandelbrotPanel.this.mandelbrotSet.getCImage(e.getY());

            if (MandelbrotPanel.this.clickZoom)
            {
                if (e.getButton() == MouseEvent.BUTTON1)
                {
                    MandelbrotPanel.this.mandelbrotSet.zoomIN(x, y);
                    MandelbrotPanel.this.repaint();
                }
                else if (e.getButton() == MouseEvent.BUTTON3)
                {
                    MandelbrotPanel.this.mandelbrotSet.zoomOUT(x, y);
                    MandelbrotPanel.this.repaint();
                }
            }

            if (MandelbrotPanel.this.clickGen)
            {
                MandelbrotPanel.this.juliaPanel.reset();
                MandelbrotPanel.this.juliaPanel.generate(x, y);
            }

            if (MandelbrotPanel.this.lineAnimate.isRunning())
            {
                MandelbrotPanel.this.lineAnimate.setIncrease(e.getX(), e.getY());
            }
        }

        @Override
        public void mouseEntered(final MouseEvent e)
        {}

        @Override
        public void mouseExited(final MouseEvent e)
        {}

        @Override
        public void mouseDragged(final MouseEvent e)
        {
            if (MandelbrotPanel.this.animation && MandelbrotPanel.this.animateType == FractalsMenu.FREE_ANIMATE)
                this.juliaList.add(new Point2D.Double(e.getX(), e.getY()));
        }

        @Override
        public void mouseMoved(final MouseEvent e)
        {}
    }
}
