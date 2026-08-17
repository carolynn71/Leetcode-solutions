class Solution {

    public int[][] constructProductMatrix(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int[][] ans = new int[m][n];

        long product = 1;

        // Prefix product
        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                ans[i][j] = (int) product;

                product = (product * grid[i][j]) % 12345;
            }
        }

        // Suffix product
        product = 1;

        for (int i = m - 1; i >= 0; i--) {

            for (int j = n - 1; j >= 0; j--) {

                ans[i][j] =
                    (int)((long) ans[i][j] * product % 12345);

                product =
                    (product * grid[i][j]) % 12345;
            }
        }

        return ans;
    }
}