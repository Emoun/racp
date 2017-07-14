package emoun.racpEditor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class RACPReferenceFont {

//Fields
	public static int WIDTH = 8;
	public static int HEIGHT = 16;
	
	private static int X_OFFSET = 28;
	private static int Y_OFFSET = 12;
	private static int X_BUFFER = 10;
	private static int COLUMNS = 16;
	private static int ROWS = 8;
	private static int COLUMN_MULT = 1;
	private static int ROW_MULT = 16;
	
	
	public BufferedImage[] glyph = new BufferedImage[128];
	
//Constructors
	public RACPReferenceFont(){}
	
//Methods
	
	public void loadFont(File referenceImage) throws IOException{
		BufferedImage image = ImageIO.read(referenceImage);
		
		for(int y = 0; y < ROWS; y++){
			for(int x = 0; x < COLUMNS; x++){
				
				glyph[(COLUMN_MULT*x) + (ROW_MULT*y)] = image.getSubimage(X_OFFSET + (x*(WIDTH+X_BUFFER)), Y_OFFSET + (y*HEIGHT), WIDTH, HEIGHT);
				
			}
		}
		
		
	}
	
}
