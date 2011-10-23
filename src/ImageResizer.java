import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;


public class ImageResizer {

	public static void main(String[] args) throws Exception {
		BufferedImage bufImage = ImageIO.read(new File("/Users/darren/Temp/test.jpeg"));
		
		// Resize
		int width = 480;
		double ratio = (double)bufImage.getWidth()/(double)bufImage.getHeight();
		int height = (int) Math.floor( width / ratio);  
		System.out.println("height; " + height + ", width: " + width + ", ratio: " + ratio + "image: " +bufImage.getWidth() + ", " + bufImage.getHeight());
		
		int type = bufImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : bufImage.getType();
		BufferedImage resizedImage = new BufferedImage(width, height, type);
		Graphics2D g = resizedImage.createGraphics();
		g.setComposite(AlphaComposite.Src);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(bufImage, 0, 0, width, height, null);
		g.dispose();
		
		
		ImageIO.write(resizedImage, "jpeg", new File("/Users/darren/Temp/testOut.jpg"));
	}
}
