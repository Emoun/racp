package emoun.racpEditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JPanel;


public class TextLine extends JPanel{
	

//Fields
	private int offset;
	private int cursor;
	
	
//Constructors
	
	public TextLine(int size, int offset){
		this.offset = offset;
		setLayout(new GridLayout(1, size));
		setBackground(Color.WHITE);
		Dimension dimension = new Dimension(size*(RACPReferenceFont.WIDTH+1), RACPReferenceFont.HEIGHT);
		setPreferredSize(dimension);
		setMaximumSize(dimension);
		setMinimumSize(dimension);
		try {
			for(int i = 0; i<size; i++){
				add(new TextField(offset + i));
			}
			repaint();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//methods
	
	public void clear(){
		for(int i = 0; i<getComponentCount(); i++){
			((TextField)getComponent(i)).clear();
		}
		this.cursor = 0;
	}
	
	public boolean display(byte c){
		if( cursor == getComponentCount()){
			return false;
		}else{
			((TextField)getComponent(cursor)).display(c);
			cursor++;
			return true;
		}
	}
	
}
