package Applet;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ControlPanel extends JPanel implements ActionListener, ChangeListener
{
    private final Fractals myFractals;
    private final JCheckBox clickZoom, clickGen, animation;

    public ControlPanel(final Fractals fractals)
    {
        this.myFractals = fractals;

        this.setLayout(new GridLayout(1, 2));

        // Mandelbrot Control
        final JPanel mandelbrotControl = new JPanel();
        mandelbrotControl.setLayout(new FlowLayout());
        mandelbrotControl.setBorder(BorderFactory.createTitledBorder("Mandelbrot Set"));

        // Zoom CheckBox
        this.clickZoom = new JCheckBox("Zoom", false);
        this.clickZoom.addActionListener(this);
        mandelbrotControl.add(this.clickZoom);

        // Generate CheckBox
        this.clickGen = new JCheckBox("Julia Set", true);
        this.clickGen.addActionListener(this);
        mandelbrotControl.add(this.clickGen);

        // Animation CheckBox
        this.animation = new JCheckBox("Animation", false);
        this.animation.addActionListener(this);
        mandelbrotControl.add(this.animation);

        // Julia Control
        final JPanel juliaControl = new JPanel();
        juliaControl.setLayout(new FlowLayout());
        juliaControl.setBorder(BorderFactory.createTitledBorder("Julia Set"));

        // Animation Speed Control
        final JLabel speed = new JLabel("Speed: ");
        juliaControl.add(speed);

        final JSlider speedControl = new JSlider(SwingConstants.HORIZONTAL, 100, 250, 175);
        speedControl.addChangeListener(this);
        juliaControl.add(speedControl);

        // Add Panels
        this.add(mandelbrotControl);
        this.add(juliaControl);
    }

    @Override
    public void actionPerformed(final ActionEvent e)
    {
        final JCheckBox src = (JCheckBox)e.getSource();

        if (src == this.clickZoom)
        {
            this.animation.setSelected(false);
            this.myFractals.setClickZoom(this.clickZoom.isSelected());
            this.myFractals.setAnimation(this.animation.isSelected());
        }
        else if (src == this.clickGen)
        {
            this.animation.setSelected(false);
            this.myFractals.setClickGen(this.clickGen.isSelected());
            this.myFractals.setAnimation(this.animation.isSelected());
        }
        else if (src == this.animation)
        {
            this.clickZoom.setSelected(false);
            this.clickGen.setSelected(false);
            this.myFractals.setClickZoom(this.clickZoom.isSelected());
            this.myFractals.setClickGen(this.clickGen.isSelected());
            this.myFractals.setAnimation(this.animation.isSelected());
        }
    }

    @Override
    public void stateChanged(final ChangeEvent e)
    {
        this.myFractals.setAnimateSpeed(350 - ((JSlider)e.getSource()).getValue());
    }
}
