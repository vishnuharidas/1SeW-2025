# Stalin Sort

"Stalin sort" is a humorous sorting algorithm where you iterate through the list from left to right, and remove any
element that is not greater than the previous kept element. The result is a monotonically increasing subsequence, not a
fully sorted list.

Example:

Input: `[3, 1, 4, 2, 5]`

Output: `[3, 4, 5]`

## Example Output

For a random list of 100 integers between 0 and 100:

```
Original List: [13, 11, 22, 55, 47, 45, 10, 67, 52, 31, 28, 70, 52, 4, 64, 9, 86, 63, 91, 9, 69, 13, 58, 93, 89, 79, 43, 33, 97, 65, 90, 35, 41, 41, 85, 66, 59, 88, 76, 82, 72, 66, 71, 83, 72, 21, 57, 35, 10, 48, 24, 91, 63, 45, 62, 23, 24, 59, 87, 3, 52, 9, 79, 65, 84, 63, 55, 58, 88, 62, 90, 24, 19, 41, 19, 87, 96, 22, 88, 97, 76, 54, 25, 98, 38, 2, 28, 33, 92, 9, 21, 45, 52, 18, 96, 46, 66, 87, 48, 85]
Sorted List: [13, 22, 55, 67, 70, 86, 91, 93, 97, 98]
```