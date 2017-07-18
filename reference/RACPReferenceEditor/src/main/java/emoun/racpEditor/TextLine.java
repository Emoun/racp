package emoun.racpEditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import emoun.racpEditor.listeners.TextFieldMouseListener;


public class TextLine extends DoublyLinkedPanel<TextLine>{
	

//Fields
	private int rowNr;
	private int cursor;
	
	private LineFormat currentFormat, minimumFormat;
	
//Constructors
	
	public TextLine(int size, int rowNr){
		this.rowNr = rowNr;
		setLayout(new FlowLayout(FlowLayout.LEFT,1,1));
		setBackground(Color.WHITE);
		
		DoublyLinkedPanel.createList(size, 
			i ->  new TextField(i, rowNr), 
			(i, f) -> {
				TextFieldMouseListener listener = new TextFieldMouseListener(f);
				f.addMouseListener(listener);
				TextLine.this.add(f);
			});
		Dimension dimension = new Dimension(RACPReferenceFont.WIDTH*size, RACPReferenceFont.HEIGHT);
		setPreferredSize(dimension);
		setMaximumSize(dimension);
		setMinimumSize(dimension);
		clear();
	}
	
//methods
	
	public void clear(){
		for(int i = 0; i<getComponentCount(); i++){
			((TextField)getComponent(i)).clear();
		}
		this.cursor = 0;
		currentFormat = new LineFormat();
		minimumFormat = new LineFormat();
	}
	
	public boolean display(byte c){
		if( cursor >= getComponentCount()){
			return false;
		}else{
			((TextField)getComponent(cursor)).display(c);
			cursor++;
			assignTabs(c, cursor-1);	
			return true;
		}
	}
	
	public void focusLast(){
		((TextField)getComponent(getComponentCount()-1)).focus();
	}
	
	public void addDisplaying(ArrayList<Byte> addTo){
		for(int i = 0; i< getComponentCount(); i++){
			TextField t = (TextField) getComponent(i);
			
			if(t.active()){
				addTo.add(t.displaying());
			}else{
				break;
			}
		}
	}
	
	public void insertCharAt(int index, byte c){
		if(index < 0 || index >= getComponentCount()){
			throw new IllegalArgumentException("Trying to insert char '"+c+"' at ("+rowNr+","+index+") which is out of bounds: ("+rowNr+","+getComponentCount()+")");
		}
		
		getField(index).displayAndPass(c);
		cursor++;
		assignTabs(c, index);
	}
	
	public void unifyGroups(){
		unifyGroups(minimumFormat);
	}
	
	public LineFormat unifyGroups(LineFormat currentMinimum){
//		System.out.print("Line '"+ rowNr + "' init: c" + currentFormat.getTabTo()+", m"+minimumFormat.getTabTo());
//		System.out.println(", a" + currentMinimum.getTabTo());
		
		LineFormat acceptedFormat = LineFormat.groupFormat(minimumFormat, currentMinimum);;
		
//		System.out.print("Line '"+ rowNr + "' joinParent: c" + currentFormat.getTabTo()+", m"+minimumFormat.getTabTo());
//		System.out.println(" a" + currentMinimum.getTabTo());
		
		if(getNext() != null){
			acceptedFormat = LineFormat.groupFormat(acceptedFormat, getNext().unifyGroups(acceptedFormat));
		}
				
//		System.out.print("Line '"+ rowNr + "' joinChild: c" + currentFormat.getTabTo()+", m"+minimumFormat.getTabTo());
//		System.out.println(" a" + acceptedFormat.getTabTo());
		
		//Contract format to minimum
		for(int i = minimumFormat.getTabTo().size()-1; i>=0; i--){
			Integer minTabToI = minimumFormat.getTabTo().get(i);
//			System.out.println("clear " + i + ", current "+ currentFormat.getTabTo().get(i) +", min " + minTabToI);
			for(int j = currentFormat.getTabTo().get(i); j> minTabToI; j--){
				if(j < getComponentCount()){
					getField(j-1).clearAndPull();
//					System.out.println("clearing " + (j-1) + ", became " + getField(j-1).displaying());
					for(int k = i; k<currentFormat.getTabTo().size(); k++){
						int old = currentFormat.getTabTo().get(k);
						currentFormat = currentFormat.replace(k, old-1);
					}
				}
			}
		}
		
		//Extends format to accepted
		for(int i = 0; i<acceptedFormat.getTabTo().size();i++){
			Integer accTabToI = acceptedFormat.getTabTo().get(i);
//			System.out.println("Tab " + i + ", current "+ currentFormat.getTabTo().get(i) +", accepted " + accTabToI);
			for(int j = currentFormat.getTabTo().get(i); j< accTabToI; j++){
				if(j < getComponentCount()){
					getField(j).tabifyAndPass();
//					System.out.println("Tabifying " + j + ", became " + getField(j).displaying());
					for(int k = i; k<currentFormat.getTabTo().size(); k++){
						int old = currentFormat.getTabTo().get(k);
						currentFormat = currentFormat.replace(k, old+1);
					}
				}
			}
		}
//		System.out.print("Line '"+ rowNr + "' format: c" + currentFormat.getTabTo()+", m"+minimumFormat.getTabTo());
//		System.out.println(" a" + acceptedFormat.getTabTo());
		currentFormat = acceptedFormat;
		return acceptedFormat;
	}
//Accessors
	
	public TextField getField(int index){
		return (TextField) getComponent(index);
	}

//Private methods
	
	private void assignTabs(byte c, int column) {
//		System.out.println("Line before '"+ rowNr + "' init: c" + currentFormat.getTabTo()+", m"+minimumFormat.getTabTo());
		if(currentFormat.getTabTo().size() == 0 && c != 9){
			return;
		}
		//Find the tab after the insertion point (column) and its index
		int currentTabColumn = column;
		int minimumTabColumn = column;
		int j = 0;
		for(; j< currentFormat.getTabTo().size(); j++){
			currentTabColumn = currentFormat.getTabTo().get(j);
			minimumTabColumn = minimumFormat.getTabTo().get(j);
			if(column < currentTabColumn){
				break;
			}
		}
		
		for(int i = j; i<currentFormat.getTabTo().size(); i++){
			Integer cR = currentFormat.getTabTo().get(i);
//			System.out.println("tabbing tabTo current " + cR);
			currentFormat = currentFormat.replace(i, cR+((c==9)?4:1));

			Integer mR = minimumFormat.getTabTo().get(i);
//			System.out.println("tabbing tabTo min " + mR);
			minimumFormat = minimumFormat.replace(i, mR+((c==9)?4:1));
		}
		
		if(c == 9){
//			System.out.println("Line after '"+ rowNr + "' init: c" + currentFormat.getTabTo()+", m"+minimumFormat.getTabTo());
			currentFormat = currentFormat.add(j, column+4);
			//	If the column is after the last tabTo
			if(j == currentFormat.getTabTo().size()-1){
				minimumFormat = minimumFormat.add(j, minimumTabColumn+(column-currentTabColumn)+4);
			}else{
				minimumFormat = minimumFormat.add(j, minimumTabColumn-(minimumTabColumn-column)+4);
			}
			
			column++;
			for(int i = 1; i<4 && column<getComponentCount(); i++){
				getField(column).tabifyAndPass();
				column++;
				cursor++;
			}
		}
		
	}
}
