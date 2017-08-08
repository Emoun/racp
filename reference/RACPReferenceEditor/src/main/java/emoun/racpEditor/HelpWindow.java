package emoun.racpEditor;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class HelpWindow extends JFrame{
	
//Fields
	String helpText = 
			"<html>"+
			"<h1>RACP Reference Editor</h1>"+
			"<p>This editor is intended to showcase the RACP specification features."+
			"<p>It is <b>not</b> intended to be a fully featured editor and is neither "+
			"fast, efficient, nor user friendly.\n"+
			"<p>Be careful when using this editor, as it never questions your actions. "
			+ "For instance, if you load a file while having made changes to a previously loaded file, "
			+ "the changes will be discarded without notice and the new file will be opened. "
			+ "Closing the editor will likewise discard any changes. Additionally, there is no way to undo changes (popularly ctrl+z) except discarding them."+
			"<table >" +
				"<tr>"+
					"<th style=\"text-align: left\">Keybind</th>" +
					"<th style=\"text-align: left\">Function</th>" +
				"</tr>"+
				"<tr>"+
					"<td>ctrl+h</th>" +
					"<td>Open help windown (as you probably know if you can see this)</th>" +
				"</tr>"+
				"<tr>"+
					"<td>ctrl+n</th>" +
					"<td>Open new file</th>" +
				"</tr>"+
				"<tr>"+
					"<td>ctrl+s</th>" +
					"<td>Save file</th>" +
				"</tr>"+	
				"<tr>"+
					"<td>ctrl+l</th>" +
					"<td>Load file</th>" +
				"</tr>"+
				"<tr>"+
					"<td>ctrl+w</th>" +
					"<td>Show/hide whitespace characters</th>" +
				"</tr>"+
				"<tr>"+
					"<td>ctrl+alt+q</th>" +
					"<td>Type hexadecimal 1</th>" +
				"</tr>"+	
				"<tr>"+
					"<td>ctrl+alt+w</th>" +
					"<td>Type hexadecimal 2</th>" +
				"</tr>"+	
				"<tr>"+
					"<td>ctrl+alt+e</th>" +
					"<td>Type hexadecimal 3</th>" +
				"</tr>"+	
				"<tr>"+
					"<td>ctrl+alt+r</th>" +
					"<td>Type hexadecimal 4</th>" +
				"</tr>"+	
				"<tr>"+
					"<td>ctrl+alt+t</th>" +
					"<td>Type hexadecimal 5</th>" +
				"</tr>"+	
				"<tr>"+
					"<td>ctrl+alt+a</th>" +
					"<td>Type hexadecimal 6</th>" +
				"</tr>"+	
				"<tr>"+
					"<td>ctrl+alt+s</th>" +
					"<td>Type hexadecimal 7</th>" +
				"</tr>"+	
				"<tr>"+
					"<td>ctrl+alt+d</th>" +
					"<td>Type hexadecimal 8</th>" +
				"</tr>"+	
				"<tr>"+
					"<td>ctrl+alt+f</th>" +
					"<td>Type hexadecimal 9</th>" +
				"</tr>"+	
				"<tr>"+
					"<td>ctrl+alt+g</th>" +
					"<td>Type hexadecimal 10</th>" +
				"</tr>"+	
				"<tr>"+
					"<td>ctrl+alt+z</th>" +
					"<td>Type hexadecimal 11</th>" +
				"</tr>"+	
				"<tr>"+
					"<td>ctrl+alt+x</th>" +
					"<td>Type hexadecimal 12</th>" +
				"</tr>"+	
				"<tr>"+
					"<td>ctrl+alt+c</th>" +
					"<td>Type hexadecimal 13</th>" +
				"</tr>"+	
				"<tr>"+
					"<td>ctrl+alt+v</th>" +
					"<td>Type hexadecimal 14</th>" +
				"</tr>"+	
				"<tr>"+
					"<td>ctrl+alt+b</th>" +
					"<td>Type hexadecimal 15</th>" +
				"</tr>"+
			"</table>" +		
			"</html>"
		;
	
//Constructors
	
	public HelpWindow(){
		JTextPane text = new JTextPane();
		setPreferredSize(new Dimension(600,300));
		
		text.setEditable(false);
		text.setContentType("text/html");
		text.setText(helpText);
		text.setCaretPosition(0); //Makes the scroll start at the top.
		
		JScrollPane scroll = new JScrollPane(text);
		add(scroll);
		pack();
		setVisible(true);
		
	}
	
}
