import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;



public class ImagePanel extends JPanel{
	
	private BufferedImage image;
	
	public ImagePanel(){
		try{
			image = ImageIO.read(new File("C:\\Users\\Brandon\\Pictures\\logo-algonquin"));
		}catch (IOException ex){
			
		}
	}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}
}
