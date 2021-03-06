package emoun.racpEditor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static final RACPReferenceFont font = new RACPReferenceFont();
	public static final RACPReferenceFont fontInv = new RACPReferenceFont();
	public static final RACPReferenceFont fontVisibleWhitespace = new RACPReferenceFont();
	public static final RACPReferenceFont fontVisibleWhitespaceInv = new RACPReferenceFont();
	
	public static boolean selecting = false;
	public static ArrayList<TextField> focusedFields = new ArrayList<TextField>();
	
	public static boolean visibleWhiteSpaceCharacters = true;
	
	public static Window window;
	
	public static void main(String[] args){
		
		try {
			font.loadFont(Main.class.getResourceAsStream("/font/RACP-reference-font-8x16.bmp"));
			fontInv.loadFont(Main.class.getResourceAsStream("/font/RACP-reference-font-8x16-inverted.bmp"));
			fontVisibleWhitespace.loadFont(Main.class.getResourceAsStream("/font/RACP-reference-font-8x16-visible-whitespace.bmp"));
			fontVisibleWhitespaceInv.loadFont(Main.class.getResourceAsStream("/font/RACP-reference-font-8x16-visible-whitespace-inverted.bmp"));
			
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to load fonts", e);
		}
		
		if(args.length == 1){
			File f = new File(args[0]);
			try {
				window = new Window(f);
			} catch (IOException e) {
				throw new IllegalArgumentException("Invalid file: '" + args[0] + "'",e);
			}
		}else{
			window = new Window();
		}
	}

	public static void clearFocusedFields(){
		for(TextField f: focusedFields){
			//Clear each field's focus
			f.unfocus();
		}
		//Remove the fields from the focused list.
		focusedFields.clear();
	}
}
