# Using a Byte to Store 8 Different Flags

A byte consists of 8 bits, and each bit can either be `0` (off) or `1` (on). This makes it possible to use a single byte
to store up to 8 different flags, where each flag represents a separate piece of information (such as a setting being
enabled or disabled).

## How Flags Work

Each bit in a byte represents a **flag**. If a bit is set to `1`, it means the flag is **true**; if it's `0`, the flag
is **false**.

For example:

- `00000001`: Only flag at position `0` is true.
- `00000010`: Only flag at position `1` is true.
- `00000011`: Flags at positions `0` and `1` are true.
- `00000101`: Flags at positions `0` and `2` are true.

Each flag corresponds to a **unique bit position**, so we can turn multiple flags on/off independently within the same
byte.

## Operations

### Setting a bit to `1` (representing `true` value)

To set a specific flag `true`, we **set** the corresponding bit to `1`. This is done using the **OR (`|`) operation**:

```
byte = byte | (1 << flag_position)
```

Example: Turning on flag at position `2` (`00000100`):

- Current byte: `00000000` (0)
- Apply `OR` with `00000100` (4)
- Result: `00000100` (4)

### Setting a bit to `0` (representing `false` value)

To set a flag to `false`, we **clear** the corresponding bit by setting it to `0`. This is done using the **AND (`&`)
operation with an inverted bit mask**:

```
byte = byte & ~(1 << flag_position)
```

Example: Turning off flag at position `2` from `00000100` (4):

- Current byte: `00000100` (4)
- Apply `AND` with `11111011` (~4)
- Result: `00000000` (0)

### Checking a flag's value

We can use the **AND (`&`) operation** to check if the flag is set (`true`) or not (`false`).

```
flag_value = (byte & (1 << flag_position)) != 0
```

Example: Checking if flag at position `2` is enabled in `00000100`:

- `00000100 & 00000100 = 00000100` (true)
- `00000100 & 00001000 = 00000000` (false)

