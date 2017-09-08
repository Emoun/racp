package emoun.racpEditor;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class TextArea extends JPanel{

	
//Constructs
	public TextArea(){
		setBackground(Color.WHITE);
		BoxLayout layout = new BoxLayout(this,BoxLayout.Y_AXIS);
		setLayout(layout);
		
		clear();	
	}
	
//Methods
	
	public void clear(){
		removeAll();
		add(new TextLine(0));	
		revalidate();
		repaint();
	}
	
	public void display(List<Byte> text){
		clear();
		getLine(0).display(text);
	}
	
	public void addDisplaying(List<Byte> addTo){
		for(int i = 0; i< getComponentCount(); i++){
			TextLine line = (TextLine) getComponent(i);
			
			line.addDisplaying(addTo);
			addTo.add(CharacterSet.RACP_NEWLINE); 
		}
		addTo.remove(addTo.size()-1);
	}
	
	public TextLine getLine(int index){
		return (TextLine) getComponent(index);
	}

	public List<Byte> displaying(){
		List<Byte> result = new ArrayList<Byte>();
				
		assert getComponentCount() > 0;
		
		for(int i = 0; i<getComponentCount()-1; i++){
			result.addAll(getLine(i).displaying());
			result.add(CharacterSet.RACP_NEWLINE);
		}
		
		result.addAll(getLine(getComponentCount()-1).displaying());
		
		return result;
	}
	
	public void defaultFocus(){
		getLine(0).getField(0).focus();
	}
}
