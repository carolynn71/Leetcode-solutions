class Solution {
    public int[][] rotateGrid(int[][] grid, int k) {

        int m = grid.length;
        int n = grid[0].length;

        int layers = Math.min(m, n) / 2;

        for (int layer = 0; layer < layers; layer++) {

            int top = layer;
            int bottom = m - 1 - layer;
            int left = layer;
            int right = n - 1 - layer;

            int perimeter =
                    2 * (bottom - top + right - left);

            int rotations = k % perimeter;

            for (int r = 0; r < rotations; r++) {
                rotateOne(grid, top, bottom, left, right);
            }
        }

        return grid;
    }

    private void rotateOne(
            int[][] grid,
            int top,
            int bottom,
            int left,
            int right) {

        int temp = grid[top][left];

        // Top row → left
        for (int i = left; i < right; i++) {
            grid[top][i] = grid[top][i + 1];
        }

        // Right column → top
        for (int i = top; i < bottom; i++) {
            grid[i][right] = grid[i + 1][right];
        }

        // Bottom row → right
        for (int i = right; i > left; i--) {
            grid[bottom][i] = grid[bottom][i - 1];
        }

        // Left column → bottom
        for (int i = bottom; i > top + 1; i--) {
            grid[i][left] = grid[i - 1][left];
        }

        grid[top + 1][left] = temp;
    }
}