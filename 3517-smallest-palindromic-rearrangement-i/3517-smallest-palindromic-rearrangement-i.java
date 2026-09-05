class Solution {
    public String smallestPalindrome(String s) {

        int[] count = new int[26];

        // Count characters
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        StringBuilder left = new StringBuilder();
        char middle = 0;

        // Build the smallest possible left half
        for (char c = 'a'; c <= 'z'; c++) {

            int freq = count[c - 'a'];

            // Half goes to the left
            for (int i = 0; i < freq / 2; i++) {
                left.append(c);
            }

            // Odd frequency character goes in the middle
            if (freq % 2 == 1) {
                middle = c;
            }
        }

        // Create right half
        String right = new StringBuilder(left).reverse().toString();

        return left.toString()
                + (middle == 0 ? "" : String.valueOf(middle))
                + right;
    }
}