class Solution {
    public char[][] rotateTheBox(char[][] box) {

        int m = box.length;
        int n = box[0].length;

        // Step 1: Let stones fall to the right
        // before rotating.
        for (int i = 0; i < m; i++) {

            int empty = n - 1;

            for (int j = n - 1; j >= 0; j--) {

                if (box[i][j] == '*') {
                    empty = j - 1;
                }

                else if (box[i][j] == '#') {
                    box[i][j] = '.';
                    box[i][empty] = '#';
                    empty--;
                }
            }
        }

        // Step 2: Rotate 90 degrees clockwise
        char[][] result = new char[n][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                result[j][m - 1 - i] = box[i][j];
            }
        }

        return result;
    }
}