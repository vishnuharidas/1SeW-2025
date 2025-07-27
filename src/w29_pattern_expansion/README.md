# Week 29: Pattern Expansion Challenge

## Problem Statement

Given a string pattern containing letters, digits, and brackets, expand the pattern according to the following rules:

- Digits before a letter or bracket indicate how many times the following element should be repeated.
- Brackets `[...]` can contain nested patterns, which should be expanded recursively.
- Letters are output as-is unless preceded by a digit, in which case they are repeated.

### Example

Input: `2a3x4[bcd5[X]]ef`

Expansion steps:

- `2a` → `aa`
- `3x` → `xxx`
- `4[bcd5[X]]` → Expand inner bracket: `bcd5[X]` → `bcdXXXXX`, then repeat 4 times: `bcdXXXXXbcdXXXXXbcdXXXXXbcdXXXXX`
- `ef` → `ef`

Final output: `aaxxxbcdXXXXXbcdXXXXXbcdXXXXXbcdXXXXXef`
