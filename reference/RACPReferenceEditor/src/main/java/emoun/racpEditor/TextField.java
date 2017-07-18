package emoun.racpEditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class TextField  extends DoublyLinkedPanel<TextField>{

//Fields	
	public static final byte DEFAULT_DISPLAY = 32;
	
	private int column;
	private int row;
	
	private byte display;
	private boolean active = false;
	private boolean focus = false;
	private boolean partOfTab = false;
	
//Constructors
	public TextField(int column, int row){
		this.column = column;
		this.row = row;
		setBackground(Color.WHITE);
		Dimension dimension = new Dimension(RACPReferenceFont.WIDTH, RACPReferenceFont.HEIGHT);
		setPreferredSize(dimension);
		setMaximumSize(dimension);
		setMinimumSize(dimension);
		
		clear();
	}
	
//Methods
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(!focus){
			if(Main.visibleWhiteSpaceCharacters && (partOfTab()?(getNext() == null) || !getNext().partOfTab():active() && (display != 9))){
				g.drawImage(Main.fontVisibleWhitespace.glyph[display], 0, 0, null);
			}else{
				g.drawImage(Main.font.glyph[display], 0, 0, null);
			}
		}else{
			if(Main.visibleWhiteSpaceCharacters && (partOfTab()?(getNext() == null) || !getNext().partOfTab():active() && (display != 9))){
				g.drawImage(Main.fontVisibleWhitespaceInv.glyph[display], 0, 0, null);
			}else{
				g.drawImage(Main.fontInv.glyph[display], 0, 0, null);
			}
		}
	}
	
	public void display(byte c){
		display = c;
		active = true;
		partOfTab = false;
		repaint();
	}
	
	public void clear(){
		display = DEFAULT_DISPLAY;
		active = false;
		partOfTab = false;
		repaint();
	}
	
	
	
	public void focus(){
		if(active){
			setFocused();
		}else{
			if (getPrevious() != null){
				if((getPrevious().active() || getPrevious().partOfTab()) && !partOfTab()){
					setFocused();
				}else{
					getPrevious().focus();
				}
			}else{
				TextLine prevLine = ((TextLine) getParent()).getPrevious();
				if(prevLine != null){
					prevLine.focusLast();
				}else{
					//First field, and file is empty
					setFocused();
				}
			}
		}
	}

	private void setFocused() {
		this.focus = true;
		Main.focusedFields.add(this);
		repaint();
	}
	
	public void unfocus(){
		this.focus = false;
		repaint();
	}
	
	public void displayAndPass(byte c){
		if(getNext() != null){
			if(active){
				getNext().displayAndPass(display);
			}else if(partOfTab()){
				getNext().tabifyAndPass();
			}
		}
		display(c);
	}

	public void tabify(){
		this.partOfTab = true;
		this.display = 9;
		repaint();
	}
	
	public void tabifyAndPass(){
		if(getNext() != null){
			if(partOfTab()){
				getNext().tabifyAndPass();
			}else if(active){
				getNext().displayAndPass(display);
			}
		}
		clear();
		tabify();
	}

	public void focusNext(){
		if(!getNext().partOfTab()){
			getNext().focus();
		}else{
			getNext().focusNext();
		}
	}
	
	public byte clearAndPull(){
		byte previousValue = display;
		clear();
		if(getNext() != null){
			if(getNext().active()){
				display(getNext().clearAndPull());
			}else if(getNext().partOfTab()){
				getNext().clearAndPull();
				tabify();
			}
		}
		return previousValue;
	}
//Accessors
	public int column(){
		return column;
	}
	
	public int row(){
		return row;
	}
	
	public boolean active(){
		return this.active;
	}
	
	public byte displaying(){
		return this.display;
	}
	
	public boolean partOfTab(){
		return this.partOfTab;
	}
}
