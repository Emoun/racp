package emoun.racpEditor;

import java.io.File;
import java.io.IOException;

public class Main {

	public static final RACPReferenceFont font = new RACPReferenceFont();
	public static final RACPReferenceFont fontInv = new RACPReferenceFont();
	
	public static void main(String[] args){
		
		try {
			font.loadFont(new File("src/resources/font/RACP-reference-font-8x16.bmp"));
			fontInv.loadFont(new File("src/resources/font/RACP-reference-font-8x16-inverted.bmp"));
			
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to load font", e);
		}
		
		if(args.length == 1){
			File f = new File(args[0]);
			try {
				new Window(f);
			} catch (IOException e) {
				throw new IllegalArgumentException("Invalid file: '" + args[0] + "'",e);
			}
		}else{
			new Window();
		}
		
	}
	
}
