package emoun.racpEditor.listeners;

import java.awt.event.MouseListener;

import emoun.racpEditor.Main;
import emoun.racpEditor.TextField;

import java.awt.event.MouseEvent;

public class TextFieldMouseListener implements MouseListener{

//Fields
	private TextField field;
//Constructors
	
	public TextFieldMouseListener(TextField field) {
		this.field = field;
	}
	
//Overriding methods
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Main.clearFocusedFields();
		field.focus();
		Main.window.updateFocusTracker();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
//Methods

}
