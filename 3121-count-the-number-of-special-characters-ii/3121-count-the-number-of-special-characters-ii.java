class Solution {
    public int numberOfSpecialChars(String word) {

        int[] firstUpper = new int[26];
        int[] lastLower = new int[26];

        // Initialize
        for (int i = 0; i < 26; i++) {
            firstUpper[i] = Integer.MAX_VALUE;
            lastLower[i] = -1;
        }

        // Record positions
        for (int i = 0; i < word.length(); i++) {

            char c = word.charAt(i);

            if (c >= 'a' && c <= 'z') {
                lastLower[c - 'a'] = i;
            } else {
                int index = c - 'A';

                firstUpper[index] =
                    Math.min(firstUpper[index], i);
            }
        }

        int count = 0;

        for (int i = 0; i < 26; i++) {

            // Both cases exist AND
            // every lowercase occurrence is before
            // every uppercase occurrence.
            if (lastLower[i] != -1 &&
                firstUpper[i] != Integer.MAX_VALUE &&
                lastLower[i] < firstUpper[i]) {

                count++;
            }
        }

        return count;
    }
}