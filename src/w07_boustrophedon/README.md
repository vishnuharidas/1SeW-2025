# Boustrophedon Text

I was researching early civilizations when I discovered that the ancient Egyptian writing system, known as hieroglyphs,
could be written both left-to-right and right-to-left, as well as in a style called "boustrophedon". This sparked my
interest, so I wrote a small snippet that prints any given text in boustrophedon style.

## What is Boustrophedon?

From [Wikipedia,](https://en.wikipedia.org/wiki/Boustrophedon)

> Boustrophedon is a style of writing in which alternate lines of writing are reversed, with letters also written in
> reverse, mirror-style. This is in contrast to modern European languages, where lines always begin on the same side,
> usually the left.

Here's a sample text from the Wikipedia page:

![An example, in English, of boustrophedon as used in inscriptions in ancient Greece (Lines 2 and 4 read right-to-left.)](https://upload.wikimedia.org/wikipedia/commons/0/00/Boustrophedon_text.png)

## The code

First, I needed a way to split the text into multiple lines, each constrained to a maximum of N characters. I
implemented this in two ways. The first, simpler approach is to split the text into chunks of N characters and then
reverse every other chunk (which may split words across lines). The second approach avoids splitting words by selecting
only the words that can fit on each line without breaking them.

Next, I explored whether Unicode offers characters that serve as mirror images of regular alphanumeric characters to use
in the "reversed" lines. Unfortunately, there are none that directly mirror standard alphanumerics. Therefore, I used
the regular characters, reversing the text order to achieve a Boustrophedon-like effect.

## Sample Output

```text
40 Columns - Easy Way (with split word):
||||||||||||||||||||||||||||||||||||||||
Boustrophedon is a style of writing in w
ever era gnitirw fo senil etanretla hcih
rsed, with letters also written in rever
ot tsartnoc ni si sihT .elyts-rorrim ,es
 modern European languages, where lines 
t yllausu ,edis emas eht no nigeb syawla
he left.

40 Columns - Hard Way (no words split):
||||||||||||||||||||||||||||||||||||||||
Boustrophedon is a style of writing in 
 era gnitirw fo senil etanretla hcihw
reversed, with letters also written in 
 ni si sihT .elyts-rorrim ,esrever
contrast to modern European languages, 
 emas eht no nigeb syawla senil erehw
side, usually the left. 
```
