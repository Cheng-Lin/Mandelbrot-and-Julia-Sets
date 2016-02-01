package Application;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ControlPanel extends JPanel
  implements ActionListener, ChangeListener
{
  private Fractals myFractals;
  private JCheckBox clickZoom, clickGen, animation;
  private JSlider speedControl;
  
  public ControlPanel(Fractals fractals)
  {
    myFractals = fractals;

    setLayout(new GridLayout(1, 2));
    
    //Mandelbrot Control
    JPanel mandelbrotControl = new JPanel();
    mandelbrotControl.setLayout(new FlowLayout());
    mandelbrotControl.setBorder(BorderFactory.createTitledBorder("Mandelbrot Set"));

    //Zoom CheckBox
    clickZoom = new JCheckBox("Zoom", false);
    clickZoom.addActionListener(this);
    mandelbrotControl.add(clickZoom);

    //Generate CheckBox
    clickGen = new JCheckBox("Julia Set", true);
    clickGen.addActionListener(this);
    mandelbrotControl.add(clickGen);

    //Animation CheckBox
    animation = new JCheckBox("Animation", false);
    animation.addActionListener(this);
    mandelbrotControl.add(animation);

    //Julia Control
    JPanel juliaControl = new JPanel();
    juliaControl.setLayout(new FlowLayout());
    juliaControl.setBorder(BorderFactory.createTitledBorder("Julia Set"));

    //Animation Speed Control
    JLabel speed = new JLabel("Speed: ");
    juliaControl.add(speed);

    JSlider speedControl = new JSlider(JSlider.HORIZONTAL, 100, 250, 175);
    speedControl.addChangeListener(this);
    juliaControl.add(speedControl);

    //Add Panels
    add(mandelbrotControl);
    add(juliaControl);
  }

  public void actionPerformed(ActionEvent e)
  {
    JCheckBox src = (JCheckBox)e.getSource();

    if (src == clickZoom)
    {
      animation.setSelected(false);
      myFractals.setClickZoom(clickZoom.isSelected());
      myFractals.setAnimation(animation.isSelected());
    }
    else if (src == clickGen)
    {
      animation.setSelected(false);
      myFractals.setClickGen(clickGen.isSelected());
      myFractals.setAnimation(animation.isSelected());
    }
    else if (src == animation)
    {
      clickZoom.setSelected(false);
      clickGen.setSelected(false);
      myFractals.setClickZoom(clickZoom.isSelected());
      myFractals.setClickGen(clickGen.isSelected());
      myFractals.setAnimation(animation.isSelected());
    }
  }

  public void stateChanged(ChangeEvent e)
  {
    myFractals.setAnimateSpeed(350 - ((JSlider)e.getSource()).getValue());
  }
}