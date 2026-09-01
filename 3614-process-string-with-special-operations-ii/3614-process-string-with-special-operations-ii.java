class Solution {
    public char processStr(String s, long k) {

        int n = s.length();

        // length[i] = length of result after processing s[i]
        long[] length = new long[n];

        long len = 0;

        // First pass: calculate lengths
        for (int i = 0; i < n; i++) {

            char ch = s.charAt(i);

            if (ch >= 'a' && ch <= 'z') {
                len++;
            }

            else if (ch == '*') {
                if (len > 0) {
                    len--;
                }
            }

            else if (ch == '#') {
                len *= 2;
            }

            // '%' does not change length

            length[i] = len;
        }

        // k is 0-based
        if (k >= len) {
            return '.';
        }

        // Second pass: work backwards
        for (int i = n - 1; i >= 0; i--) {

            char ch = s.charAt(i);

            if (ch >= 'a' && ch <= 'z') {

                /*
                 * This character was appended at the
                 * last position of the string at this point.
                 */
                if (k == length[i] - 1) {
                    return ch;
                }

                // Remove this character while going backwards
                len = length[i] - 1;
            }

            else if (ch == '*') {

                /*
                 * '*' removed one character.
                 * Restore the previous length.
                 */
                len++;
            }

            else if (ch == '#') {

                /*
                 * Before #:
                 *     ABC
                 *
                 * After #:
                 *     ABCABC
                 *
                 * Map second-half indices back to
                 * the corresponding first-half index.
                 */
                long half = length[i] / 2;

                if (half > 0 && k >= half) {
                    k %= half;
                }

                len = half;
            }

            else if (ch == '%') {

                /*
                 * Reverse operation.
                 *
                 * index k in reversed string corresponds to
                 * index len - 1 - k in original string.
                 */
                if (len > 0) {
                    k = len - 1 - k;
                }
            }
        }

        return '.';
    }
}