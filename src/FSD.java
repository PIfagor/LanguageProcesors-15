import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @param args
 * 
 *            //Floyd Steinberg dithering // //Follow these steps: //1. Create
 *            an integer array that can hold 1 value for each pixel // in the
 *            image. Parameters h and w are the image height and width. // //2.
 *            Copy the image data to the integer array. // Each 3-bytes in
 *            ucdata will be stored as 1 integer. // //3. Apply Floyd-Steinberg
 *            algorithm: // Start at the beginning of the integer data. // At
 *            each pixel... // Set the pixel to either black or white // Record
 *            the change in color that happened // Distribute this change
 *            (error) to the pixels around it // using the following pattern: //
 *            // * 7/16 // 3/16 5/16 1/16 // // This means: // Asterisk is the
 *            current pixel. // Add 7/16 of the error to the pixel to the right
 *            // Add 3/16 of the error to the pixel to the lower-left // Add
 *            5/16 of the error to the pixel below // Add 1/16 of the error to
 *            the pixel to the lower-right // // Check pixel coordinates to
 *            verify that they are not outside // the bounds of the image. //
 *            //4. Put integer pixels back into the original data structure.
 *            //5. Display the image on screen. //6. Free the integer array.
 * 
 * 
 *            //Floyd Steinberg dithering algorithm...
 */

public class FSD {

	public BufferedImage img = null;
	public BufferedImage resultImg = null;
	public int _H;
	public int _W;
	C3[][] pixels;
	int[] intPixel;

	public FSD(String imgPath) {

		try {
			img = ImageIO.read(new File(imgPath));
		} catch (IOException e) {
			System.err.println("Some problems");
		}
		_H = img.getHeight();
		_W = img.getWidth();
		;
		pixels = new C3[_W][_H];
		intPixel = new int[_W * _H * 3];
	}

	public void getPixels() {
		for (int j = 0; j < _W; j++) {
			for (int i = 0; i < _H; i++) {
				pixels[j][i] = new C3(img.getRGB(j, i));
			}
		}
	}

	public void makeLineChars() {
		int index = -1;
		for (int j = 0; j < _W; j++) {
			for (int i = 0; i < _H; i++) {

				intPixel[++index] = pixels[j][i].r;
				intPixel[++index] = pixels[j][i].g;
				intPixel[++index] = pixels[j][i].b;

			}
		}
	}

	public static Image getImageFromArray(int[] pixels, int width, int height) {
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);
		WritableRaster raster = (WritableRaster) image.getData();
		raster.setPixels(0, 0, width, height, pixels);
		return image;
	}

	public static int[] FloydSteinbergDithering(int[] pixel, int w, int h) {

		int[] result = new int[(pixel.length / 3) + 1];

		for (int i = 1; i < result.length; i++) {
			result[i - 1] = ((pixel[i * 3 - 3] & 0x0ff) << 16)
					| ((pixel[i * 3 - 2] & 0x0ff) << 8)
					| (pixel[i * 3 - 1] & 0x0ff);
		}

		for (int i = 0; i < result.length; i++) {
			int oldPixel = result[i];
			int newPixel = findClosestPalleteColor(oldPixel, 1);
			int quantError = oldPixel - newPixel;

			result[i] = newPixel;

			if (i % w != 0||i==0)
				result[i + 1] += quantError * threeInOne(Math.floor(7 / 16));
			if (i % (w + 1) != 0 && i <= (w * (h - 1)))
				result[i + w - 1] += quantError * threeInOne(Math.floor(3	/ 16));
			if (i <= (w * (h - 1)))
				result[i + w] += quantError * threeInOne(Math.floor(5 / 16));
			if ((i <= (w * (h - 1)) && i % w != 0)||i==0)
				result[i + w + 1] += quantError * threeInOne(Math.floor(1	/ 16));
		}

		for (int i = 1; i < result.length; i++) {
			pixel[i * 3 - 3] = (result[i - 1] >> 16) & 0x0ff;
			pixel[i * 3 - 2] = (result[i - 1] >> 8) & 0x0ff;
			pixel[i * 3 - 1] = (result[i - 1]) & 0x0ff;
		}

		return pixel;
	};

	public static int threeInOne(int sourse) {
		return ((sourse & 0x0ff) << 16) | ((sourse & 0x0ff) << 8)
				| (sourse & 0x0ff);
	}
	
	public static int threeInOne(double ssourse) {
		int sourse = (int) ssourse;
		return ((sourse & 0x0ff) << 16) | ((sourse & 0x0ff) << 8)
				| (sourse & 0x0ff);
	}
	private static int findClosestPalleteColor(int oldPixel, int type) {
		int black = ((0 & 0x0ff) << 16) | ((0 & 0x0ff) << 8) | (0 & 0x0ff);
		int white = ((255 & 0x0ff) << 16) | ((255 & 0x0ff) << 8)
				| (255 & 0x0ff);

		int red = ((255 & 0x0ff) << 16) | ((0 & 0x0ff) << 8) | (0 & 0x0ff);
		int green = ((0 & 0x0ff) << 16) | ((255 & 0x0ff) << 8) | (0 & 0x0ff);
		int blue = ((0 & 0x0ff) << 16) | ((0 & 0x0ff) << 8) | (255 & 0x0ff);

		int magenta = ((255 & 0x0ff) << 16) | ((0 & 0x0ff) << 8)| (255 & 0x0ff);
		int yellow = ((255 & 0x0ff) << 16) | ((255 & 0x0ff) << 8) | (0 & 0x0ff);
		int cyan = ((0 & 0x0ff) << 16) | ((255 & 0x0ff) << 8) | (255 & 0x0ff);

		int[] pallete = new int[8];
		pallete[0] = white;
		pallete[1] = black;
		pallete[2] = red;
		pallete[3] = green;
		pallete[4] = blue;
		pallete[5] = magenta;
		pallete[6] = yellow;
		pallete[7] = cyan;
		switch (type) {
		case 0:
			if (Math.abs(black - oldPixel) < Math.abs(white - oldPixel))
				return black;
			else
				return white;
		case 1:
			int closerColor =  Math.abs(pallete[0] - oldPixel);
			for (int i = 1; i < pallete.length; i++) {
				int tempCloser = Math.abs(pallete[i] - oldPixel);
				if (tempCloser < closerColor)
					closerColor = tempCloser;
			}
			return closerColor;
		case 2:
			return oldPixel / 256;

		default:
			System.err.print("Wrong type");
			// throw(new Exception("Wrong type"));
			return oldPixel;

		}

	}

	public void saveImg(int[] charPixel) {

		int ind = -1;
		for (int i = 0; i < _W; i++) {
			for (int j = 0; j < _H; j++) {

				img.setRGB(i, j, new Color(charPixel[++ind], charPixel[++ind],
						charPixel[++ind]).getRGB());
			}
		}
		String format = "png";
		File outputfile = new File("res/saved." + format);
		try {
			ImageIO.write(img, format, outputfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		String path = "res/dog.png";
		String path2 = "res/black.png";
		String path3 = "res/sunrise.png";
		String path4 = "res/lena.gif";
		String path1 = "res/rgb.jpg";
		FSD main = new FSD(path1);

		main.getPixels();
		main.makeLineChars();

		main.saveImg(FSD.FloydSteinbergDithering(main.intPixel, main._W,
				main._H));

		
//		int  [] img = new   int  [] {123, 123, 100, 234, 234, 234, 123, 123, 100, 234, 234, 234, 123, 123, 100, 234, 234, 234, 128,128,128,129,129,129,127,127,127};
//		int w = 3;
//		int h = 3;
//		
//		for (int i = 0; i < img.length; i++) {
//			if (i%3 == 0) {
//				System.out.println();
//			}
//			System.out.print(img[i]+" ");
//		}
//		int [] res = FSD.FloydSteinbergDithering(img,w,h);
//		
//		for (int i = 0; i < res.length; i++) {
//			if (i%3 == 0) {
//				System.out.println();
//			}
//			System.out.print(res[i]+"\t");
//		}
//		
//		for (int i = 0; i < main._W; i++) {
//			for (int j = 0; j < main._H; j++) {
//				 System.out.println(main.pixels[i][j]);
//			}
//		}


		System.out.println("All gii");

	}

	public class C3 {
		public int r, g, b;

		public C3(int c) {
			Color color = new Color(c);
			this.r = color.getRed();
			this.g = color.getGreen();
			this.b = color.getBlue();
		}

		@Override
		public String toString() {
			String res = "r:" + r + " g:" + g + " b" + b;
			return res;
		}

		public int toRGB() {
			return toColor().getRGB();
		}

		public Color toColor() {
			return new Color(clamp(r), clamp(g), clamp(b));
		}

		public int clamp(int c) {
			return Math.max(0, Math.min(255, c));
		}
	}

}
