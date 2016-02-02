package Application;

import java.awt.image.BufferedImage;

public class JuliaSet
{
    double xMax, xMin;
    double yMax, yMin;
    double xFactor, yFactor;
    double kReal = 0, kImage = 0;
    int imgWidth, imgHeight;
    int maxIteration = 32;

    public JuliaSet(final int imgWidth, final int imgHeight)
    {
        // set image size
        this.imgWidth = imgWidth;
        this.imgHeight = imgHeight;

        // set x axis
        this.xMax = 2.0;
        this.xMin = -2.0;

        // set y axis
        this.yMax = (this.xMax - this.xMin) / imgWidth * imgHeight / 2.0;
        this.yMin = -this.yMax;
    }

    public int getWidth()
    {
        return this.imgWidth;
    }

    public int getHeight()
    {
        return this.imgHeight;
    }

    public void setImgSize(final int imgWidth, final int imgHeight)
    {
        this.imgWidth = imgWidth;
        this.imgHeight = imgHeight;

        this.setCoordinate(this.xMax, this.xMin);
    }

    public void setCoordinate(final double xMax, final double xMin)
    {
        // set x axis
        this.xMax = xMax;
        this.xMin = xMin;

        // set y axis
        final double yCenter = this.yMax - (this.yMax - this.yMin) / 2.0;
        final double yLength = (xMax - xMin) / this.imgWidth * this.imgHeight / 2.0;
        this.yMax = yCenter + yLength;
        this.yMin = yCenter - yLength;
    }

    public void reset()
    {
        // set x axis
        this.xMax = 2.0;
        this.xMin = -2.0;

        // set y axis
        this.yMax = (this.xMax - this.xMin) / this.imgWidth * this.imgHeight / 2.0;
        this.yMin = -this.yMax;

        this.maxIteration = 32;
    }

    public void zoomIN(final double xCenter, final double yCenter)
    {
        // set x axis
        final double xLength = (this.xMax - this.xMin) / 3;
        this.xMax = xCenter + xLength;
        this.xMin = xCenter - xLength;

        // set y axis
        final double yLength = (this.yMax - this.yMin) / 3;
        this.yMax = yCenter + yLength;
        this.yMin = yCenter - yLength;

        this.maxIteration += 3;
    }

    public void zoomOUT(final double xCenter, final double yCenter)
    {
        // set x axis
        final double xLength = (this.xMax - this.xMin) / 2 * 1.5;
        this.xMax = xCenter + xLength;
        this.xMin = xCenter - xLength;

        // set y axis
        final double yLength = (this.yMax - this.yMin) / 2 * 1.5;
        this.yMax = yCenter + yLength;
        this.yMin = yCenter - yLength;

        this.maxIteration -= 3;
    }

    public void setK(final double kReal, final double kImage)
    {
        // set K value
        this.kReal = kReal;
        this.kImage = kImage;
    }

    public BufferedImage generate()
    {
        final BufferedImage julia = new BufferedImage(this.imgWidth, this.imgHeight, BufferedImage.TYPE_INT_RGB);

        this.xFactor = (this.xMax - this.xMin) / (this.imgWidth - 1.0);
        this.yFactor = (this.yMax - this.yMin) / (this.imgHeight - 1.0);
        final double iterationFactor = 1276.0 / this.maxIteration;
        int count = 0;

        for (int x = 0; x < this.imgWidth; x++)
        {
            final double zRealTemp = x * this.xFactor + this.xMin;

            for (int y = 0; y < this.imgHeight; y++)
            {
                double zReal = zRealTemp;
                double zImage = y * this.yFactor + this.yMin;
                boolean isInside = true;
                count = 0;

                while (count < this.maxIteration)
                {
                    count++;

                    final double zReal2 = zReal * zReal, zImage2 = zImage * zImage;

                    if (zReal2 + zImage2 > 4)
                    {
                        isInside = false;
                        break;
                    }

                    zImage = 2 * zReal * zImage + this.kImage;
                    zReal = zReal2 - zImage2 + this.kReal;
                }

                if (isInside)
                    julia.setRGB(x, y, 0 << 16 | 0 << 8 | 0);
                else
                {
                    final int totalRGB = (int)(count * iterationFactor - 1);
                    final int choice = totalRGB / 255;
                    final int remainValue = (totalRGB - choice * 255) % 255;

                    switch (choice)
                    {
                        case 0:
                            julia.setRGB(x, y, 255 << 16 | remainValue << 8 | 0);
                            break;

                        case 1:
                            julia.setRGB(x, y, 255 - remainValue << 16 | 255 << 8 | 0);
                            break;

                        case 2:
                            julia.setRGB(x, y, 0 << 16 | 255 << 8 | remainValue);
                            break;

                        case 3:
                            julia.setRGB(x, y, 0 << 16 | 255 - remainValue << 8 | 255);
                            break;

                        case 4:
                            julia.setRGB(x, y, remainValue << 16 | 0 << 8 | 255);
                            break;

                        default:
                            julia.setRGB(x, y, 255 << 16 | 0 << 8 | 255);
                            break;
                    }
                }
            }
        }

        return julia;
    }

    public double getX(final int xPixel)
    {
        return xPixel * this.xFactor + this.xMin;
    }

    public double getY(final int yPixel)
    {
        return yPixel * this.yFactor + this.yMin;
    }
}
