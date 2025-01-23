# Checking for same Gmail address

Gmail provides a unique feature that allows multiple variations of the same email address, using dots (`.`) and the plus
sign (`+`). For example, the following Gmail addresses are all valid and route to the same inbox:

- `user@gmail.com`
- `u.s.e.r@gmail.com`
- `user+twitter@gmail.com`
- `user+insta@gmail.com`

All of these email addresses belong to the same inbox (`user@gmail.com`). This feature allows users to sign up for
different services or websites using variations of their email and easily filter emails based on the source.

### The Problem for Developers

For website developers, this feature can sometimes be problematic. Many websites do not validate email variations
properly, treating each variation as a unique email address. This loophole can allow users to create multiple accounts
using variations of the same Gmail address.

### About This Code

This week's code extracts the original username from a given Gmail address variation. This can be used to find the
actual Gmail username, to prevent signing up using a variation of their Gmail address.