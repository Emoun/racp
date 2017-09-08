package emoun.racpEditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import emoun.racpEditor.listeners.TextFieldMouseListener;

public class TextField  extends LinkedDisplay<TextField,Byte>{

//Fields	
	public static final byte DEFAULT_DISPLAY = CharacterSet.RACP_SPACE;
	/**
	 * The minimum size of the body of a tabstop. Must be 0 or higher.
	 * The body of a tabstop contains all non-tab characters between two tabsstops.
	 */
	public static final int DEFAULT_TABSTOP_MIN_BODY = 2;
	/**
	 * The minimum size of the padding after the body of a tabstop. Must be 0 or higher.
	 * The padding is the empty space after the last character that is between two tabstops.
	 */
	public static final int DEFAULT_TABSTOP_MIN_PADDING = 2;
	
	private int row;
	
	private byte display;
	private boolean focus = false;
	
	/**
	 * The size the tab should be
	 */
	private int alignment;
	
//Constructors
	public TextField(int row){
		this.row = row;
		setBackground(Color.WHITE);
		
		display = DEFAULT_DISPLAY;
		resetAlignment();
	}
	
//Methods
	
	public void setAlignment(int x){
		alignment = x;
		Dimension d = new Dimension((RACPReferenceFont.WIDTH)*x, RACPReferenceFont.HEIGHT);
		setPreferredSize(d);
		setMaximumSize(d);
		setMinimumSize(d);
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		if(!focus){
			if(Main.visibleWhiteSpaceCharacters){
				if(last()){
					if(getParentLine().last()){
						g.drawImage(Main.font.glyph[DEFAULT_DISPLAY], 0, 0, null);
					}else{
						g.drawImage(Main.fontVisibleWhitespace.glyph[CharacterSet.RACP_NEWLINE], 0, 0, null);
					}
				}else if(displayingTab()){
					for(int i = 0; i<alignment-1; i++){
						g.drawImage(Main.font.glyph[DEFAULT_DISPLAY], RACPReferenceFont.WIDTH*i, 0, null);
					}
					g.drawImage(Main.fontVisibleWhitespace.glyph[CharacterSet.RACP_TAB], RACPReferenceFont.WIDTH*(alignment-1), 0, null);
				}else{
					g.drawImage(Main.fontVisibleWhitespace.glyph[displaying()], 0, 0, null);
				}
			}else{
				for(int i = 0; i<alignment; i++){
					g.drawImage(Main.font.glyph[DEFAULT_DISPLAY], RACPReferenceFont.WIDTH*i, 0, null);
				}
				g.drawImage(Main.font.glyph[displaying()], 0, 0, null);
			}
		}else{
			if(Main.visibleWhiteSpaceCharacters){
				if(last()){
					if(getParentLine().last()){
						g.drawImage(Main.fontInv.glyph[DEFAULT_DISPLAY], 0, 0, null);
					}else{
						g.drawImage(Main.fontVisibleWhitespaceInv.glyph[CharacterSet.RACP_NEWLINE], 0, 0, null);
					}
				}else if(displayingTab()){
					g.drawImage(Main.fontInv.glyph[DEFAULT_DISPLAY], 0, 0, null);
					for(int i = 1; i<alignment-1; i++){
						g.drawImage(Main.font.glyph[DEFAULT_DISPLAY], RACPReferenceFont.WIDTH*i, 0, null);
					}
					g.drawImage(Main.fontVisibleWhitespace.glyph[CharacterSet.RACP_TAB], RACPReferenceFont.WIDTH*(alignment-1), 0, null);
				}else{
					g.drawImage(Main.fontVisibleWhitespaceInv.glyph[displaying()], 0, 0, null);
				}
			}else{
				g.drawImage(Main.fontInv.glyph[DEFAULT_DISPLAY], 0, 0, null);
				for(int i = 1; i<alignment; i++){
					g.drawImage(Main.font.glyph[DEFAULT_DISPLAY], RACPReferenceFont.WIDTH*i, 0, null);
				}
				g.drawImage(Main.fontInv.glyph[displaying()], 0, 0, null);
			}
		}
	}
	
	public void splitLine(){
		List<Byte> displayingNext = displayingNext();
		System.out.println(displayingNext);
		makeLast();
		
		if(getParentLine().last()){
			TextLine newLine = new TextLine(this.row()+1);
			DoublyLinkedPanel.link(getParentLine(), newLine);
			getParentLine().getParent().add(newLine);
			newLine.display(displayingNext);
		}else{
			getParentLine().getNext().pushAndDisplay(displayingNext);
		}
	}
	
	@Override
	public void display(Byte c) {
		if(last()){
			TextField newField = newDisplay();
			DoublyLinkedPanel.link(this, newField);
			getParentLine().add(newField);
		}
		display = c;
		resetAlignment();
	}
	
	@Override
	public void pushAndDisplay(Byte c){
		if(last()){
			TextField newDis = newDisplay();
			DoublyLinkedPanel.link(this, newDis);
			getParent().add(newDis);
			display(c);
		}else{
			super.pushAndDisplay(c);
		}
	}
	
	@Override
	public Byte pullDisplayAndClearLast() {
		if(!last() && getNext().last()){
			//Save displaying before next is removed.
			Byte old = displaying();
			getNext().pullDisplayAndClearLast();
			makeLast();
			return old;
		}else{
			return super.pullDisplayAndClearLast();
		}
	};
	
	@Override
	public TextField newDisplay() {
		TextField f = new TextField(row());
		TextFieldMouseListener listener = new TextFieldMouseListener(f);
		f.addMouseListener(listener);
		return f;
	}
	
	@Override
	public Byte displaying(){
		if(last()){
			display = DEFAULT_DISPLAY;
			setAlignment(1);
		}
		return this.display;
	}
	
	public List<Byte> displayingNext(){
		List<Byte> result = new ArrayList<Byte>();
		TextField f = this;
		System.out.println("(" + row + ", " + column() + ") -> " + f.getNext());
		while(!f.last()){
			result.add(f.displaying());
			f = f.getNext();
		}
		return result;
	}
	
	public void makeLast(){
		if(!last()){
			getNext().makeLast();
			getNext().clear();
		}
		display = DEFAULT_DISPLAY;
		resetAlignment();
	}
	
	public void focus(){
//		System.out.println("Focus: " + this);
		this.focus = true;
		Main.focusedFields.add(this);
		repaint();
	}
	
	public void unfocus(){
//		System.out.println("Unfocus: " + this);
		this.focus = false;
		repaint();
	}
	
	public void focusNext(){
		if(last()){
			getParentLine().getNext().getField(0).focus();
		}else{
			getNext().focus();
		}
	}
		
	public TextLine getParentLine(){
		return (TextLine) getParent();
	}
	
	/**
	 * Aligns the fields by enlarging one so that they both end at the same column
	 * @param other
	 * @return
	 */
	public void alignWith(TextField other){
		
		if(!this.displayingTab()){
			throw new IllegalArgumentException("This field is not displaying tab.");
		}
		if(!other.displayingTab()){
			throw new IllegalArgumentException("Other field is not displaying tab.");
		}
		
		
		int thisCol = nextColumn();
		int otherCol = other.nextColumn();
				
		if(thisCol > otherCol){
			other.setAlignment(other.alignment + (thisCol - otherCol));
		}else if(thisCol < otherCol){
			setAlignment(this.alignment + (otherCol - thisCol));
		}
	}
	
	public void resetAlignment(){
		int alignment;
		if(displayingTab()) {
			if(first()) {
				alignment = DEFAULT_TABSTOP_MIN_BODY;
			}else {
				TextField f = this;
				int i  = 0;
				for(; i<DEFAULT_TABSTOP_MIN_BODY; i++) {
					f = f.getPrevious();
					if(f.displayingTab()) {
						break;
					}
					if(f.first()){
						i++;
						break;
					}
				}
				alignment = DEFAULT_TABSTOP_MIN_BODY - i;
			}
			alignment += DEFAULT_TABSTOP_MIN_PADDING;
		}else {
			alignment = 1;
		}
		
		setAlignment(alignment);
	}
	
	@Override
	public String toString(){
		return "TextField(" + row() + ","+column()+")["+displaying()+","+(displayingTab()?"tabbed":"") + "," + (last()?"last":"") + "," + (focus?"focus":"") +"]";
	}
//Accessors
	
	/**
	 * The first column occupied by this field.
	 * @return
	 */
	public int column(){
		if(getPrevious() == null){
			return 0;
		}else{
			return getPrevious().nextColumn();
		}
	}
	
	/**
	 * The first column after this field and not occupied by it.
	 * @return
	 */
	public int nextColumn(){
		int next = 0;
		
		if(!first()){
			next = getPrevious().nextColumn() ;
		}
		return next + alignment;
	}
	
	public int row(){
		return row;
	}
			
	public boolean displayingTab(){
		return !last()? displaying() == CharacterSet.RACP_TAB: false;
	}

	
//Static methods
	
	public static TextField newTextField(int row){
		return (new TextField(row)).newDisplay();
	}
}
