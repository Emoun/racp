package emoun.racpEditor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.swing.JFrame;

import org.apache.commons.io.FileUtils;

public class Window extends JFrame{
	
//Fields
	public static final int DEFAULT_COLUMNS = 100;
	public static final int DEFAULT_ROWS = 30;
	
	private String currentFile = "Untitled";
	private boolean changed = false;
	private TextArea textArea;
	private String content;
//Constructors
	
	public Window(){
		textArea = new TextArea(DEFAULT_COLUMNS, DEFAULT_ROWS);
		
		add(textArea);
		pack();
		setVisible(true);
		content = "";
	}
	
	public Window(File f) throws IOException{
		this();
		content = FileUtils.readFileToString(f, StandardCharsets.US_ASCII);
		textArea.display(content, 0);
		
	}
}
