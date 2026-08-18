class Solution {

    public boolean canBeEqual(String s1, String s2) {

        int[][] count = new int[2][26];

        for (int i = 0; i < 4; i++) {

            // Add characters from s1
            count[i % 2][s1.charAt(i) - 'a']++;

            // Remove characters from s2
            count[i % 2][s2.charAt(i) - 'a']--;
        }

        // Check both even and odd positions
        for (int parity = 0; parity < 2; parity++) {

            for (int c = 0; c < 26; c++) {

                if (count[parity][c] != 0) {
                    return false;
                }
            }
        }

        return true;
    }
}