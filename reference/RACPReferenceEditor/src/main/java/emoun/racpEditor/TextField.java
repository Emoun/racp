package emoun.racpEditor;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

import javax.swing.JPanel;

public class TextField  extends JPanel {

//Fields	
	public static final byte DEFAULT_DISPLAY = 32;
	
	private long offset;
	private byte display;
	private boolean focus = false;
	
	
//Constructors
	public TextField(long offset) throws IOException{
		this.offset = offset;
		setBackground(Color.WHITE);
		clear();
	}
	
//Methods
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(!focus){
			g.drawImage(Main.font.glyph[display], 0, 0, null);
		}else{
			g.drawImage(Main.fontInv.glyph[display], 0, 0, null);
		}
	}
	
	public void display(byte c){
		display = c;
	}
	
	public void clear(){
		display = DEFAULT_DISPLAY;
	}
}
