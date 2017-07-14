package emoun.racpEditor;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JPanel;

public class TextArea extends JPanel{

	
//Constructs
	public TextArea(int columns, int rows){
		setBackground(Color.WHITE);
		setLayout(new GridLayout(rows, 1));
		for(int i = 0; i<rows; i++){
			add(new TextLine(columns,(columns+1)*i));
		}
		
	}
	
	public int display(String text, int offset) throws IOException{
		
		nextLine:
		for(int i = 0; i<getComponentCount(); i++){
			
			TextLine l = (TextLine) getComponent(i);
			l.clear();
			
			while(offset<text.length()){
				byte next = (byte) text.charAt(offset++);
				if(next != 10){
					l.display((byte) next);
				}else{
					continue nextLine;
				}
			}
		}
		return offset;
	}
	
}
