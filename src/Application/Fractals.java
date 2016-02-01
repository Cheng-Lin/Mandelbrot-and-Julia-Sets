package Application;

import java.awt.*;
import javax.swing.*;

public class Fractals extends JFrame
{
	private FractalsMenuBar menuBar;
	private MandelbrotPanel mandelbrotSet;
	private JuliaPanel juliaSet;
	private JPanel juliaSets;
	private ControlPanel control;

	public Fractals()
	{
		super("Mandelbrot & Julia Sets");
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

	public void saveM(int width, int height)
	{ mandelbrotSet.saveImage(width, height); }

	public void saveJ(int width, int height)
	{ juliaSet.saveImage(width, height); }

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

	public static void main(String[] args)
	{
		Fractals windows = new Fractals();
		windows.setSize(608, 387);
		windows.show();
	}
}