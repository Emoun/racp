# Revised ASCII Codes for Programming Specification - Glosarry

**Character Display Slot**: 

When displaying a string for a human(oid) user on a screen (in 2D), a Character Display Slot (CDS) defines the area on the screen in which one (1) character of the string is displayed. All CDSs are not nescessairy the same size as that is delegated to the font to specify. Monospaced fonts would have CDSs with the same size.

A CDS is said to *display* a character, of the displayed graphic in its area represents the character. 

A CDS is said to *contain* a character, if it is displaying that specific character from the string. I.e. a CDS contains a specific character from a specific string, regardless of the the encoding of the character. E.g. given a string "cbc", a CDS can contain either the first or the second 'c', but not both at the same time. A CDS containing a character must also be displaying it.
The exceptions to this rule are the TAB character, which is usually contained by multiple, consequtive CDSs, and the NL character, which is usually not contained by any CDS. In that case, the next CDS is therefore the next CDS not containing a NL.

Given a CDS containing a character, the *next* CDS is the CDS containing the succeeding character. I.e. if a CDS is containing the fifth character in a string, the next CDS contains the sixth character in the same string. If a CDS contains the last character in a string, the next CDS is the one that would have displayed the next character in the string, if the string had been longer.

If a CDS is the next CDS to another CDS, that CDS is then the *previous* CDS to the CDS in question.

A character is said to *take up* a CDS if that CDS contains the character.

















