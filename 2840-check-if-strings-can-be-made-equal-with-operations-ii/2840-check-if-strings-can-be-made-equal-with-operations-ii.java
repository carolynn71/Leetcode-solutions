class Solution {

    public boolean checkStrings(String s1, String s2) {

        int[][] count = new int[2][26];

        for (int i = 0; i < s1.length(); i++) {

            int parity = i % 2;

            // Characters in s1
            count[parity][s1.charAt(i) - 'a']++;

            // Characters in s2
            count[parity][s2.charAt(i) - 'a']--;
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