package emoun.racpEditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.apache.commons.io.FileUtils;

import emoun.racpEditor.listeners.KeyboardListener;

public class Window extends JFrame{
	
//Fields
	public File currentFile ;
	private boolean changed = false;
	private TextArea textArea;
	private ArrayList<Byte> content;
//Constructors
	
	public Window(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(new KeyboardListener(this));
		setFocusTraversalKeysEnabled(false); //Enables tab clicks to be sent o the keyboard listener
		
		setPreferredSize(new Dimension(900,500));
		
		textArea = new TextArea();
		
		
		JPanel resizer = new JPanel();
		resizer.setBackground(Color.GREEN);
		resizer.setLayout(new FlowLayout(FlowLayout.LEFT));
		resizer.add(textArea);
		
		JScrollPane scroller = new JScrollPane(resizer);
		scroller.setBackground(Color.PINK);
		scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		add(scroller);
		pack();
		setVisible(true);
		content = new ArrayList<Byte>();
		textArea.defaultFocus();
		updateFocusTracker();
	}
	
	public Window(File f) throws IOException{
		this();
		this.currentFile = f;
		for(byte c: FileUtils.readFileToByteArray(f)){
			content.add(c);
		}
		textArea.display(content);
		textArea.repaint();
		updateFocusTracker();
	}
	
//Methods
	public void updateFocusTracker(){
		TextField f = Main.focusedFields.get(0);
		setTitle(""+ f.row() +":"+ f.column() + " | " + ((currentFile != null)?currentFile.getAbsolutePath():"Untitled"));
	}
	
	public void typeCharacter(byte c){
//		System.out.println("type: " + c);
		
		if(Main.focusedFields.size() == 1){
			TextField f = Main.focusedFields.get(0);
			if(c == 10){// newline
				f.splitLine();
			}else{
				f.pushAndDisplay(c);
			}
			Main.clearFocusedFields();
			f.focusNext();
			f.getParentLine().unifyGroup();
			updateFocusTracker();
		}else if(Main.focusedFields.size() == 0){
			throw new IllegalArgumentException("No focus");
		}else{
			throw new IllegalArgumentException("Not supporting multiple select");
		}
	}
	
	public void delete(){
		System.out.println("Enter delete");
		
		if(Main.focusedFields.size() == 1){
			TextField f = Main.focusedFields.get(0);
			TextLine l = f.getParentLine();
			Main.clearFocusedFields();
			if(!f.last()){
				f.pullDisplayAndClearLast();
			}else{
				if(l.last()){
				}else{
					TextLine nextL = l.getNext();
					List<Byte> disp = l.displaying();
					disp.addAll(nextL.displaying());
					l.display(disp);
					nextL.pullDisplayAndClearLast();
				}
			}
			f.focus();
			l.unifyGroup();
		}else if(Main.focusedFields.size() == 0){
			throw new IllegalArgumentException("No focus");
		}else{
			throw new IllegalArgumentException("Not supporting multiple select");
		}
	}
	
	public void backspace(){
		System.out.println("Enter backspace");
		
		if(Main.focusedFields.size() == 1){
			TextField f = Main.focusedFields.get(0);
			Main.clearFocusedFields();
			if(f.first()){
				TextLine l = f.getParentLine();
				if(l.first()){
					f.focus();
				}else{
					TextLine prevL = l.getPrevious();
					prevL.getField(prevL.getComponentCount()-1).focus();
					delete();
				}
			}else{
				f.getPrevious().focus();
				delete();
			}
		}else if(Main.focusedFields.size() == 0){
			throw new IllegalArgumentException("No focus");
		}else{
			throw new IllegalArgumentException("Not supporting multiple select");
		}
	}
	
	public void invertVisibleWhitespace(){
		System.out.println("Invert whitespaces");
		Main.visibleWhiteSpaceCharacters = !Main.visibleWhiteSpaceCharacters;
		textArea.repaint();
	}

}
