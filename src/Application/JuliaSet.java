package Application;

import java.awt.*;
import java.awt.image.BufferedImage;

public class JuliaSet
{
  double xMax, xMin;
  double yMax, yMin;
  double xFactor, yFactor;
  double kReal = 0, kImage = 0;
  int imgWidth, imgHeight;
  int maxIteration = 32;

  public JuliaSet(int imgWidth, int imgHeight)
  {
    // set image size
    this.imgWidth = imgWidth;
    this.imgHeight = imgHeight;

    // set x axis
    xMax = 2.0;
    xMin = -2.0;

    // set y axis
    yMax = (xMax - xMin) / imgWidth * imgHeight / 2.0;
    yMin = -yMax;
  }

  public int getWidth()
  { return imgWidth; }

  public int getHeight() 
  { return imgHeight; }

  public void setImgSize(int imgWidth, int imgHeight)
  {
    this.imgWidth = imgWidth;
    this.imgHeight = imgHeight;

    setCoordinate(xMax, xMin);
  }

  public void setCoordinate(double xMax, double xMin)
  {
    // set x axis
    this.xMax = xMax;
    this.xMin = xMin;

    // set y axis
    double yCenter = yMax - (yMax - yMin) / 2.0;
    double yLength = (xMax - xMin) / imgWidth * imgHeight / 2.0;
    yMax = yCenter + yLength;
    yMin = yCenter - yLength;
  }

  public void reset()
  {
    // set x axis
    xMax = 2.0;
    xMin = -2.0;

    // set y axis
    yMax = (xMax - xMin) / imgWidth * imgHeight / 2.0;
    yMin = -yMax;

    maxIteration = 32;
  }

  public void zoomIN(double xCenter, double yCenter)
  {
    // set x axis
    double xLength = (xMax - xMin) / 3;
    xMax = xCenter + xLength;
    xMin = xCenter - xLength;

    // set y axis
    double yLength = (yMax - yMin) / 3;
    yMax = yCenter + yLength;
    yMin = yCenter - yLength;

    maxIteration += 3;
  }

  public void zoomOUT(double xCenter, double yCenter)
  {
    // set x axis
    double xLength = (xMax - xMin) / 2 * 1.5;
    xMax = xCenter + xLength;
    xMin = xCenter - xLength;

    // set y axis
    double yLength = (yMax - yMin) / 2 * 1.5;
    yMax = yCenter + yLength;
    yMin = yCenter - yLength;

    maxIteration -= 3;
  }

  public void setK(double kReal, double kImage)
  {
    // set K value
    this.kReal = kReal;
    this.kImage = kImage;
  }

  public BufferedImage generate()
  {
    BufferedImage julia = new BufferedImage(imgWidth, imgHeight,
				BufferedImage.TYPE_INT_RGB);
    
    xFactor = (xMax - xMin) / (imgWidth - 1.0);
    yFactor = (yMax - yMin) / (imgHeight - 1.0);
    double iterationFactor = 1276.0 / maxIteration;
    int count = 0;

    for (int x = 0; x < imgWidth; x++)
    {
      double zRealTemp = x * xFactor + xMin;
      
      for (int y = 0; y < imgHeight; y++)
      {
        double zReal = zRealTemp;
        double zImage = y * yFactor + yMin;
        boolean isInside = true;
        count = 0;

        while(count < maxIteration)
        {
          count++;

          double zReal2 = zReal * zReal,
                 zImage2 = zImage * zImage;

          if (zReal2 + zImage2 > 4)
          {
            isInside = false;
            break;
          }

          zImage = 2 * zReal * zImage + kImage;
          zReal = zReal2 - zImage2 + kReal;
        }
        
        if (isInside)
          julia.setRGB(x, y, 0 << 16 | 0 << 8 | 0);
        else
        {
          int totalRGB = (int)(count * iterationFactor - 1);
          int choice = totalRGB / 255;
          int remainValue = (totalRGB - choice * 255) % 255;
          
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
              julia.setRGB(x, y, remainValue << 16 |0 << 8 | 255);
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

  public double getX(int xPixel)
  { return xPixel * xFactor + xMin; }

  public double getY(int yPixel)
  { return yPixel * yFactor + yMin; }
}