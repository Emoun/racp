package emoun.racpEditor.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.HashSet;

import emoun.racpEditor.CharacterSet;
import emoun.racpEditor.Window;
import static java.awt.event.KeyEvent.*;

public class KeyboardListener implements KeyListener{

//Fields
	private Window window;
	
	private HashSet<Integer> pressedKeys = new HashSet<Integer>();
	
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
		System.out.println("Key Pressed: (KeyChar):" + e.getKeyChar() + " (KeyCode):" + e.getKeyCode() + " (Pressed):" + pressedKeys);
		
		// If an ASCII character which is also in RACP is entered, type the associated RACP character.
		System.out.println("Containskey: " + CharacterSet.ASCII_TO_RACP_MAPPING.size());
		if(CharacterSet.ASCII_TO_RACP_MAPPING.containsKey(e.getKeyChar())){
			window.typeCharacter(CharacterSet.ASCII_TO_RACP_MAPPING.get(e.getKeyChar()));
		}else{
			pressedKeys.add(e.getKeyCode());
			if(pressedKeysAre(VK_CONTROL, VK_W)){
				window.invertVisibleWhitespace();
			}else if(pressedKeysAre(VK_CONTROL, VK_H)){
				window.invokeHelp();
			}else if(pressedKeysAre(VK_CONTROL, VK_N)){
				window.newDocument();
			}else if(pressedKeysAre(VK_CONTROL, VK_S)){
				window.save();
				pressedKeys.clear();
			}else if(pressedKeysAre(VK_CONTROL, VK_L)){
				window.load();
				pressedKeys.clear();
			}else if(pressedKeysAre(VK_BACK_SPACE)){
				window.backspace();
			}else if(pressedKeysAre(VK_DELETE)){
				window.delete();
			}else if(pressedKeysAre(VK_CONTROL, VK_ALT, VK_Q)){
				window.typeCharacter(CharacterSet.RACP_HEX_1);
			}else if(pressedKeysAre(VK_CONTROL, VK_ALT, VK_W)){
				window.typeCharacter(CharacterSet.RACP_HEX_2);
			}else if(pressedKeysAre(VK_CONTROL, VK_ALT, VK_E)){
				window.typeCharacter(CharacterSet.RACP_HEX_3);
			}else if(pressedKeysAre(VK_CONTROL, VK_ALT, VK_R)){
				window.typeCharacter(CharacterSet.RACP_HEX_4);
			}else if(pressedKeysAre(VK_CONTROL, VK_ALT, VK_T)){
				window.typeCharacter(CharacterSet.RACP_HEX_5);
			}else if(pressedKeysAre(VK_CONTROL, VK_ALT, VK_A)){
				window.typeCharacter(CharacterSet.RACP_HEX_6);
			}else if(pressedKeysAre(VK_CONTROL, VK_ALT, VK_S)){
				window.typeCharacter(CharacterSet.RACP_HEX_7);
			}else if(pressedKeysAre(VK_CONTROL, VK_ALT, VK_D)){
				window.typeCharacter(CharacterSet.RACP_HEX_8);
			}else if(pressedKeysAre(VK_CONTROL, VK_ALT, VK_F)){
				window.typeCharacter(CharacterSet.RACP_HEX_9);
			}else if(pressedKeysAre(VK_CONTROL, VK_ALT, VK_G)){
				window.typeCharacter(CharacterSet.RACP_HEX_10);
			}else if(pressedKeysAre(VK_CONTROL, VK_ALT, VK_Z)){
				window.typeCharacter(CharacterSet.RACP_HEX_11);
			}else if(pressedKeysAre(VK_CONTROL, VK_ALT, VK_X)){
				window.typeCharacter(CharacterSet.RACP_HEX_12);
			}else if(pressedKeysAre(VK_CONTROL, VK_ALT, VK_C)){
				window.typeCharacter(CharacterSet.RACP_HEX_13);
			}else if(pressedKeysAre(VK_CONTROL, VK_ALT, VK_V)){
				window.typeCharacter(CharacterSet.RACP_HEX_14);
			}else if(pressedKeysAre(VK_CONTROL, VK_ALT, VK_B)){
				window.typeCharacter(CharacterSet.RACP_HEX_15);
			}else if(pressedKeysAre(VK_UP)){
				window.arrowUp();
			}else if(pressedKeysAre(VK_DOWN)){
				window.arrowDown();
			}else if(pressedKeysAre(VK_RIGHT)){
				window.arrowRight();
			}else if(pressedKeysAre(VK_LEFT)){
				window.arrowLeft();
			}else{
				System.out.println("No combination.");
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
		if(keys.length != pressedKeys.size()){
			return false;
		}
		boolean containsAll = true;
		
		for(int k: keys){
			containsAll &= pressedKeys.contains(k);
		}
		return containsAll;
	}
	
}
