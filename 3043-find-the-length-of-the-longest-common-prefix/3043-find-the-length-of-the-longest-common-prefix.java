import java.util.*;

class Solution {
    public int longestCommonPrefix(int[] arr1, int[] arr2) {

        HashSet<Integer> set = new HashSet<>();

        // Store all prefixes of arr1
        for (int x : arr1) {
            while (x > 0) {
                set.add(x);
                x /= 10;
            }
        }

        int maxPrefix = 0;

        // Check prefixes of arr2
        for (int x : arr2) {

            while (x > 0) {

                if (set.contains(x)) {
                    maxPrefix = Math.max(maxPrefix, x);
                    break;
                }

                x /= 10;
            }
        }

        // Number of digits in the longest prefix
        if (maxPrefix == 0) {
            return 0;
        }

        return String.valueOf(maxPrefix).length();
    }
}