import java.util.*;

class Solution {

    public String generateString(String str1, String str2) {

        int n = str1.length();
        int m = str2.length();

        int len = n + m - 1;

        char[] ans = new char[len];
        boolean[] fixed = new boolean[len];

        // Initially use 'a' everywhere
        Arrays.fill(ans, 'a');

        // Step 1: Handle all T positions
        for (int i = 0; i < n; i++) {

            if (str1.charAt(i) != 'T')
                continue;

            for (int j = 0; j < m; j++) {

                int pos = i + j;

                // Conflict with another T
                if (fixed[pos] &&
                    ans[pos] != str2.charAt(j)) {

                    return "";
                }

                ans[pos] = str2.charAt(j);
                fixed[pos] = true;
            }
        }

        // Step 2: Handle F positions
        for (int i = 0; i < n; i++) {

            if (str1.charAt(i) != 'F')
                continue;

            // Check whether current substring == str2
            boolean same = true;

            for (int j = 0; j < m; j++) {

                if (ans[i + j] != str2.charAt(j)) {
                    same = false;
                    break;
                }
            }

            // Already different → F condition satisfied
            if (!same)
                continue;

            // We need to change one unfixed character.
            // Choose the RIGHTMOST one to keep
            // the result lexicographically smallest.
            int change = -1;

            for (int j = i + m - 1; j >= i; j--) {

                if (!fixed[j]) {
                    change = j;
                    break;
                }
            }

            // Every character is fixed by T constraints
            if (change == -1)
                return "";

            // Smallest character greater than 'a'
            ans[change] = 'b';
            fixed[change] = true;
        }

        return new String(ans);
    }
}