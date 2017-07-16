package emoun.racpEditor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class RACPReferenceFont {

//Fields
	public static final int WIDTH = 8;
	public static final int HEIGHT = 16;
	
	public static final int X_OFFSET = 28;
	public static final int Y_OFFSET = 12;
	public static final int X_BUFFER = 10;
	public static final int COLUMNS = 16;
	public static final int ROWS = 8;
	public static final int COLUMN_MULT = 1;
	public static final int ROW_MULT = 16;
	
	
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
