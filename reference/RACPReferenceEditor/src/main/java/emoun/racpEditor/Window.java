package emoun.racpEditor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.JFrame;

import org.apache.commons.io.FileUtils;

import emoun.racpEditor.listeners.KeyboardListener;

public class Window extends JFrame{
	
//Fields
	public static final int DEFAULT_COLUMNS = 100;
	public static final int DEFAULT_ROWS = 30;
	
	public File currentFile ;
	private boolean changed = false;
	private TextArea textArea;
	private ArrayList<Byte> content;
	private int displayStart , displayEnd;
//Constructors
	
	public Window(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(new KeyboardListener(this));
		setFocusTraversalKeysEnabled(false); //Enables tab events
		textArea = new TextArea(DEFAULT_COLUMNS, DEFAULT_ROWS);
		add(textArea);
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
		displayStart = 0;
		displayEnd = textArea.display(content, displayStart);
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
			textArea.insertCharAt(f.row(), f.column(), c);
			Main.clearFocusedFields();
			
			f.focusNext();
			updateFocusTracker();
		}else if(Main.focusedFields.size() == 0){
			throw new IllegalArgumentException("No focus");
		}else{
			throw new IllegalArgumentException("Not supporting multiple select");
		}
		textArea.repaint();
	}
	
	public void invertVisibleWhitespace(){
		System.out.println("Invert");
		Main.visibleWhiteSpaceCharacters = !Main.visibleWhiteSpaceCharacters;
		textArea.repaint();
	}
}
