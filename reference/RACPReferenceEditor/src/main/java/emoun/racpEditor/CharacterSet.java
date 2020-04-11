package emoun.racpEditor;

import java.util.HashMap;

public class CharacterSet {
	

//Fields

	public static final byte RACP_FALSE = 0;
	public static final byte RACP_TRUE = 64;
	public static final byte RACP_SPACE = 100;
	public static final byte RACP_TAB = 101;
	public static final byte RACP_NEWLINE = 102;
	public static final byte RACP_ESCAPE = 31;
	public static final byte RACP_SLASH = 27;
	public static final byte RACP_BACKSLASH = 91;
	public static final byte RACP_RPILCROW = 28;
	public static final byte RACP_PILCROW = 92;
	public static final byte RACP_LDAQ = 29;
	public static final byte RACP_RDAQ = 93;
	public static final byte RACP_LLATH = 58;
	public static final byte RACP_RLATH = 122;
	public static final byte RACP_LJAMB = 59;
	public static final byte RACP_RJAMB = 123;
	public static final byte RACP_LPAREN = 60;
	public static final byte RACP_RPAREN = 124;
	public static final byte RACP_LBRACKET = 61;
	public static final byte RACP_RBRACKET = 125;
	public static final byte RACP_LBRACE = 62;
	public static final byte RACP_RBRACE = 126;
	public static final byte RACP_LA_BRACKET = 63;
	public static final byte RACP_RA_BRACKET = 127;
	public static final byte RACP_LT = 30;
	public static final byte RACP_GT = 94;
	public static final byte RACP_EQUAL = 95;
	public static final byte RACP_NOT = 96;
	public static final byte RACP_AMPERSAND = 97;
	public static final byte RACP_VBAR = 98;
	public static final byte RACP_CARET = 99;
	public static final byte RACP_DOLLAR = 121;
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
	public static final byte RACP_HEX_15 = 47;
	public static final byte RACP_PERIOD = 105;
	public static final byte RACP_COMMA = 106;
	public static final byte RACP_EXCLAMATION	= 107;
	public static final byte RACP_QUESTION = 108;
	public static final byte RACP_PLUS = 114;
	public static final byte RACP_DASH = 115;
	public static final byte RACP_ASTERISK = 111;
	public static final byte RACP_DIVISION = 112;
	public static final byte RACP_PERCENT = 113;
	public static final byte RACP_SECTION = 119;
	public static final byte RACP_QUOTE = 109;
	public static final byte RACP_APOSTROPHE = 110;
	public static final byte RACP_UNDERSCORE = 116;
	public static final byte RACP_TILDE = 117;
	public static final byte RACP_HASH = 120;
	public static final byte RACP_A = 1;
	public static final byte RACP_a = 65;
	public static final byte RACP_AT = 118;
	public static final byte RACP_COLON = 103;
	public static final byte RACP_SEMICOLON = 104;
		
	public static final HashMap<Character, Byte> ASCII_TO_RACP_MAPPING = new HashMap<Character, Byte>();
	static {
		ASCII_TO_RACP_MAPPING.put('\t', RACP_TAB);
		ASCII_TO_RACP_MAPPING.put('\n', RACP_NEWLINE);		
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
		
		mapToSelf(ASCII_TO_RACP_MAPPING, '0', ':');
		
		ASCII_TO_RACP_MAPPING.put(':', RACP_COLON);
		ASCII_TO_RACP_MAPPING.put(';', RACP_SEMICOLON);		
		ASCII_TO_RACP_MAPPING.put('<', RACP_LT);
		ASCII_TO_RACP_MAPPING.put('=', RACP_EQUAL);
		ASCII_TO_RACP_MAPPING.put('>', RACP_GT);
		ASCII_TO_RACP_MAPPING.put('?', RACP_QUESTION);
		ASCII_TO_RACP_MAPPING.put('@', RACP_AT);
		
		mapSequence(ASCII_TO_RACP_MAPPING, 'A', RACP_A, 26);
		
		ASCII_TO_RACP_MAPPING.put('[', RACP_LBRACKET);
		ASCII_TO_RACP_MAPPING.put('\\', RACP_BACKSLASH);
		ASCII_TO_RACP_MAPPING.put(']', RACP_RBRACKET);
		ASCII_TO_RACP_MAPPING.put('^', RACP_CARET);
		ASCII_TO_RACP_MAPPING.put('_', RACP_UNDERSCORE);
		
		mapSequence(ASCII_TO_RACP_MAPPING, 'a', RACP_a, 26);
		
		ASCII_TO_RACP_MAPPING.put('{', RACP_LBRACE);
		ASCII_TO_RACP_MAPPING.put('|', RACP_VBAR);
		ASCII_TO_RACP_MAPPING.put('}', RACP_RBRACE);
		ASCII_TO_RACP_MAPPING.put('~', RACP_TILDE);
	}
	
	/**
	 * Maps the given characters to themselves in the given map.
	 * @param map
	 * @param from inclusive
	 * @param to exclusive
	 */
	public static void mapToSelf(HashMap<Character, Byte> map, char from, char to) {
		for(byte i = ((byte) from); i<((byte) to); i++){
			map.put((char)i, i);
		}
	}
	
	/**
	 * Maps one sequence of characters to another.
	 * @param map
	 * @param fromStart Start of the sequence to map
	 * @param toStart Start of the sequence to map to
	 * @param length The number of characters to map
	 */
	public static void mapSequence(HashMap<Character, Byte> map, char fromStart, byte toStart, int length) {
		for(byte i = 0; i<length; i++){
			map.put((char)(((byte)fromStart) + i), (byte) (toStart + i));
		}
	}
}