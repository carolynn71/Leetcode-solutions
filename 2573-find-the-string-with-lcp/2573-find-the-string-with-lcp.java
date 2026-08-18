class Solution {

    public String findTheString(int[][] lcp) {

        int n = lcp.length;
        char[] s = new char[n];

        // Build the lexicographically smallest string
        int index = 0;

        for (char c = 'a'; c <= 'z'; c++) {

            // Find the first unassigned position
            while (index < n && s[index] != '\0') {
                index++;
            }

            if (index == n) {
                break;
            }

            // All positions with lcp[index][j] > 0
            // must have the same character.
            for (int j = index; j < n; j++) {

                if (lcp[index][j] > 0) {
                    s[j] = c;
                }
            }
        }

        // More than 26 different characters are needed
        for (int i = 0; i < n; i++) {
            if (s[i] == '\0') {
                return "";
            }
        }

        // Validate the constructed string
        for (int i = n - 1; i >= 0; i--) {

            for (int j = n - 1; j >= 0; j--) {

                if (s[i] == s[j]) {

                    // If either suffix ends here,
                    // LCP must be exactly 1.
                    if (i == n - 1 || j == n - 1) {

                        if (lcp[i][j] != 1) {
                            return "";
                        }

                    } else {

                        // If characters are equal:
                        // lcp[i][j] = 1 + lcp[i+1][j+1]
                        if (lcp[i][j] != lcp[i + 1][j + 1] + 1) {
                            return "";
                        }
                    }

                } else {

                    // Different first characters means LCP = 0
                    if (lcp[i][j] != 0) {
                        return "";
                    }
                }
            }
        }

        return new String(s);
    }
}