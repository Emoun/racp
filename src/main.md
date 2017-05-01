# Revised ASCII Codes for Programming Specification
**version** 0.0.1

## Introduction

This document describes the complete specification for the **R**evised **A**SCII **C**odes for **P**rogramming character encoding.
This is an 8-bit encoding with 128 valid characters ranging the values of 0 - 127. The most significant bit is reserved for future specification of a multi-byte encoding compatible with RACP.

This encoding is based on the ASCII standard but seeks to modernise it to make better use of the limitted encoding space. The encoding __does not__ seek to be compatible with either ASCII nor Unicode but will take inspiration from them where there is no reason not to.

## Features, compared to ASCII

- Control character cleanup: Only contains four control characters; Null, Tab, New-Line, and Escape. Put the old control characters slots to good use with new characters useful in the modern programming world.
- Only a single New-Line character (ASCII 'Line Feed').
- Dedicated Escape character for use within a string when programming.
- Tab now has a specification, no more dependence on the editor to tab correctly. (TODO)
- Three new bracket/paranthesis sets: 2 sets with dedicated use cases and 1 for generic use. No more using '<' and '>' for brackets.
- Additional numeral systems:
	- Booleans: Dedicated TRUE/FALSE characters. No more using '0b' to distinguish from decimal.
	- Hexadecimal: Dedicated hexadecimal characters. No more using '0x' to distinguish from decimal.
- All your favourite characters are right where you left them! Any character in RACP also found in ASCII is exactly the same place.

## Specification

Dec | Hex | Bin | Symbol | ASCII | Graphics | Description
:---:|:---:|:---:|:---:|:---:|:---:|:------
000 | 00 | 0000 0000 | NULL | \0 | ␀ | Null character
001 | 01 | 0000 0000 | 	<% | 	\\{	 | 	  | Script start
002 | 02 | 0000 0000 | 	<: | 	\\[	 | 	  | Formatting start
003 | 03 | 0000 0000 | 	:> | 	\\]	 | 	  | Formatting end or formatting until next newLine
004 | 04 | 0000 0000 | 	%> | 	\\}	 | 	  | Script end or script until next newLine
005 | 05 | 0000 0000 | 	FALSE |	\F	 |    | Boolean value false
006 | 06 | 0000 0000 | 	TRUE | 	\T	 | 	  | Boolean value true
007 | 07 | 0000 0000 | 	<%> | 	\%	 | 	  | Script divider; If outside of script, then script mark
008 | 08 | 0000 0000 | 	<:> | 	\:	 | 	  | Formatting divider; If outside formatting, then formatting mark
009 | 09 | 0000 0000 | 	HT | 	\t	 | ␉	  | Horizontal Tab
010 | 0A | 0000 0000 | 	NL | 	\n	 | U+2424 | New Line
011 | 0B | 0000 0000 | 	0x1 | 	\1	 | ١	  | Hexadecimal number one
012 | 0C | 0000 0000 | 	0x2 | 	\2	 | ٢	  | Hexadecimal number two
013 | 0D | 0000 0000 | 	0x3 | 	\3	 | ٣	  | Hexadecimal number three
014 | 0E | 0000 0000 | 	0x4 | 	\4	 | ٤	  | Hexadecimal number four
015 | 0F | 0000 0000 | 	0x5 |	\5	 | ٥	  | Hexadecimal number five
016 | 10 | 0000 0000 | 	0x6 | 	\6	 | ٦	  | Hexadecimal number six
017 | 11 | 0000 0000 | 	0x7 | 	\7	 | ٧	  | Hexadecimal number seven
018 | 12 | 0000 0000 | 	0x8 | 	\8	 | ٨	  | Hexadecimal number eight
019 | 13 | 0000 0000 | 	0x9 | 	\9	 | ٩	  | Hexadecimal number nine
020 | 14 | 0000 0000 | 	0xA | 	\a	 | < ٠ with ٠ on top>	  | Hexadecimal number ten
021 | 15 | 0000 0000 | 	0xB | 	\b	 | < ١ with ٠ on top>	  | Hexadecimal number eleven
022 | 16 | 0000 0000 | 	0xC | 	\c	 | < ٢ with ٠ on top>	  | Hexadecimal number twelve
023 | 17 | 0000 0000 | 	0xD | 	\d	 | < ٣ with ٠ on top>	  | Hexadecimal number thirteen
024 | 18 | 0000 0000 | 	0xE | 	\e	 | < ٤ with ٠ on top>	  | Hexadecimal number fourteen
025 | 19 | 0000 0000 | 	0xF | 	\f	 | < ٥ with ٠ on top>	  | Hexadecimal number fifteen
026 | 1A | 0000 0000 | 	CUR | 	\C	 | ¤	  | Currency sign
027 | 1B | 0000 0000 | 	ESC | 	\E	 | ␛	  | Escape the next character; if the character has special meaning, dont interpret the meaning just print its symbol
028 | 1C | 0000 0000 | 	<&#124; | 	\(	 | ⟨	  | Angle bracket left
029 | 1D | 0000 0000 | 	&#124;> | 	\)	 | ⟩	  | Angle bracket right
030 | 1E | 0000 0000 | 	LDAQ | 	\<	 | «	 | 
031 | 1F | 0000 0000 | 	RDAQ | 	\>	 | »	 | 
032 | 20 | 0000 0000 | 	SP | 	  	 | ␠	  | space
033 | 21 | 0000 0000 | 	! | 	!	 | !	  | exclamation mark
034 | 22 | 0000 0000 | 	" | 	"	 | "	  | Quotation mark
035 | 23 | 0000 0000 | 	# | 	#	 | #	  | Number sign
036 | 24 | 0000 0000 | 	$ | 	$	 | $	  | Dollar sign
037 | 25 | 0000 0000 | 	% | 	%	 | %	  | Percent sign
038 | 26 | 0000 0000 | 	& | 	&	 | &	  | Ampersand
039 | 27 | 0000 0000 | 	' | 	'	 | '	  | Apostrophe)
040 | 28 | 0000 0000 | 	( | 	(	 | (	  | round brackets or parentheses
041 | 29 | 0000 0000 | 	) | 	)	 | )	  | round brackets or parentheses
042 | 2A | 0000 0000 | 	* | 	*	 | *	  | Asterisk
043 | 2B | 0000 0000 | 	+ | 	+	 | +	  | Plus sign
044 | 2C | 0000 0000 | 	, | 	,	 | ,	  | Comma
045 | 2D | 0000 0000 | 	- | 	-	 | -	  | Hyphen
046 | 2E | 0000 0000 | 	. | 	.	 | .	  | Full stop , dot
047 | 2F | 0000 0000 | 	/ | 	/	 | /	  | Slash
048 | 30 | 0000 0000 | 	0 | 	0	 | 0	  | Decimal number zero
049 | 31 | 0000 0000 | 	1 | 	1	 | 1	  | Decimal number one
050 | 32 | 0000 0000 | 	2 | 	2	 | 2	  | Decimal number two
051 | 33 | 0000 0000 | 	3 | 	3	 | 3	  | Decimal number three
052 | 34 | 0000 0000 | 	4 | 	4	 | 4	  | Decimal number four
053 | 35 | 0000 0000 | 	5 | 	5	 | 5	  | Decimal number five
054 | 36 | 0000 0000 | 	6 | 	6	 | 6	  | Decimal number six
055 | 37 | 0000 0000 | 	7 | 	7	 | 7	  | Decimal number seven
056 | 38 | 0000 0000 | 	8 | 	8	 | 8	  | Decimal number eight
057 | 39 | 0000 0000 | 	9 | 	9	 | 9	  | Decimal number nine
058 | 3A | 0000 0000 | 	: | 	:	 | :	  | Colon
059 | 3B | 0000 0000 | 	; | 	;	 | ;	  | Semicolon
060 | 3C | 0000 0000 | 	< | 	<	 | <	  | Less-than sign; left guillemet 
061 | 3D | 0000 0000 | 	= | 	=	 | =	  | Equals sign
062 | 3E | 0000 0000 | 	> | 	>	 | >	  | Greater-than sign ; Inequality; right guillemet 
063 | 3F | 0000 0000 | 	? | 	?	 | ?	  | Question mark
064 | 40 | 0000 0000 | 	@ | 	@	 | @	  | At sign
065 | 41 | 0000 0000 | 	A | 	A	 | A	  | Capital A 
066 | 42 | 0000 0000 | 	B | 	B	 | B	  | Capital B 
067 | 43 | 0000 0000 | 	C | 	C	 | C	  | Capital C 
068 | 44 | 0000 0000 | 	D | 	D	 | D	  | Capital D 
069 | 45 | 0000 0000 | 	E | 	E	 | E	  | Capital E 
070 | 46 | 0000 0000 | 	F | 	F	 | F	  | Capital F 
071 | 47 | 0000 0000 | 	G | 	G	 | G	  | Capital G 
072 | 48 | 0000 0000 | 	H | 	H	 | H	  | Capital H 
073 | 49 | 0000 0000 | 	I | 	I	 | I	  | Capital I 
074 | 4A | 0000 0000 | 	J | 	J	 | J	  | Capital J 
075 | 4B | 0000 0000 | 	K | 	K	 | K	  | Capital K 
076 | 4C | 0000 0000 | 	L | 	L	 | L	  | Capital L 
077 | 4D | 0000 0000 | 	M | 	M	 | M	  | Capital M 
078 | 4E | 0000 0000 | 	N | 	N	 | N	  | Capital N 
079 | 4F | 0000 0000 | 	O | 	O	 | O	  | Capital O 
080 | 50 | 0000 0000 | 	P | 	P	 | P	  | Capital P 
081 | 51 | 0000 0000 | 	Q | 	Q	 | Q	  | Capital Q 
082 | 52 | 0000 0000 | 	R | 	R	 | R	  | Capital R 
083 | 53 | 0000 0000 | 	S | 	S	 | S	  | Capital S 
084 | 54 | 0000 0000 | 	T | 	T	 | T	  | Capital T 
085 | 55 | 0000 0000 | 	U | 	U	 | U	  | Capital U 
086 | 56 | 0000 0000 | 	V | 	V	 | V	  | Capital V 
087 | 57 | 0000 0000 | 	W | 	W	 | W	  | Capital W 
088 | 58 | 0000 0000 | 	X | 	X	 | X	  | Capital X 
089 | 59 | 0000 0000 | 	Y | 	Y	 | Y	  | Capital Y 
090 | 5A | 0000 0000 | 	Z | 	Z	 | Z	  | Capital Z 
091 | 5B | 0000 0000 | 	[ | 	[	 | [	  | square brackets or box brackets
092 | 5C | 0000 0000 | 	\ | 	\\	 | \	  | Backslash
093 | 5D | 0000 0000 | 	] | 	]	 | ]	  | square brackets or box brackets
094 | 5E | 0000 0000 | 	^ | 	^	 | ^	  | Caret or circumflex accent
095 | 5F | 0000 0000 | 	_ | 	_	 | _	  | underscore , understrike , underbar or low line
096 | 60 | 0000 0000 | 	` | 	`	 | `	  | Grave accent
097 | 61 | 0000 0000 | 	a | 	a	 | a	  | Lowercase  a 
098 | 62 | 0000 0000 | 	b | 	b	 | b	  | Lowercase  b 
099 | 63 | 0000 0000 | 	c | 	c	 | c	  | Lowercase  c 
100 | 64 | 0000 0000 | 	d | 	d	 | d	  | Lowercase  d 
101 | 65 | 0000 0000 | 	e | 	e	 | e	  | Lowercase  e 
102 | 66 | 0000 0000 | 	f | 	f	 | f	  | Lowercase  f 
103 | 67 | 0000 0000 | 	g | 	g	 | g	  | Lowercase  g 
104 | 68 | 0000 0000 | 	h | 	h	 | h	  | Lowercase  h 
105 | 69 | 0000 0000 | 	i | 	i	 | i	  | Lowercase  i 
106 | 6A | 0000 0000 | 	j | 	j	 | j	  | Lowercase  j 
107 | 6B | 0000 0000 | 	k | 	k	 | k	  | Lowercase  k 
108 | 6C | 0000 0000 | 	l | 	l	 | l	  | Lowercase  l 
109 | 6D | 0000 0000 | 	m | 	m	 | m	  | Lowercase  m 
110 | 6E | 0000 0000 | 	n | 	n	 | n	  | Lowercase  n 
111 | 6F | 0000 0000 | 	o | 	o	 | o	  | Lowercase  o 
112 | 70 | 0000 0000 | 	p | 	p	 | p	  | Lowercase  p 
113 | 71 | 0000 0000 | 	q | 	q	 | q	  | Lowercase  q 
114 | 72 | 0000 0000 | 	r | 	r	 | r	  | Lowercase  r 
115 | 73 | 0000 0000 | 	s | 	s	 | s	  | Lowercase  s 
116 | 74 | 0000 0000 | 	t | 	t	 | t	  | Lowercase  t 
117 | 75 | 0000 0000 | 	u | 	u	 | u	  | Lowercase  u 
118 | 76 | 0000 0000 | 	v | 	v	 | v	  | Lowercase  v 
119 | 77 | 0000 0000 | 	w | 	w	 | w	  | Lowercase  w 
120 | 78 | 0000 0000 | 	x | 	x	 | x	  | Lowercase  x 
121 | 79 | 0000 0000 | 	y | 	y	 | y	  | Lowercase  y 
122 | 7A | 0000 0000 | 	z | 	z	 | z	  | Lowercase  z 
123 | 7B | 0000 0000 | 	{ | 	{	 | {	  | curly brackets or braces
124 | 7C | 0000 0000 | 	&#124; | 	&#124;	 | &#124;	  | vertical-bar, vbar, vertical line or vertical slash
125 | 7D | 0000 0000 | 	} | 	}	 | }	  | curly brackets or braces
126 | 7E | 0000 0000 | 	~ | 	~	 | ~	  | Tilde ; swung dash
127 | 7F | 0000 0000 |	APPR | 	\\~	 | ≈	  | Approx; double swing dash



