# RACP
**R**evised **A**SCII **C**odes for **P**rogramming - 
A character set for the modern programming environment.

## Motivation

The use of the ASCII character set has been ongoing for about 50 years now<sup>1</sup> and it seems time for a modernisation.
I find that ASCII no longer fits the role it has as the de facto character set. It was designed for use with teletypes<sup>2</sup>, but is now used for so much more. This begs the question: why? The short answer is that it's what we have always done. But, this answer hinders progress and innovation, so I have decided to try and modernise ASCII for the current software development environment.

ASCII uses 7-bit character codes, i.e. 128 codes in total, but about a quarter of those are control codes which are rarely used<sup>3</sup>. A huge waste. 
RACP aims at repurposing those codes for useful endevours and additionally define rules which solve some of the annoyances programmers face today. Like: 

*"Why does this file not use the proper new-line;* <insert *\n* or *\r\n* at your discretion> *?"*

*"Is that number in hexadecimal, decimal, or, god forbid, binary?"*

and everyones favorite: 

*"Was this file tabbed with 2-, 4-, or 8-space tabs?"*

ASCII is not all bad, I must admit. Most characters are well thought out and many are ingeniously placed. 
Therefore, for all non-useless characters ( which is essentially all non-control characters <sup>4</sup> ) are also found in RACP, though they may be repositioned. No reason to reinvent the wheel, just the spokes.

Lastly, I must acknowledge that this is probably a futile endevour, as it is excruciatingly unlikely that the world will switch from using ASCII. But, at least I will know that there is a better solution out there - even though no one wants to use it.
#

1: https://en.wikipedia.org/wiki/ASCII

2: http://www.asciitable.com/

3: This is actually just an assumption of mine. (<sup>2</sup>) does hint at its correctness, but is not definitive.

4: The exception is the pseudo character 'Accent Grave' which will be replaced by a proper character.

## FAQ

#### *Its < insert current year >, why make a new character set that does not support all natural languages?*

_Warning: Very subjective opinion._

RACP is designed for programmers to use in source/protocol code. Therefore, non-technical users are not expected to
ever read pure RACP. Internationalization is not a goal of the character set. 
In reality, RACP is specifically designed to minimize internationalization so that all of the world's programmers can converge on a single
natural language, English. This is an important goal, as it will allow all programmers to always be able to read other programmers' code.
The obvious choice of language is English, and therefore RACP only supports strictly English characters.
Anyone who want to argue against the use of English must provide extraordenary evidence that shows that most source code today or ever is written in another language that should be used instead.

The proliferation of Unicode in programming language sources is bad for the world. 
It means that programmers from different human language backround can isolate themselves by writing their programs using their native language, which is unreadable to all non-native programmers. 
Additionally, it actually allows the use of emojis as variable names. If anyone seriously thinks that is a good idea, I urge them to rethink their life choices.

#### *Why not use all 256 values available in a byte and add more characters?*

The primary reason is that of future compatability. By having one bit free, we will able to, in the future, define a Unicode like character set or encoding which always has that bit set to 1. This makes it trivial to figure out which of the two incodings a string is written in. 

The second reason is that it does not really add that much functionality. 128 different characters seems more than enough for all current use cases, when remembering that we use ASCII for everything today, which has 31 less characters (that are usable).
Additionally, character bloat is a real thing. There are only so many keys on a keyboard, and even with only 128 characters I have spent a considerable amount of time trying to find new key combinations to use for the new characters. 
Also, can we really expect programmers to remember 256 characters? Can we even expect them to remember 128? If programmers can't remember which characters are at their disposal and how to type them, they will never use them.

If I could, I would have less characters. Since we cannot have undefined characters in the 7 bit range, and 64 (6-bit) is strictly too few, 128 is the answer.

#### *Why is there no* NULL *character?*

What does *NULL* mean? the lack of a character? how can a character be the lack of a character? shouldn't there just *not* be a character?

*"How will you implement null-terminated strings without a NULL character?"*

You shouldn't. Null-terminated strings have been called many things. I will now call them an abomination.

#### *What use cases is the character set designed for?*

RACP should be used anywhere a programmer needs to read or edit text. 
Source code is the most obvious and prevalent situation. 
Even though protocols are intended for machine reading, programmers must be able to read what is sent and received when developing or debugging applications using the protocol.

#### *Why are Elastic Tabstops a requirement and not just a recommendation?*

Because recommendations are ignored<sup>5</sup> and this is Better<sup>TM</sup>.

#### *Why use the names* Jamb *and* Lath *for the new brackets?*

I wanted short names which are not widely used in computer science (yet). 
I searched for bracket synonyms and they came up. 
Their real world meanings are also comfortably close to what they should be used for (though many might disagree with this).

#### *What are those weird characters you use for the first 9 hexadecimals?*

They are used when writing numbers in Arabic.<sup>6</sup>
Since I speak and write Arabic they seemed as an obvious choice. 
I tried inventing my own characters but I am apparently not a very creative person.

#### *Many of the new characters have no Graphics representation. Why is that?*

They are characters which do not have an established unique graphical representation. 
The specification does not yet seek to mandate their graphical representation, though the reference editor does propose (non-mandatory) representations.

No graphical representation of *any* character is mandated by this specification.

#### *Terminals often use the so-called unused control characters*

This is true. Though, any terminals using RACP would have probably been written from scratch to do so, which is a great opportunity to instead implement a proper interface that does not rely on specific characters.
I do not see this as a problem, as all applications should distinguish between characters and commands. In RACP, no character should be interpreted as a command, not even TAB or NL.

#
5: https://en.wikipedia.org/wiki/Metric_Conversion_Act

6: https://en.wikipedia.org/wiki/Eastern_Arabic_numerals


