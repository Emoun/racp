package emoun.racpEditor;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class TextArea extends JPanel{

	
//Constructs
	public TextArea(int columns, int rows){
		setBackground(Color.WHITE);
		BoxLayout layout = new BoxLayout(this,BoxLayout.Y_AXIS);
		setLayout(layout);
		
		DoublyLinkedPanel.createList(rows, i -> new TextLine(columns,i) , 
				(i, l) -> add(l));
		
	}
	
//Methods
	
	public int display(ArrayList<Byte> text, int offset) throws IOException{
		
		nextLine:
		for(int i = 0; i<getComponentCount(); i++){
			
			TextLine l = (TextLine) getComponent(i);
			l.clear();
			
			while(offset<text.size()){
				byte next = text.get(offset++);
				if(next != 10){
					l.display(next);
				}else{
					continue nextLine;
				}
			}
		}
		getLine(0).unifyGroups();
		return offset;
	}
	
	public void addDisplaying(ArrayList<Byte> addTo){
		for(int i = 0; i< getComponentCount(); i++){
			TextLine line = (TextLine) getComponent(i);
			
			line.addDisplaying(addTo);
			addTo.add((byte) 10); 
		}
		addTo.remove(addTo.size()-1);
	}

	public void insertCharAt(int row, int column, byte c){
		if(row < 0 || row >= getComponentCount()){
			throw new IllegalArgumentException("Trying to insert char '"+c+"' at line '" + row + "' but there are only '"+getComponentCount()+"'");
		}
		
		getLine(row).insertCharAt(column, c);
		getLine(0).unifyGroups();
	}
	
	public TextLine getLine(int index){
		return (TextLine) getComponent(index);
	}

	public void defaultFocus(){
		((TextField)getLine(0).getComponent(0)).focus();
	}
}
