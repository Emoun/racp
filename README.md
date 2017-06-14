# RACP
**R**evised **A**SCII **C**odes for **P**rogramming - 
A character set for the modern programming environment.

## Motivation

The use of the ASCII character set has been ongoing for about 50 years now<sup>1</sup> and it seems time for a modernisation.
I find that ASCII no longer fits the role it has as the de facto character set. It was designed for use with teletypes<sup>2</sup>, but is now used for so much more. This begs the question: why? The short answer is that its what we have always done. But, this answer hinders progress and innovation, so I have decided to try and modernise ASCII for the current software development environment.

ASCII is uses 7-bit code, i.e. 128 codes in total, but about a quarter of those are control codes which are rarely used<sup>3</sup>. A huge waste. 
RACP aims at repurposing those codes for useful endevours and additionally define rules which solve some of the annoyances programmers face today. Like: 

*"Why does this file not use the proper new-line;* <insert *\n* or *\n\r* at your discretion> *?"*

*"Is that number in hexadecimal, decimal, or, god forbid, binary?"*

and everyones favorite: 

*"Was this file tabbed with 2-, 4-, or 8-space tabs?"*

ASCII is not all bad, I must admit. Most characters are well thought out an ingeniously placed. 
Therefore, for all non-useless characters ( which is essentially all, except the control characters ) RACP will maintain their placement
as it is in ASCII. No reason to reinvent the wheel, just the spokes.

Lastly, I must acknowledge that this is probably a futile endevour, as it is excruciatingly unlikely that the world will switch from using ASCII. But, at least I will know that there is a better solution out there - even though no one wants to use it.
#

1: https://en.wikipedia.org/wiki/ASCII

2: http://www.asciitable.com/

3: This is actually just an assumption of mine. (<sup>2</sup>) does hint at its correctness, but is not definitive.


