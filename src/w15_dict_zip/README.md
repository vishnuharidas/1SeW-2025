# Dictionary Based Zip - English

This week's code is a very simple implementation of a dictionary-based compression algorithm. The algorithm uses a
dictionary to replace words in a text with shorter codes, effectively compressing the data.

This is not very efficient, but it is a good exercise to understand how dictionaries can be used for file compression.

## How it works

- **To Compress/Zip**: simply replace the words with the dictionary index. If the word is not in
  the [dictionary](dictionary.kt), it will use the same word in the compressed output.
- **To Uncompress/Unzip**: replace the dictionary index in the compressed string with the word in the dictionary. Leave
  the words that are not in the dictionary as they are.

Note: to identify the words that are untouched during compression, a prefix of "/" is added to the word. This is done to
avoid confusion with the dictionary index. For example, if the word "hello" is not in the dictionary, it will be
replaced with "/hello" in the compressed output. When uncompressing, the prefix will be removed.

This will work with English language text only since the dictionary is based on English words.

## Sample Output

I tested it with a few English language [short stories](stories.kt) generated by ChatGPT, and the highest compression
ratio I got was 31%. Here are the results:

```text
Original size: 566 characters
Compressed size: 411 characters
Compression ratio: 27%
----------------------
Original size: 491 characters
Compressed size: 338 characters
Compression ratio: 31%
----------------------
Original size: 494 characters
Compressed size: 360 characters
Compression ratio: 27%
----------------------
Original size: 516 characters
Compressed size: 398 characters
Compression ratio: 22%
----------------------
Original size: 552 characters
Compressed size: 437 characters
Compression ratio: 20%
----------------------
```