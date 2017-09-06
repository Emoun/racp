package emoun.racpEditor.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

import emoun.racpEditor.CharacterSet;
import emoun.racpEditor.Window;
import static java.awt.event.KeyEvent.*;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class KeyboardListener implements KeyListener, FocusListener{

//Fields
	private Window window;
	
	private HashSet<Integer> pressedKeys = new HashSet<Integer>();
	
	private HashMap<int[], Procedure> keybinds = new HashMap();
	
//Constructors
	
	public KeyboardListener(Window window) {
		this.window = window;
		setKeybinds();
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
		if(CharacterSet.ASCII_TO_RACP_MAPPING.containsKey(e.getKeyChar())){
			window.typeCharacter(CharacterSet.ASCII_TO_RACP_MAPPING.get(e.getKeyChar()));
		}else{
			pressedKeys.add(e.getKeyCode());
			for(Entry<int[], Procedure> entry: keybinds.entrySet()){
				if(pressedKeysAre(entry.getKey())){
					entry.getValue().run();
					return;
				}
			}
			System.out.println("No combination. Has focus: " +  window.hasFocus());
			//if(window.h)
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
//		System.out.println("Key Released: (KeyChar):" + e.getKeyChar() + " (KeyCode):" + e.getKeyCode());
		pressedKeys.remove(e.getKeyCode());
		
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Focus gained.");
	}

	@Override
	public void focusLost(FocusEvent e) {
		//Whenever focus is lost, all pressed keys should be released
		System.out.println("Focus lost.");
		pressedKeys.clear();
	}
	
//Private methods
	
	private boolean pressedKeysAre(int... keys){
		if(keys.length != pressedKeys.size()){
			return false;
		}
		boolean containsAll = true;
		
		for(int k: keys){
			containsAll &= pressedKeys.contains(k);
		}
		return containsAll;
	}
	
	private int[] keys(int...keys){
		return keys;
	}
	
	private void bind(int[] k, Procedure p){
		keybinds.put(k, p);
	}
	
	private void setKeybinds(){
		bind(keys(VK_CONTROL, VK_W),() -> { window.invertVisibleWhitespace(); });
		bind(keys(VK_CONTROL, VK_H),() -> { window.invokeHelp(); });
		bind(keys(VK_CONTROL, VK_N),() -> { window.newDocument(); });
		bind(keys(VK_CONTROL, VK_S),() -> {	window.save();pressedKeys.clear(); });
		bind(keys(VK_CONTROL, VK_L),() -> {	window.load();pressedKeys.clear(); });
		bind(keys(VK_BACK_SPACE),() -> { window.backspace(); });
		bind(keys(VK_DELETE),() -> {window.delete(); });
		bind(keys(VK_CONTROL, VK_ALT, VK_1),() -> {window.typeCharacter(CharacterSet.RACP_HEX_0); });
		bind(keys(VK_CONTROL, VK_ALT, VK_Q),() -> {window.typeCharacter(CharacterSet.RACP_HEX_1); });
		bind(keys(VK_CONTROL, VK_ALT, VK_W),() -> {window.typeCharacter(CharacterSet.RACP_HEX_2); });
		bind(keys(VK_CONTROL, VK_ALT, VK_E),() -> {window.typeCharacter(CharacterSet.RACP_HEX_3); });
		bind(keys(VK_CONTROL, VK_ALT, VK_R),() -> {window.typeCharacter(CharacterSet.RACP_HEX_4); });
		bind(keys(VK_CONTROL, VK_ALT, VK_T),() -> {window.typeCharacter(CharacterSet.RACP_HEX_5); });
		bind(keys(VK_CONTROL, VK_ALT, VK_A),() -> {window.typeCharacter(CharacterSet.RACP_HEX_6); });
		bind(keys(VK_CONTROL, VK_ALT, VK_S),() -> {window.typeCharacter(CharacterSet.RACP_HEX_7); });
		bind(keys(VK_CONTROL, VK_ALT, VK_D),() -> {window.typeCharacter(CharacterSet.RACP_HEX_8); });
		bind(keys(VK_CONTROL, VK_ALT, VK_F),() -> {window.typeCharacter(CharacterSet.RACP_HEX_9); });
		bind(keys(VK_CONTROL, VK_ALT, VK_G),() -> {window.typeCharacter(CharacterSet.RACP_HEX_10); });
		bind(keys(VK_CONTROL, VK_ALT, VK_Z),() -> {window.typeCharacter(CharacterSet.RACP_HEX_11); });
		bind(keys(VK_CONTROL, VK_ALT, VK_X),() -> {window.typeCharacter(CharacterSet.RACP_HEX_12); });
		bind(keys(VK_CONTROL, VK_ALT, VK_C),() -> {window.typeCharacter(CharacterSet.RACP_HEX_13); });
		bind(keys(VK_CONTROL, VK_ALT, VK_V),() -> {window.typeCharacter(CharacterSet.RACP_HEX_14); });
		bind(keys(VK_CONTROL, VK_ALT, VK_B),() -> {window.typeCharacter(CharacterSet.RACP_HEX_15); });
		bind(keys(VK_UP),() -> {window.arrowUp(); });
		bind(keys(VK_DOWN),() -> {window.arrowDown(); });
		bind(keys(VK_RIGHT),() -> {window.arrowRight(); });
		bind(keys(VK_LEFT),() -> {window.arrowLeft(); });
		bind(keys(0),() -> { window.typeCharacter(CharacterSet.RACP_TRUE); });
		bind(keys(VK_SHIFT, 0),() -> { window.typeCharacter(CharacterSet.RACP_FALSE); });
	}	
}

@FunctionalInterface
interface Procedure{
	public void run();
}