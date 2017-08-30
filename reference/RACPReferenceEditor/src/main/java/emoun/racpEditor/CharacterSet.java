package emoun.racpEditor;

import java.util.HashMap;

public class CharacterSet {
	

//Fields

	public static final byte RACP_FALSE = 0;
	public static final byte RACP_TRUE = 1;
	public static final byte RACP_SPACE = 2;
	public static final byte RACP_TAB = 3;
	public static final byte RACP_NEWLINE = 4;
	public static final byte RACP_SLASH = 6;
	public static final byte RACP_BACKSLASH = 7;
	public static final byte RACP_LPAREN = 16;
	public static final byte RACP_RPAREN = 17;
	public static final byte RACP_LBRACKET = 18;
	public static final byte RACP_RBRACKET = 19;
	public static final byte RACP_LBRACE = 20;
	public static final byte RACP_RBRACE = 21;
	public static final byte RACP_LT = 24;
	public static final byte RACP_GT = 25;
	public static final byte RACP_EQUAL = 26;
	public static final byte RACP_AMPERSAND = 28;
	public static final byte RACP_VBAR = 29;
	public static final byte RACP_CARET = 30;
	public static final byte RACP_DOLLAR = 31;
	public static final byte RACP_HEX_0 = 32;
	public static final byte RACP_HEX_1 = 33;
	public static final byte RACP_HEX_2 = 34;
	public static final byte RACP_HEX_3 = 35;
	public static final byte RACP_HEX_4 = 36;
	public static final byte RACP_HEX_5 = 37;
	public static final byte RACP_HEX_6 = 38;
	public static final byte RACP_HEX_7 = 39;
	public static final byte RACP_HEX_8 = 40;
	public static final byte RACP_HEX_9 = 41;
	public static final byte RACP_HEX_10 = 42;
	public static final byte RACP_HEX_11 = 43;
	public static final byte RACP_HEX_12 = 44;
	public static final byte RACP_HEX_13 = 45;
	public static final byte RACP_HEX_14 = 46;
	public static final byte RACP_HEX_15 = 57;
	public static final byte RACP_PERIOD = 60;
	public static final byte RACP_COMMA = 61;
	public static final byte RACP_EXCLAMATION	= 62;
	public static final byte RACP_QUESTION = 63;
	public static final byte RACP_PLUS = 91;
	public static final byte RACP_DASH = 92;
	public static final byte RACP_PERCENT = 95;
	public static final byte RACP_ASTERISK = 93;
	public static final byte RACP_QUOTE = 123;
	public static final byte RACP_APOSTROPHE = 124;
	public static final byte RACP_UNDERSCORE = 125;
	public static final byte RACP_TILDE = 126;
	public static final byte RACP_HASH = 127;
	
	public static final byte ASCII_TAB = 9;
	public static final byte ASCII_NEWLINE = 10;
	public static final byte ASCII_SPACE = 32;
	public static final byte ASCII_LT = 60;
	
	
	public static final HashMap<Character, Byte> ASCII_TO_RACP_MAPPING = new HashMap<Character, Byte>();
	static {
		ASCII_TO_RACP_MAPPING.put('\t', RACP_TAB);
		ASCII_TO_RACP_MAPPING.put((char)ASCII_NEWLINE, RACP_NEWLINE);		
		ASCII_TO_RACP_MAPPING.put(' ', RACP_SPACE);
		ASCII_TO_RACP_MAPPING.put('!', RACP_EXCLAMATION);
		ASCII_TO_RACP_MAPPING.put('"', RACP_QUOTE);
		ASCII_TO_RACP_MAPPING.put('#', RACP_HASH);
		ASCII_TO_RACP_MAPPING.put('$', RACP_DOLLAR);
		ASCII_TO_RACP_MAPPING.put('%', RACP_PERCENT);
		ASCII_TO_RACP_MAPPING.put('&', RACP_AMPERSAND);
		ASCII_TO_RACP_MAPPING.put('\'', RACP_APOSTROPHE);
		ASCII_TO_RACP_MAPPING.put('(', RACP_LPAREN);
		ASCII_TO_RACP_MAPPING.put(')', RACP_RPAREN);
		ASCII_TO_RACP_MAPPING.put('*', RACP_ASTERISK);
		ASCII_TO_RACP_MAPPING.put('+', RACP_PLUS);
		ASCII_TO_RACP_MAPPING.put(',', RACP_COMMA);
		ASCII_TO_RACP_MAPPING.put('-', RACP_DASH);
		ASCII_TO_RACP_MAPPING.put('.', RACP_PERIOD);
		ASCII_TO_RACP_MAPPING.put('/', RACP_SLASH);
		for(byte i = ((byte)'0'); i<((byte)'<'); i++){
			ASCII_TO_RACP_MAPPING.put((char)i, i);
		}
		ASCII_TO_RACP_MAPPING.put('<', RACP_LT);
		ASCII_TO_RACP_MAPPING.put('=', RACP_EQUAL);
		ASCII_TO_RACP_MAPPING.put('>', RACP_GT);
		ASCII_TO_RACP_MAPPING.put('?', RACP_QUESTION);
		for(byte i = ((byte)'@'); i<((byte)'['); i++){
			ASCII_TO_RACP_MAPPING.put((char)i, i);
		}
		ASCII_TO_RACP_MAPPING.put('[', RACP_LBRACKET);
		ASCII_TO_RACP_MAPPING.put('\\', RACP_BACKSLASH);
		ASCII_TO_RACP_MAPPING.put(']', RACP_RBRACKET);
		ASCII_TO_RACP_MAPPING.put('^', RACP_CARET);
		ASCII_TO_RACP_MAPPING.put('_', RACP_UNDERSCORE);
		for(byte i = ((byte)'a'); i<((byte)'{'); i++){
			ASCII_TO_RACP_MAPPING.put((char)i, i);
		}
		ASCII_TO_RACP_MAPPING.put('{', RACP_LBRACE);
		ASCII_TO_RACP_MAPPING.put('|', RACP_VBAR);
		ASCII_TO_RACP_MAPPING.put('}', RACP_RBRACE);
		ASCII_TO_RACP_MAPPING.put('~', RACP_TILDE);
	}
	
}