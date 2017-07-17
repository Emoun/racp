# Revised ASCII Codes for Programming Specification
**version** 0.0.2

## Introduction

This document describes the complete specification for the **R**evised **A**SCII **C**odes for **P**rogramming character encoding.
This is an 8-bit encoding with 128 valid characters ranging the values of 0 - 127. The most significant bit is reserved for future specification of a multi-byte encoding, compatible with RACP, which handles all other characters in the world (basically, a new Unicode compatible with RACP).

This encoding is based on the ASCII standard but seeks to modernise it to make better use of the limited encoding space. The encoding __does not__ seek to be compatible with either ASCII nor Unicode but will take inspiration from them where there is no reason not to.

## Features, compared to ASCII

- Control character cleanup: Only contains four control characters; Null, Tab, New-Line, and Escape. Puts the old control characters slots to good use with new characters useful in the modern programming world.
- Only one unambiguous New-Line character (ASCII 'Line Feed').
- Dedicated Escape character for use within a string when programming.
- Tab now has a specification, no more dependence on the editor to tab correctly.
- Three new bracket/paranthesis sets: 2 sets with dedicated use cases and 1 for generic use. No more using '<' and '>' for brackets.
- Additional numeral systems:
	- Booleans: Dedicated TRUE/FALSE number characters. No more using '0b' to distinguish from decimal.
	- Hexadecimal: Dedicated hexadecimal number characters. No more using '0x' to distinguish from decimal.
- All your favourite characters are right where you left them! Any character in RACP also found in ASCII is exactly the same place.

## Specification

### Column description:

- **Dec**: The decimal encoding of the character.
- **Hex**: The hexadecimal encoding of the character.
- **Bin**: The binary encoding of the character.
- **Symbol**: Unique symbol representing the character.
- **ASCII**: A set of ASCII characters that map to a RACP character. These are designed to allow for characters to be written in ASCII and then converted to RACP using an automated tool, and vice versa.
- **Graphics**: Proposed graphical representation of the character on-screen.
- **Description**: Short description. See next section for more information on specific topics.

Dec | Hex | Bin | Symbol | ASCII | Graphics | Description
:---:|:---:|:----:|:---:|:---:|:---:|:------
000 | 00 | 0000 0000 | NULL | \0 |  | Null character
001 | 01 | 0000 0001 | 	<% | 	\\{	 | 	  | Script start
002 | 02 | 0000 0010 | 	<: | 	\\[	 | 	  | Formatting start
003 | 03 | 0000 0011 | 	:> | 	\\]	 | 	  | Formatting end or formatting until next newLine
004 | 04 | 0000 0100 | 	%> | 	\\}	 | 	  | Script end or script until next newLine
005 | 05 | 0000 0101 | 	FALSE |	\F	 |    | Boolean value false
006 | 06 | 0000 0110 | 	TRUE | 	\T	 | 	  | Boolean value true
007 | 07 | 0000 0111 | 	PIL | 	\p	 | ¶	  | Pilcrow
008 | 08 | 0000 1000 | 	RPIL | 	\q	 | ⁋	  | Reversed Pilcrow
009 | 09 | 0000 1001 | 	TAB | 	\t	 | 	  | Tab
010 | 0A | 0000 1010 | 	NL | 	\n	 | U+2424 | New Line
011 | 0B | 0000 1011 | 	0x1 | 	\1	 | ١	  | Hexadecimal number one
012 | 0C | 0000 1100 | 	0x2 | 	\2	 | ٢	  | Hexadecimal number two
013 | 0D | 0000 1101 | 	0x3 | 	\3	 | ٣	  | Hexadecimal number three
014 | 0E | 0000 1110 | 	0x4 | 	\4	 | ٤	  | Hexadecimal number four
015 | 0F | 0000 1111 | 	0x5 |	\5	 | ٥	  | Hexadecimal number five
016 | 10 | 0001 0000 | 	0x6 | 	\6	 | ٦	  | Hexadecimal number six
017 | 11 | 0001 0001 | 	0x7 | 	\7	 | ٧	  | Hexadecimal number seven
018 | 12 | 0001 0010 | 	0x8 | 	\8	 | ٨	  | Hexadecimal number eight
019 | 13 | 0001 0011 | 	0x9 | 	\9	 | ٩	  | Hexadecimal number nine
020 | 14 | 0001 0100 | 	0xA | 	\a	 | < ٠ with ٠ on top	  | Hexadecimal number ten
021 | 15 | 0001 0101 | 	0xB | 	\b	 | < ١ with ٠ on top>	  | Hexadecimal number eleven
022 | 16 | 0001 0110 | 	0xC | 	\c	 | < ٢ with ٠ on top>	  | Hexadecimal number twelve
023 | 17 | 0001 0111 | 	0xD | 	\d	 | < ٣ with ٠ on top>	  | Hexadecimal number thirteen
024 | 18 | 0001 1000 | 	0xE | 	\e	 | < ٤ with ٠ on top>	  | Hexadecimal number fourteen
025 | 19 | 0001 1001 | 	0xF | 	\f	 | < ٥ with ٠ on top>	  | Hexadecimal number fifteen
026 | 1A | 0001 1010 | 	CUR | 	\C	 | ¤	  | Currency sign
027 | 1B | 0001 1011 | 	ESC | 	\E	 | ␛	  | Escape the next character; if the character has special meaning, dont interpret the meaning just print its symbol
028 | 1C | 0001 1100 | 	<&#124; | 	\(	 | ⟨	  | Angle bracket left
029 | 1D | 0001 1101 | 	&#124;> | 	\)	 | ⟩	  | Angle bracket right
030 | 1E | 0001 1110 | 	LDAQ | 	\<	 | «	 | Left-pointing Double Angle Quote
031 | 1F | 0001 1111 | 	RDAQ | 	\>	 | »	 | Rgiht-pointing Double Angle Quaote
032 | 20 | 0010 0000 | 	SP | 	  	 | ␠	  | space
033 | 21 | 0010 0001 | 	! | 	!	 | !	  | exclamation mark
034 | 22 | 0010 0010 | 	" | 	"	 | "	  | Quotation mark
035 | 23 | 0010 0011 | 	# | 	#	 | #	  | Number sign
036 | 24 | 0010 0100 | 	$ | 	$	 | $	  | Dollar sign
037 | 25 | 0010 0101 | 	% | 	%	 | %	  | Percent sign
038 | 26 | 0010 0110 | 	& | 	&	 | &	  | Ampersand
039 | 27 | 0010 0111 | 	' | 	'	 | '	  | Apostrophe
040 | 28 | 0010 1000 | 	( | 	(	 | (	  | round brackets or parentheses
041 | 29 | 0010 1001 | 	) | 	)	 | )	  | round brackets or parentheses
042 | 2A | 0010 1010 | 	* | 	*	 | *	  | Asterisk
043 | 2B | 0010 1011 | 	+ | 	+	 | +	  | Plus sign
044 | 2C | 0010 1100 | 	, | 	,	 | ,	  | Comma
045 | 2D | 0010 1101 | 	- | 	-	 | -	  | Hyphen
046 | 2E | 0010 1110 | 	. | 	.	 | .	  | Full stop , dot
047 | 2F | 0010 1111 | 	/ | 	/	 | /	  | Slash, Forward Slash
048 | 30 | 0011 0000 | 	0 | 	0	 | 0	  | Decimal number zero
049 | 31 | 0011 0001 | 	1 | 	1	 | 1	  | Decimal number one
050 | 32 | 0011 0010 | 	2 | 	2	 | 2	  | Decimal number two
051 | 33 | 0011 0011 | 	3 | 	3	 | 3	  | Decimal number three
052 | 34 | 0011 0100 | 	4 | 	4	 | 4	  | Decimal number four
053 | 35 | 0011 0101 | 	5 | 	5	 | 5	  | Decimal number five
054 | 36 | 0011 0110 | 	6 | 	6	 | 6	  | Decimal number six
055 | 37 | 0011 0111 | 	7 | 	7	 | 7	  | Decimal number seven
056 | 38 | 0011 1000 | 	8 | 	8	 | 8	  | Decimal number eight
057 | 39 | 0011 1001 | 	9 | 	9	 | 9	  | Decimal number nine
058 | 3A | 0011 1010 | 	: | 	:	 | :	  | Colon
059 | 3B | 0011 1011 | 	; | 	;	 | ;	  | Semicolon
060 | 3C | 0011 1100 | 	< | 	<	 | <	  | Less-than sign; left guillemet 
061 | 3D | 0011 1101 | 	= | 	=	 | =	  | Equals sign
062 | 3E | 0011 1110 | 	> | 	>	 | >	  | Greater-than sign ; Inequality; right guillemet 
063 | 3F | 0011 1111 | 	? | 	?	 | ?	  | Question mark
064 | 40 | 0100 0000 | 	@ | 	@	 | @	  | At sign
065 | 41 | 0100 0001 | 	A | 	A	 | A	  | Capital A 
066 | 42 | 0100 0010 | 	B | 	B	 | B	  | Capital B 
067 | 43 | 0100 0011 | 	C | 	C	 | C	  | Capital C 
068 | 44 | 0100 0100 | 	D | 	D	 | D	  | Capital D 
069 | 45 | 0100 0101 | 	E | 	E	 | E	  | Capital E 
070 | 46 | 0100 0110 | 	F | 	F	 | F	  | Capital F 
071 | 47 | 0100 0111 | 	G | 	G	 | G	  | Capital G 
072 | 48 | 0100 1000 | 	H | 	H	 | H	  | Capital H 
073 | 49 | 0100 1001 | 	I | 	I	 | I	  | Capital I 
074 | 4A | 0100 1010 | 	J | 	J	 | J	  | Capital J 
075 | 4B | 0100 1011 | 	K | 	K	 | K	  | Capital K 
076 | 4C | 0100 1100 | 	L | 	L	 | L	  | Capital L 
077 | 4D | 0100 1101 | 	M | 	M	 | M	  | Capital M 
078 | 4E | 0100 1110 | 	N | 	N	 | N	  | Capital N 
079 | 4F | 0100 1111 | 	O | 	O	 | O	  | Capital O 
080 | 50 | 0101 0000 | 	P | 	P	 | P	  | Capital P 
081 | 51 | 0101 0001 | 	Q | 	Q	 | Q	  | Capital Q 
082 | 52 | 0101 0010 | 	R | 	R	 | R	  | Capital R 
083 | 53 | 0101 0011 | 	S | 	S	 | S	  | Capital S 
084 | 54 | 0101 0100 | 	T | 	T	 | T	  | Capital T 
085 | 55 | 0101 0101 | 	U | 	U	 | U	  | Capital U 
086 | 56 | 0101 0110 | 	V | 	V	 | V	  | Capital V 
087 | 57 | 0101 0111 | 	W | 	W	 | W	  | Capital W 
088 | 58 | 0101 1000 | 	X | 	X	 | X	  | Capital X 
089 | 59 | 0101 1001 | 	Y | 	Y	 | Y	  | Capital Y 
090 | 5A | 0101 1010 | 	Z | 	Z	 | Z	  | Capital Z 
091 | 5B | 0101 1011 | 	[ | 	[	 | [	  | square brackets or box brackets
092 | 5C | 0101 1100 | 	\ | 	\\	 | \	  | Backslash
093 | 5D | 0101 1101 | 	] | 	]	 | ]	  | square brackets or box brackets
094 | 5E | 0101 1110 | 	^ | 	^	 | ^	  | Caret or circumflex accent
095 | 5F | 0101 1111 | 	_ | 	_	 | _	  | underscore , understrike , underbar or low line
096 | 60 | 0110 0000 | 	SEC | 	\s	 | §	  | Section Sign
097 | 61 | 0110 0001 | 	a | 	a	 | a	  | Lowercase  a 
098 | 62 | 0110 0010 | 	b | 	b	 | b	  | Lowercase  b 
099 | 63 | 0110 0011 | 	c | 	c	 | c	  | Lowercase  c 
100 | 64 | 0110 0100 | 	d | 	d	 | d	  | Lowercase  d 
101 | 65 | 0110 0101 | 	e | 	e	 | e	  | Lowercase  e 
102 | 66 | 0110 0110 | 	f | 	f	 | f	  | Lowercase  f 
103 | 67 | 0110 0111 | 	g | 	g	 | g	  | Lowercase  g 
104 | 68 | 0110 1000 | 	h | 	h	 | h	  | Lowercase  h 
105 | 69 | 0110 1001 | 	i | 	i	 | i	  | Lowercase  i 
106 | 6A | 0110 1010 | 	j | 	j	 | j	  | Lowercase  j 
107 | 6B | 0110 1011 | 	k | 	k	 | k	  | Lowercase  k 
108 | 6C | 0110 1100 | 	l | 	l	 | l	  | Lowercase  l 
109 | 6D | 0110 1101 | 	m | 	m	 | m	  | Lowercase  m 
110 | 6E | 0110 1110 | 	n | 	n	 | n	  | Lowercase  n 
111 | 6F | 0110 1111 | 	o | 	o	 | o	  | Lowercase  o 
112 | 70 | 0111 0000 | 	p | 	p	 | p	  | Lowercase  p 
113 | 71 | 0111 0001 | 	q | 	q	 | q	  | Lowercase  q 
114 | 72 | 0111 0010 | 	r | 	r	 | r	  | Lowercase  r 
115 | 73 | 0111 0011 | 	s | 	s	 | s	  | Lowercase  s 
116 | 74 | 0111 0100 | 	t | 	t	 | t	  | Lowercase  t 
117 | 75 | 0111 0101 | 	u | 	u	 | u	  | Lowercase  u 
118 | 76 | 0111 0110 | 	v | 	v	 | v	  | Lowercase  v 
119 | 77 | 0111 0111 | 	w | 	w	 | w	  | Lowercase  w 
120 | 78 | 0111 1000 | 	x | 	x	 | x	  | Lowercase  x 
121 | 79 | 0111 1001 | 	y | 	y	 | y	  | Lowercase  y 
122 | 7A | 0111 1010 | 	z | 	z	 | z	  | Lowercase  z 
123 | 7B | 0111 1011 | 	{ | 	{	 | {	  | curly brackets or braces
124 | 7C | 0111 1100 | 	&#124; | 	&#124;	 | &#124;	  | vertical-bar, vbar, vertical line or vertical slash
125 | 7D | 0111 1101 | 	} | 	}	 | }	  | curly brackets or braces
126 | 7E | 0111 1110 | 	~ | 	~	 | ~	  | Tilde ; swing dash
127 | 7F | 0111 1111 |	DIV | 	\\~	 | ÷	  | Division Sign

### Tabbing

Using the tab character enables the creation of  simple formatting in a string of characters.
By default a tab is defined as being displayed as a blank and takes up a set number of character display slots. This standard does not specify the number, except to require any specific display of a string to use the same number for all tabs in the same string.

If a line of characters contains a set of tabs, and the next line in the string contains the exact same number of tabs, the tabs must be aligned in such a way that each tab in the second line is directly below the corresponding tab in the previous line. This requirement is stronger than the requirement of all tabs needing to take up the same character display slots. Therefore, if a tab needs to take up more slots to be alligned with its predecessor then it must do so. If the characters preceding a tab are so numerous that the tab is aligned further to the right than its predecessor, even though it takes up only one space, the predecessor must be realigned to match the tab.
These allignment requirements are chained. This means if three consequtive lines have the exact same number of tabs, all the lines must align their tabs to match each other, and so on for more lines.

### Script

An implicit instruction language that must be interpreted and run at this point in the reading of the text.

A script segment is started by the \\{ (ASCII) character and is bounded by the \\} character. Any character in between these two is part of that script segment. The language of the script is undefined by this specification and is implicitly known by any reader of the character stream.

The script segment is executed by the entity reading the character stream. The segment should specify intructions for reader to do at that given moment of the character stream reading, be it state change or generation of additional character.

### Formatting

An implicit instruction language that must be interpreted and run at this point in the reading of the text.

A formatting segment is started by the \\[ (ASCII) character and is bounded by the \\] character.Any characterin between these two is part of that script segment. The language of the script is undefined by this specification and is implicitly known by any reader of the character stream.

The formatting segment tells the current reader of the character stream, how to continue reading the character stream.































