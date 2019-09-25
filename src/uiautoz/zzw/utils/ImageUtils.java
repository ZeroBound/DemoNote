package uiautoz.zzw.utils;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

/**
 * @author zzw
 *
 * 图片处理工具类
 */
public class ImageUtils {

	/** to see cutImage **/
	public static void cutJPG(InputStream input, OutputStream out, int x, int y,
							  int width, int height) throws IOException {

		cutImage(input, out, "jpg", x, y, width, height);
	}

	/** to see cutImage, this png type **/
	public static void cutPNG(InputStream input, OutputStream out, int x, int y,
							  int width, int height) throws IOException {

		cutImage(input, out, "png", x, y, width, height);
	}

	/**
	 * 裁剪图片
	 *
	 * @param input InputStream
	 * @param out OutputStream
	 * @param type Image type
	 * @param x point x
	 * @param y point y
	 * @param width image w
	 * @param height image h
	 * @throws IOException
	 */
	public static void cutImage(InputStream input, OutputStream out, String type,
								int x, int y, int width, int height) throws IOException {

		// 判断 image 图片格式
		String imageType = (null == type || "".equals(type)) ? "jpg" : type;

		ImageInputStream imageStream = null;
		try {
			Iterator<ImageReader> readers = ImageIO .getImageReadersByFormatName(imageType);

			ImageReader reader = readers.next();
			imageStream = ImageIO.createImageInputStream(input);
			reader.setInput(imageStream, true);

			// System.out.println(reader.getWidth(0) + reader.getHeight(0));

			ImageReadParam param = reader.getDefaultReadParam();
			Rectangle rect = new Rectangle(x, y, width, height);
			param.setSourceRegion(rect);
			BufferedImage bi = reader.read(0, param);
			ImageIO.write(bi, imageType, out);
		} finally {
			if (imageStream != null) {
				imageStream.close();
			}
		}
	}

	public static boolean sameAs(String tagImgPath, String myImgPath) throws IOException {
		return sameAs(tagImgPath, myImgPath, 100);
	}

	/**
	 * 图片相似度对比(同坐标点颜色之比)
	 *
	 * @param targetImgPath 目标图片路径
	 * @param myImgPath 标准图片路径
	 * @param percent 相似度比标准
	 * @return True if the similarity of the two pictures is larger than the specified range.
	 * @throws IOException img path is error
	 */
	public static boolean sameAs(String targetImgPath, String myImgPath, double percent) throws IOException {

		percent = (percent < 0) ? 1 : (percent > 100) ? 100.00 : percent;

		double percentage = percent / 100d;

		BufferedImage otherImage = ImageIO.read(new FileInputStream(targetImgPath));
		BufferedImage myImage = ImageIO.read(new FileInputStream(myImgPath));


		int width = myImage.getWidth();
		int height = myImage.getHeight();
		double numberPixels = height * width;
		int diffPixel = 0;

		if(width != otherImage.getWidth() || height != otherImage.getHeight()){
			return false;
		}
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				// 获取坐标点的颜色
				if (myImage.getRGB(x, y) != otherImage.getRGB(x, y)) {
					diffPixel++;
				}
			}
		}

		// 小于标准相似度，则不是同一种图片
		return percentage <= 1.0 - (diffPixel / numberPixels);
	}
}