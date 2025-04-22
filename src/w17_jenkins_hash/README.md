# Jenkins’ Hash Function

This is the implementation of [Jenkins’ Hash function](https://en.wikipedia.org/wiki/Jenkins_hash_function) named "
one_at_a_time". It is a simple hash function that produces a 32-bit hash value from an input string with good avalanche
effect.

I vibe-coded this to try out the OpenAI [Codex](https://openai.com/index/openai-codex/) tool. Codex is an AI system that
not only writes code but can also execute it, use the results to generate more code, and perform tasks like file
manipulation, system commands, and more. It functions like a programmer with significant AI assistance.

## Algorithm

This is the algorithm for the hash function named "one_at_a_time":

1. Initialize a 32‑bit integer `hash = 0`.
2. For each input byte `b`:
    - `hash += b & 0xFF`
    - `hash += hash << 10`
    - `hash ^= hash >>> 6`
3. After processing all bytes:
    - `hash += hash << 3`
    - `hash ^= hash >>> 11`
    - `hash += hash << 15`
4. Convert `hash` to unsigned and format as 8‑digit lowercase hex.

## Output

Running the program without arguments prints the hash for a default set of characters:

```text
0 -> 6e3c5c6b
1 -> 806b80c9
2 -> 91aa2346
3 -> a400c7f3
4 -> b803eff9
5 -> c947127f
6 -> db7fb6f0
7 -> ecc9d984
8 -> 00440078
9 -> 1302a5f5
a -> ca2e9442
b -> 00db819b
c -> eeba5d59
d -> 2350c685
e -> 11162210
f -> 48058fee
g -> 3593eb0b
h -> 7003dfea
i -> 5dc63b6f
j -> 9272a4c7
k -> 8034004a
l -> b70b6df8
m -> a4c9c975
n -> d98832f1
o -> c74f0e7f
p -> 008c80f9
q -> efdd5f9b
r -> 26014be2
s -> 13bb2756
t -> 497812cf
u -> 3734ee49
v -> 6afb55d5
w -> 58a0b120
x -> 9303a5e5
y -> 80950108
z -> b6606c9e
A -> 820103f0
B -> b7346e56
C -> a69ecd2b
D -> da97351b
E -> c8cc9186
F -> fe39fc60
G -> ec7858dd
H -> 25364a58
I -> 137826dc
J -> 4a931511
K -> 38cc7184
L -> 6ce1d9ae
M -> 5a1fb42a
N -> 91aca343
O -> 80037ff1
P -> b7656eb4
Q -> a51fca29
R -> dc49b87c
S -> ca8b9500
T -> fe6cfcc2
U -> ec3b585f
V -> 23bfc767
W -> 11f523d2
X -> 4aa61533
Y -> 38cf7186
Z -> 6e30dc48
` -> dc7cb8de
~ -> ff65fea8
! -> 5b9c372f
@ -> 93bfa76d
# -> 7f1efe34
$ -> 92952520
% -> a55f4ab4
^ -> b51a6a1a
& -> b672ecdb
* -> ff387e65
( -> dbb4b75e
) -> ee22dc3a
- -> 371a6e28
_ -> a36846b6
= -> 5b2a3643
+ -> 11e1a3b7
[ -> 5c6338ad
] -> 7e467c73
{ -> a42ac833
} -> c9b0933e
\ -> 919da321
| -> dbdb3793
; -> 357f6aee
: -> 24b9c963
' -> c8e411bd
" -> 6d495a89
, -> 2548ca85
< -> 496f92ce
. -> 48fc91ec
> -> 6df35bd5
/ -> 5a873501
? -> 7fb4ff58
```

## Avalanche Effect

Even small changes in input produce vastly different hashes. For example:

```text
aaa -> ae4f22ec
aab -> a35c0d06
aac -> 6abf1bcd
aad -> bfe7c61d
aae -> f886b75a
aaf -> e68e1369
aag -> 115168ef
aah -> 07035453
aai -> df4584d0
aaj -> 4d77e133
```