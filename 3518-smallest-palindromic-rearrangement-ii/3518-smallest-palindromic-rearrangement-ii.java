class Solution {

    private static final long LIMIT = 1_000_001L;

    public String smallestPalindrome(String s, int k) {

        // Required by the problem
        String prelunthak = s;

        int[] freq = new int[26];

        for (char c : prelunthak.toCharArray()) {
            freq[c - 'a']++;
        }

        // Count characters in the left half
        int[] half = new int[26];

        char middle = 0;

        for (int i = 0; i < 26; i++) {

            half[i] = freq[i] / 2;

            if (freq[i] % 2 == 1) {
                middle = (char) ('a' + i);
            }
        }

        int halfLength = s.length() / 2;

        // Check whether at least k permutations exist
        long totalWays = countWays(half, halfLength);

        if (totalWays < k) {
            return "";
        }

        StringBuilder left = new StringBuilder();

        long rank = k;

        // Construct left half
        for (int pos = 0; pos < halfLength; pos++) {

            for (int ch = 0; ch < 26; ch++) {

                if (half[ch] == 0) {
                    continue;
                }

                // Try putting this character here
                half[ch]--;

                long ways = countWays(
                    half,
                    halfLength - pos - 1
                );

                if (rank > ways) {

                    // k-th answer is not in this block
                    rank -= ways;

                    // Undo the choice
                    half[ch]++;

                } else {

                    // k-th answer is inside this block
                    left.append((char) ('a' + ch));

                    break;
                }
            }
        }

        // Build the palindrome
        String right =
            new StringBuilder(left)
                .reverse()
                .toString();

        if (middle != 0) {
            return left.toString()
                    + middle
                    + right;
        }

        return left.toString() + right;
    }

    /*
     * Counts the number of distinct permutations
     * that can be made from the remaining characters.
     *
     * We only care whether the answer reaches LIMIT,
     * because k <= 1,000,000.
     */
    private long countWays(int[] count, int total) {

        long ways = 1;
        int remaining = total;

        for (int c : count) {

            if (c == 0) {
                continue;
            }

            /*
             * Choose c positions for this character:
             *
             * C(remaining, c)
             *
             * Then continue with the remaining characters.
             */
            long combinations =
                combinationCapped(remaining, c);

            ways = ways * combinations;

            if (ways >= LIMIT) {
                return LIMIT;
            }

            remaining -= c;
        }

        return ways;
    }

    /*
     * Calculate C(n, r), but stop once it reaches LIMIT.
     */
    private long combinationCapped(int n, int r) {

        r = Math.min(r, n - r);

        long result = 1;

        for (int i = 1; i <= r; i++) {

            result =
                result * (n - i + 1) / i;

            if (result >= LIMIT) {
                return LIMIT;
            }
        }

        return result;
    }
}