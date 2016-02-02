package Applet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class JuliaPanel extends JPanel implements MouseListener
{
    private JuliaSet juliaSet;
    private BufferedImage juliaImage;
    private boolean clickZoom = true;

    public JuliaPanel()
    {
        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        this.setBackground(Color.white);

        this.addMouseListener(this);
    }

    public void setClickZoom(final boolean clickZoom)
    {
        this.clickZoom = clickZoom;
    }

    public void reset()
    {
        this.juliaSet.reset();

        this.repaint();
    }

    public void generate(final double kReal, final double kImage)
    {
        this.juliaSet.setK(kReal, kImage);

        this.repaint();
    }

    @Override
    public void mousePressed(final MouseEvent e)
    {}

    @Override
    public void mouseReleased(final MouseEvent e)
    {}

    @Override
    public void mouseClicked(final MouseEvent e)
    {
        if (this.clickZoom)
        {
            if (e.getButton() == MouseEvent.BUTTON1)
            {
                this.juliaSet.zoomIN(this.juliaSet.getX(e.getX()), this.juliaSet.getY(e.getY()));
                this.repaint();
            }
            else if (e.getButton() == MouseEvent.BUTTON3)
            {
                this.juliaSet.zoomOUT(this.juliaSet.getX(e.getX()), this.juliaSet.getY(e.getY()));
                this.repaint();
            }
        }
    }

    @Override
    public void mouseEntered(final MouseEvent e)
    {}

    @Override
    public void mouseExited(final MouseEvent e)
    {}

    @Override
    public void paint(final Graphics g)
    {
        super.paint(g);

        if (this.juliaSet == null)
            this.juliaSet = new JuliaSet(this.getWidth() - 4, this.getHeight() - 4);
        else
        {
            if (this.juliaSet.getWidth() != this.getWidth() - 4 || this.juliaSet.getHeight() != this.getHeight() - 4)
                this.juliaSet.setImgSize(this.getWidth() - 4, this.getHeight() - 4);
        }
        this.juliaImage = this.juliaSet.generate();

        try
        {
            g.drawImage(this.juliaImage, 2, 2, null);
        }
        catch (final Exception e)
        {}
    }
}
