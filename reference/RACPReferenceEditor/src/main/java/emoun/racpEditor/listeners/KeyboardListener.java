package emoun.racpEditor.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.HashSet;

import emoun.racpEditor.Window;

import static java.awt.event.KeyEvent.*;

public class KeyboardListener implements KeyListener{

//Fields
	private Window window;
	
	private HashSet<Integer> pressedKeys = new HashSet<Integer>();
	
	public static HashSet<Byte> pureKeys = new HashSet<Byte>();
	{
		
		pureKeys.add((byte)'\t');
		pureKeys.add((byte)'\n');
		pureKeys.add((byte)VK_ESCAPE);
		pureKeys.add((byte)' ');
		pureKeys.add((byte)'!');
		pureKeys.add((byte)'"');
		pureKeys.add((byte)'#');
		pureKeys.add((byte)'$');
		pureKeys.add((byte)'%');
		pureKeys.add((byte)'&');
		pureKeys.add((byte)'\'');
		pureKeys.add((byte)'(');
		pureKeys.add((byte)')');
		pureKeys.add((byte)'*');
		pureKeys.add((byte)'+');
		pureKeys.add((byte)',');
		pureKeys.add((byte)'-');
		pureKeys.add((byte)'.');
		pureKeys.add((byte)'/');
		pureKeys.add((byte)'0');
		pureKeys.add((byte)'1');
		pureKeys.add((byte)'2');
		pureKeys.add((byte)'3');
		pureKeys.add((byte)'4');
		pureKeys.add((byte)'5');
		pureKeys.add((byte)'6');
		pureKeys.add((byte)'7');
		pureKeys.add((byte)'8');
		pureKeys.add((byte)'9');
		pureKeys.add((byte)':');
		pureKeys.add((byte)';');
		pureKeys.add((byte)'<');
		pureKeys.add((byte)'=');
		pureKeys.add((byte)'>');
		pureKeys.add((byte)'?');
		pureKeys.add((byte)'@');
		pureKeys.add((byte)'A');
		pureKeys.add((byte)'B');
		pureKeys.add((byte)'C');
		pureKeys.add((byte)'D');
		pureKeys.add((byte)'E');
		pureKeys.add((byte)'F');
		pureKeys.add((byte)'G');
		pureKeys.add((byte)'H');
		pureKeys.add((byte)'I');
		pureKeys.add((byte)'J');
		pureKeys.add((byte)'K');
		pureKeys.add((byte)'L');
		pureKeys.add((byte)'M');
		pureKeys.add((byte)'N');
		pureKeys.add((byte)'O');
		pureKeys.add((byte)'P');
		pureKeys.add((byte)'Q');
		pureKeys.add((byte)'R');
		pureKeys.add((byte)'S');
		pureKeys.add((byte)'T');
		pureKeys.add((byte)'U');
		pureKeys.add((byte)'V');
		pureKeys.add((byte)'X');
		pureKeys.add((byte)'W');
		pureKeys.add((byte)'Y');
		pureKeys.add((byte)'Z');
		pureKeys.add((byte)'[');
		pureKeys.add((byte)'\\');
		pureKeys.add((byte)']');
		pureKeys.add((byte)'^');
		pureKeys.add((byte)'_');
		pureKeys.add((byte)'a');
		pureKeys.add((byte)'b');
		pureKeys.add((byte)'c');
		pureKeys.add((byte)'d');
		pureKeys.add((byte)'e');
		pureKeys.add((byte)'f');
		pureKeys.add((byte)'g');
		pureKeys.add((byte)'h');
		pureKeys.add((byte)'i');
		pureKeys.add((byte)'j');
		pureKeys.add((byte)'k');
		pureKeys.add((byte)'l');
		pureKeys.add((byte)'m');
		pureKeys.add((byte)'n');
		pureKeys.add((byte)'o');
		pureKeys.add((byte)'p');
		pureKeys.add((byte)'q');
		pureKeys.add((byte)'r');
		pureKeys.add((byte)'s');
		pureKeys.add((byte)'t');
		pureKeys.add((byte)'u');
		pureKeys.add((byte)'v');
		pureKeys.add((byte)'x');
		pureKeys.add((byte)'w');
		pureKeys.add((byte)'y');
		pureKeys.add((byte)'z');
		pureKeys.add((byte)'{');
		pureKeys.add((byte)'|');
		pureKeys.add((byte)'}');
		pureKeys.add((byte)'~');
		
	}
//Constructors
	
	public KeyboardListener(Window window) {
		this.window = window;
	}
	
//Overriding methods
	
	@Override
	public void keyTyped(KeyEvent e) {
//		System.out.println("Typed: (KeyChar):" + e.getKeyChar() + " (KeyCode):" + e.getKeyCode());
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("Key Pressed: (KeyChar):" + e.getKeyChar() + " (KeyCode):" + e.getKeyCode());
		
		// If usual character is entered, then just print it
		if(pureKeys.contains((byte)e.getKeyChar())){
			window.typeCharacter((byte) e.getKeyChar());
		}else{
			pressedKeys.add(e.getKeyCode());
			if(pressedKeysAre(VK_CONTROL, VK_W)){
				window.invertVisibleWhitespace();
			}else if(pressedKeysAre(VK_BACK_SPACE)){
				window.backspace();
			}else if(pressedKeysAre(VK_DELETE)){
				window.delete();
			}else{
//				System.out.println("Contains: " + pressedKeys);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
//		System.out.println("Key Released: (KeyChar):" + e.getKeyChar() + " (KeyCode):" + e.getKeyCode());
		pressedKeys.remove(e.getKeyCode());
	}

//Private methods
	
	public boolean pressedKeysAre(int... keys){
		boolean containsAll = true;
		
		for(int k: keys){
			containsAll &= pressedKeys.contains(k);
		}
		return containsAll;
	}
	
}
