package Applet;

import java.awt.*;
import javax.swing.*;

public class Fractals extends JApplet
{
	private FractalsMenuBar menuBar;
	private MandelbrotPanel mandelbrotSet;
	private JuliaPanel juliaSet;
	private JPanel juliaSets;
	private ControlPanel control;

	@Override
	public void init()
	{
		//Menubar
		menuBar = new FractalsMenuBar(this);
		setJMenuBar(menuBar);

		//Main Panel
		JPanel imagePanel = new JPanel();
		imagePanel.setLayout(new GridLayout(1, 2));

		//Julia Set Panel
		juliaSet = new JuliaPanel();

		//Mandelbrot Set Panel
		mandelbrotSet = new MandelbrotPanel(juliaSet);

		//add to main panel
		imagePanel.add(mandelbrotSet);
		imagePanel.add(juliaSet);

		//Control Panel
		control = new ControlPanel(this);

		Container c = getContentPane();
		c.add(imagePanel, BorderLayout.CENTER);
		c.add(control,BorderLayout.SOUTH);
	}

	public void newGenM()
	{
		mandelbrotSet.reset();
	}

	public void newGenJ()
	{
		juliaSet.reset();
	}

	public void setClickZoom(boolean clickZoom)
	{ mandelbrotSet.setClickZoom(clickZoom); }

	public void setClickGen(boolean clickGen)
	{ mandelbrotSet.setClickGen(clickGen); }

	public void setAnimation(boolean animation)
	{ mandelbrotSet.setAnimation(animation); }

	public void setAnimateSpeed(int value)
	{ mandelbrotSet.setAnimateSpeed(value); }

	public void setAnimateType(int type)
	{ mandelbrotSet.setAnimateType(type); }
}