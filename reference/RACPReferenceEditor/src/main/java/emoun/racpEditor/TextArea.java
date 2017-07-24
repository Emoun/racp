package emoun.racpEditor;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class TextArea extends JPanel{

	
//Constructs
	public TextArea(){
		setBackground(Color.BLUE);
		BoxLayout layout = new BoxLayout(this,BoxLayout.Y_AXIS);
		setLayout(layout);
		
		add(new TextLine(0));		
	}
	
//Methods
	
	public void display(ArrayList<Byte> text){
		getLine(0).display(text);
	}
	
	public void addDisplaying(ArrayList<Byte> addTo){
		for(int i = 0; i< getComponentCount(); i++){
			TextLine line = (TextLine) getComponent(i);
			
			line.addDisplaying(addTo);
			addTo.add((byte) 10); 
		}
		addTo.remove(addTo.size()-1);
	}
	
	public TextLine getLine(int index){
		return (TextLine) getComponent(index);
	}

	public void defaultFocus(){
		getLine(0).getField(0).focus();
	}
}
