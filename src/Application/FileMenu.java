package Application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class FileMenu extends JMenu implements ActionListener
{
    FractalsMenuBar menuBar;
    private final JMenuItem mSmall, mMedium, mLarge, mCustom;
    private final JMenuItem jSmall, jMedium, jLarge, jCustom;
    private final JMenuItem exit;

    public FileMenu(final FractalsMenuBar menuBar)
    {
        super("File");
        this.setMnemonic('F');

        this.menuBar = menuBar;

        // Second level menu under "Save Image"
        final JMenu saveImage = new JMenu("Save Image");
        saveImage.setMnemonic('I');
        final MSetListener mSet = new MSetListener();

        // Third level menu under "Mandlebrot Set"
        final JMenu mandelbrotSet = new JMenu("Mandelbrot Set");
        mandelbrotSet.setMnemonic('M');

        this.mSmall = new JMenuItem("600 x 600");
        this.mSmall.addActionListener(mSet);
        mandelbrotSet.add(this.mSmall);

        this.mMedium = new JMenuItem("1800 x 1800");
        this.mMedium.addActionListener(mSet);
        mandelbrotSet.add(this.mMedium);

        this.mLarge = new JMenuItem("3000 x 3000");
        this.mLarge.addActionListener(mSet);
        mandelbrotSet.add(this.mLarge);

        this.mCustom = new JMenuItem("Customize");
        this.mCustom.setMnemonic('C');
        this.mCustom.addActionListener(mSet);
        mandelbrotSet.addSeparator();
        mandelbrotSet.add(this.mCustom);

        // Third level menu under "Julia Set"
        final JMenu juliaSet = new JMenu("Julia Set");
        juliaSet.setMnemonic('J');
        final JSetListener jSet = new JSetListener();

        this.jSmall = new JMenuItem("600 x 600");
        this.jSmall.addActionListener(jSet);
        juliaSet.add(this.jSmall);

        this.jMedium = new JMenuItem("1800 x 1800");
        this.jMedium.addActionListener(jSet);
        juliaSet.add(this.jMedium);

        this.jLarge = new JMenuItem("3000 x 3000");
        this.jLarge.addActionListener(jSet);
        juliaSet.add(this.jLarge);

        this.jCustom = new JMenuItem("Customize");
        this.jCustom.setMnemonic('C');
        this.jCustom.addActionListener(jSet);
        juliaSet.addSeparator();
        juliaSet.add(this.jCustom);

        saveImage.add(mandelbrotSet);
        saveImage.add(juliaSet);

        this.exit = new JMenuItem("Exit");
        this.exit.setMnemonic('E');
        this.exit.addActionListener(this);

        this.add(saveImage);
        this.addSeparator();
        this.add(this.exit);
    }

    @Override
    public void actionPerformed(final ActionEvent e)
    {
        this.menuBar.closeApplication();
    }

    private class MSetListener implements ActionListener
    {
        @Override
        public void actionPerformed(final ActionEvent e)
        {
            final JMenuItem src = (JMenuItem)e.getSource();

            if (src == FileMenu.this.mSmall)
                FileMenu.this.menuBar.saveM(600, 600);
            else if (src == FileMenu.this.mMedium)
                FileMenu.this.menuBar.saveM(1800, 1800);
            else if (src == FileMenu.this.mLarge)
                FileMenu.this.menuBar.saveM(3000, 3000);
            else if (src == FileMenu.this.mCustom) this.saveM();
        }

        public void saveM()
        {
            // User Input Width
            final String strWidth = JOptionPane.showInputDialog("Please input the width of the image.");
            int intWidth = 0;

            // Catch Invalid Input
            try
            {
                intWidth = Integer.parseInt(strWidth);
            }
            catch (final NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(FileMenu.this.menuBar, "Invalid Number!", "Input Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (intWidth <= 0)
            {
                JOptionPane.showMessageDialog(FileMenu.this.menuBar, "Invalid Number!", "Input Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // User Input Height
            final String strHeight = JOptionPane.showInputDialog("Please input the height of the image.");
            int intHeight = 0;

            // Catch Invalid Input
            try
            {
                intHeight = Integer.parseInt(strHeight);
            }
            catch (final NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(FileMenu.this.menuBar, "Invalid Number!", "Input Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (intHeight <= 0)
            {
                JOptionPane.showMessageDialog(FileMenu.this.menuBar, "Invalid Number!", "Input Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Save Image
            FileMenu.this.menuBar.saveM(intWidth, intHeight);
        }
    }

    private class JSetListener implements ActionListener
    {
        @Override
        public void actionPerformed(final ActionEvent e)
        {
            final JMenuItem src = (JMenuItem)e.getSource();

            if (src == FileMenu.this.jSmall)
                FileMenu.this.menuBar.saveJ(600, 600);
            else if (src == FileMenu.this.jMedium)
                FileMenu.this.menuBar.saveJ(1800, 1800);
            else if (src == FileMenu.this.jLarge)
                FileMenu.this.menuBar.saveJ(3000, 3000);
            else if (src == FileMenu.this.jCustom) this.saveJ();
        }

        public void saveJ()
        {
            // User Input Width
            final String strWidth = JOptionPane.showInputDialog("Please input the width of the image.");
            int intWidth = 0;

            // Catch Invalid Input
            try
            {
                intWidth = Integer.parseInt(strWidth);
            }
            catch (final NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(FileMenu.this.menuBar, "Invalid Number!", "Input Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (intWidth <= 0)
            {
                JOptionPane.showMessageDialog(FileMenu.this.menuBar, "Invalid Number!", "Input Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // User Input Height
            final String strHeight = JOptionPane.showInputDialog("Please input the height of the image.");
            int intHeight = 0;

            // Catch Invalid Input
            try
            {
                intHeight = Integer.parseInt(strHeight);
            }
            catch (final NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(FileMenu.this.menuBar, "Invalid Number!", "Input Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (intHeight <= 0)
            {
                JOptionPane.showMessageDialog(FileMenu.this.menuBar, "Invalid Number!", "Input Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Save Image
            FileMenu.this.menuBar.saveJ(intWidth, intHeight);
        }
    }
}
