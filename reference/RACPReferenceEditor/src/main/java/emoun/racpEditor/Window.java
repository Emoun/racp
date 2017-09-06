package emoun.racpEditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
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
	private HelpWindow help;
//Constructors
	
	public Window(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		KeyboardListener keyboardListener = new KeyboardListener(this);
		addKeyListener(keyboardListener);
		addFocusListener(keyboardListener);
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
		textArea.defaultFocus();
		updateFocusTracker();
	}
	
	public Window(File f) throws IOException{
		this();
		loadFile(f);
	}
	
//Methods
	public void updateFocusTracker(){
		TextField f = Main.focusedFields.get(0);
		setTitle(""+ f.row() +":"+ f.column() + " | " + (changed? "*": "")  + ((currentFile != null)?currentFile.getAbsolutePath():"Untitled"));
	}
	
	public void typeCharacter(byte c){
		System.out.println("type: " + c);
		
		if(Main.focusedFields.size() == 1){
			TextField f = Main.focusedFields.get(0);
			System.out.println("Field: (" + f.row() +"," + f.column() +")");
			if(c == CharacterSet.RACP_NEWLINE){// newline
				f.splitLine();
			}else{
				f.pushAndDisplay(c);
			}
			Main.clearFocusedFields();
			f.focusNext();
			f.getParentLine().unifyGroup();
			changed = true;
			updateFocusTracker();
		}else if(Main.focusedFields.size() == 0){
			throw new IllegalArgumentException("No focus");
		}else{
			throw new IllegalArgumentException("Not supporting multiple select:" + Main.focusedFields);
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
			changed = true;
			updateFocusTracker();
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
					return;
				}else{
					TextLine prevL = l.getPrevious();
					prevL.getField(prevL.getComponentCount()-1).focus();
					delete();
				}
			}else{
				f.getPrevious().focus();
				delete();
			}
			changed = true;
			updateFocusTracker();
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
	
	public void invokeHelp(){
		System.out.println("Help");
		if(help == null){
			help = new HelpWindow();
		}else{
			help.setVisible(!help.isVisible());
		}
	}

	public void newDocument(){
		System.out.println("New document");
		
		Main.clearFocusedFields();		
		textArea.display(new ArrayList<Byte>());
		textArea.repaint();
		textArea.defaultFocus();
		currentFile = null;
		changed = false;
		updateFocusTracker();
		
	}

	public void save(){
		System.out.println("Save");
		
		if(currentFile == null){
			System.out.println("Save untitled.");
			
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setApproveButtonText("Save");
			fileChooser.setApproveButtonToolTipText("Save in this folder with the given name.");
			int returnVal =fileChooser.showOpenDialog(this);
			
			if( returnVal == JFileChooser.APPROVE_OPTION){
				File selectedFile = fileChooser.getSelectedFile();
				System.out.println("File approved: " + selectedFile);
				
				if(selectedFile.exists()){
					throw new IllegalArgumentException("Overwrite unimplemented.");
				}else{
					forceSave(selectedFile);
					System.out.println("Save successfull.");
				}
			}else{
				System.out.println("File not approved: " + returnVal);
			}
		}else{
			System.out.println("Saving opened file");
			forceSave(currentFile);
		}
	}

	public void load(){
		System.out.println("Save");
		
		JFileChooser fileChooser = new JFileChooser(currentFile);
		fileChooser.setApproveButtonText("Load");
		fileChooser.setApproveButtonToolTipText("Load the chosen file into the editor.");
		int returnVal =fileChooser.showOpenDialog(this);
		
		if( returnVal == JFileChooser.APPROVE_OPTION){
			File selectedFile = fileChooser.getSelectedFile();
			System.out.println("File approved: " + selectedFile);
			
			if(selectedFile.exists()){
				if(selectedFile.canRead()){
					try {
						loadFile(selectedFile);
					} catch (IOException e) {
						throw new IllegalArgumentException("Error loading file.", e);
					}
				}else{
					throw new IllegalArgumentException("Cannot read file");
				}
			}else{
				throw new IllegalArgumentException("File does not exist");
			}
		}else{
			System.out.println("File not approved: " + returnVal);
		}
		
	}
	
	public void arrowUp(){
		System.out.println("Arrow up.");
		
		if(Main.focusedFields.size() == 1){
			TextField f = Main.focusedFields.get(0);
			Main.clearFocusedFields();
			
			if(f.row() == 0){
				//Cannot go further up, therefore focus stays
				f.focus();
			}else{
				int newRow = f.row()-1;
				textArea.getLine(newRow).focusColumn(f.column());
			}
			updateFocusTracker();
		}else if(Main.focusedFields.size() == 0){
			throw new IllegalArgumentException("No focus");
		}else{
			throw new IllegalArgumentException("Not supporting multiple select");
		}
		
	}
	
	public void arrowDown(){
		System.out.println("Arrow down.");
		
		if(Main.focusedFields.size() == 1){
			TextField f = Main.focusedFields.get(0);
			Main.clearFocusedFields();
			
			if(f.row() == (textArea.getComponentCount()-1)){
				//Cannot go further down, therefore focus stays
				f.focus();
			}else{
				int newRow = f.row() + 1;
				textArea.getLine(newRow).focusColumn(f.column());
			}
			updateFocusTracker();
		}else if(Main.focusedFields.size() == 0){
			throw new IllegalArgumentException("No focus");
		}else{
			throw new IllegalArgumentException("Not supporting multiple select");
		}
		
	}

	public void arrowRight(){
		System.out.println("Arrow right.");
		
		if(Main.focusedFields.size() == 1){
			TextField f = Main.focusedFields.get(0);
			Main.clearFocusedFields();
			
			if(f.last()){
				if(f.getParentLine().last()){
					//Cannot go further neither right nor down, therefore focus stays
					f.focus();
				}else{
					//Cannot go further right, therefore go to start of next line
					f.getParentLine().getNext().focusColumn(0);
				}
			}else{
				f.focusNext();
			}
			updateFocusTracker();
		}else if(Main.focusedFields.size() == 0){
			throw new IllegalArgumentException("No focus");
		}else{
			throw new IllegalArgumentException("Not supporting multiple select");
		}
		
	}
	
	public void arrowLeft(){
		System.out.println("Arrow right.");
		
		if(Main.focusedFields.size() == 1){
			TextField f = Main.focusedFields.get(0);
			Main.clearFocusedFields();
			
			if(f.first()){
				if(f.getParentLine().first()){
					//Cannot go further neither left nor up, therefore focus stays
					f.focus();
				}else{
					//Cannot go further left, therefore go to end of previous line
					f.getParentLine().getPrevious().focusLast();
				}
			}else{
				f.getPrevious().focus();
			}
			updateFocusTracker();
		}else if(Main.focusedFields.size() == 0){
			throw new IllegalArgumentException("No focus");
		}else{
			throw new IllegalArgumentException("Not supporting multiple select");
		}
		
	}

//Private methods
	private void forceSave(File selectedFile) {
		try {
			FileOutputStream out = new FileOutputStream(selectedFile);
			try {
				List<Byte> contents = textArea.displaying();
				byte[] result = new byte[contents.size()];
				
				for(int i = 0; i<result.length; i++){
					result[i] = contents.get(i);
				}
				
				out.write(result);
				currentFile = selectedFile; 
				changed = false;
				updateFocusTracker();
			} catch (IOException e) {
				throw new IllegalStateException("Cannot write to file", e);
			}
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				throw new IllegalStateException("Cannot flush or close", e);
			}
		} catch (FileNotFoundException e) {
			throw new IllegalStateException("Not possible", e);
		}
	}
	
	private void loadFile(File f) throws IOException{
		this.currentFile = f;
		List<Byte> content = new ArrayList<Byte>();
		for(byte c: FileUtils.readFileToByteArray(f)){
			content.add(c);
		}
		textArea.display(content);
		Main.clearFocusedFields();
		textArea.defaultFocus();
		textArea.repaint();
		changed = false;
		updateFocusTracker();
	}
}
