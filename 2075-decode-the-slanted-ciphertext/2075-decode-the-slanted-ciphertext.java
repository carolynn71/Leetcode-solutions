class Solution {

    public String decodeCiphertext(String encodedText, int rows) {

        int len = encodedText.length();
        int cols = len / rows;

        StringBuilder ans = new StringBuilder();

        // Start from every column in the first row
        for (int start = 0; start < cols; start++) {

            int r = 0;
            int c = start;

            // Move diagonally down-right
            while (r < rows && c < cols) {

                ans.append(encodedText.charAt(r * cols + c));

                r++;
                c++;
            }
        }

        // Remove trailing spaces
        while (ans.length() > 0
                && ans.charAt(ans.length() - 1) == ' ') {

            ans.deleteCharAt(ans.length() - 1);
        }

        return ans.toString();
    }
}