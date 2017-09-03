package emoun.racpEditor;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;


public class TextLine extends LinkedDisplay<TextLine,List<Byte>>{
	

//Fields
	private int rowNr;
	
//Constructors
	
	public TextLine(int rowNr){
		this.rowNr = rowNr;
		setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		setBackground(Color.RED);
		
		add(TextField.newTextField(rowNr));
		
	}
	
//methods
	
	public void focusLast(){
		getField(getComponentCount()-1).focus();
	}
	
	public void addDisplaying(List<Byte> addTo){
		for(int i = 0; i< getComponentCount()-1; i++){
			TextField t = getField(i);
			
			addTo.add(t.displaying());
		}
	}
	
	public void unifyGroupNext(List<TextField> prevTabs){
		List<TextField> thisTabs = tabs();
		
		// If in group with the previous line
		// Align with it.
		if(prevTabs.size() == thisTabs.size()){
			for(int i = 0; i<prevTabs.size(); i++){
				TextField thisF = thisTabs.get(i);
				TextField prevF = prevTabs.get(i);
				thisF.alignWith(prevF);
			}
		}
		
		// Align with all the next lines
		if(!last()){
			getNext().unifyGroupNext(thisTabs);	
			// Realign with the previous, in case any changes were made
			if(prevTabs.size() == thisTabs.size()){
				for(int i = 0; i<prevTabs.size(); i++){
					TextField thisF = thisTabs.get(i);
					TextField prevF = prevTabs.get(i);
					thisF.alignWith(prevF);
				}
			}
		}
		
		revalidate();
		repaint();
	}
	
	public void unifyGroupPrev(List<TextField> nextTabs){
		List<TextField> thisTabs = tabs();
				
		if(nextTabs.size() == thisTabs.size()){
			for(int i = 0; i<nextTabs.size(); i++){
				thisTabs.get(i).alignWith(nextTabs.get(i));
			}
		}
		if(!first()){
			getPrevious().unifyGroupPrev(thisTabs);		
		}
		if(nextTabs.size() == thisTabs.size()){
			for(int i = 0; i<nextTabs.size(); i++){
				thisTabs.get(i).alignWith(nextTabs.get(i));
			}
		}
		revalidate();
		repaint();
	}

	public void unifyGroup(){
		DoublyLinkedPanel.forAll((x) -> x.resetAlignment(), this);
		ArrayList<TextField> empty = new ArrayList<TextField>();
		if(!last()){
			unifyGroupNext(empty);
		}
		if(!first()){
			unifyGroupPrev(empty);
			if(!last()){
				unifyGroupNext(empty);
			}
		}
		revalidate();
		repaint();
	}
	
	public void resetAlignment(){
		for(TextField f: tabs()){
			f.resetAlignment();
		}
	}
//Accessors
	
 	public TextField getField(int index){
		return (TextField) getComponent(index);
	}
	
	public List<TextField> tabs(){
		ArrayList<TextField> result = new ArrayList();
		for(int i = 0; i<getComponentCount(); i++){
			TextField f = getField(i);
			if(f.displayingTab()){
				result.add(f);
			}
		}
		return result;
	}

	@Override
	public void display(List<Byte> text){
		int fieldCur = 0;
		while(text.size() > 0){
			byte next = text.remove(0);
			//If newline
			if(next == CharacterSet.RACP_NEWLINE){
				if(last()){
					TextLine newLine = new TextLine(rowNr+1);
					DoublyLinkedPanel.link(this, newLine);
					getParent().add(newLine);
				}
				getNext().display(text);
				break;
			}else{
				getField(fieldCur).display(next);
				fieldCur++;
			}
		}
		getField(fieldCur).makeLast();
		unifyGroup();
	}
	
	@Override
	public List<Byte> displaying() {
		ArrayList<Byte> result = new ArrayList<Byte>();
		
		for(int i = 0; i < getComponentCount()-1; i++){
			TextField f = getField(i);
			
			result.add(f.displaying());
		}
		return result;
	}

	@Override
	public TextLine newDisplay() {
		return new TextLine(rowNr+1);
	}
	
	public void focusColumn(int column){
		int upto = getField(getComponentCount()-1).nextColumn();
		if(column < 0){
			throw new IllegalArgumentException("Negative column.");
		}else if(column >= upto){
			focusLast();
			return;
		}
		
		for(int i = 0; i<getComponentCount(); i++){
			TextField f = getField(i);
			
			if(f.column()<= column && column < f.nextColumn()){
				f.focus();
				return;
			}
		}
		throw new IllegalStateException("No field found to focus: '" + column + "'");
	}
	
//Private methods
	
}
