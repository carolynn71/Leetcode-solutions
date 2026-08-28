class Solution {
    public String lexPalindromicPermutation(String s, String target) {

        int n = s.length();

        // Count characters
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // A palindrome can have at most one odd frequency
        int odd = 0;
        int middle = -1;

        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1) {
                odd++;
                middle = i;
            }
        }

        if (odd > 1) {
            return "";
        }

        // Only half the characters are needed
        // to construct the left half.
        for (int i = 0; i < 26; i++) {
            freq[i] /= 2;
        }

        int half = n / 2;

        char[] ans = new char[n];

        /*
         * First try to make the left half
         * exactly equal to target's left half.
         */
        int pos = 0;

        while (pos < half) {

            int c = target.charAt(pos) - 'a';

            if (freq[c] == 0) {
                break;
            }

            ans[pos] = target.charAt(pos);
            freq[c]--;
            pos++;
        }

        /*
         * If we successfully matched the entire
         * left half, construct the palindrome and
         * check whether it is strictly greater.
         */
        if (pos == half) {

            buildPalindrome(ans, half, middle);

            String result = new String(ans);

            if (result.compareTo(target) > 0) {
                return result;
            }
        }

        /*
         * We need to make the answer greater.
         *
         * Backtrack from the current position.
         * At each position try the smallest character
         * greater than target[pos].
         */
        while (true) {

            if (pos < half) {

                int targetChar =
                    target.charAt(pos) - 'a';

                for (int c = targetChar + 1; c < 26; c++) {

                    if (freq[c] > 0) {

                        ans[pos] = (char) ('a' + c);
                        freq[c]--;

                        // Fill remaining left half
                        // with smallest possible characters.
                        int index = pos + 1;

                        for (int x = 0; x < 26; x++) {

                            while (freq[x] > 0) {
                                ans[index++] = (char) ('a' + x);
                                freq[x]--;
                            }
                        }

                        buildPalindrome(ans, half, middle);

                        return new String(ans);
                    }
                }
            }

            // No larger character at this position.
            // Backtrack.
            if (pos == 0) {
                return "";
            }

            pos--;

            int c = target.charAt(pos) - 'a';

            freq[c]++;
        }
    }

    private void buildPalindrome(
            char[] ans,
            int half,
            int middle) {

        // Middle character for odd length
        if (middle != -1) {
            ans[half] = (char) ('a' + middle);
        }

        // Mirror left half
        for (int i = 0; i < half; i++) {
            ans[ans.length - 1 - i] = ans[i];
        }
    }
}