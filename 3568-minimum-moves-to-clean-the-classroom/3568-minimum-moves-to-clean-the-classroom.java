import java.util.*;

class Solution {

    public int minMoves(String[] classroom, int energy) {

        int m = classroom.length;
        int n = classroom[0].length();

        int startR = -1;
        int startC = -1;

        List<int[]> litter = new ArrayList<>();

        // Find start and all litter positions
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {

                char ch = classroom[r].charAt(c);

                if (ch == 'S') {
                    startR = r;
                    startC = c;
                } 
                else if (ch == 'L') {
                    litter.add(new int[]{r, c});
                }
            }
        }

        int totalLitter = litter.size();

        // No litter
        if (totalLitter == 0) {
            return 0;
        }

        /*
         * Map each litter cell to a bit.
         */
        int[][] litterId = new int[m][n];

        for (int[] row : litterId) {
            Arrays.fill(row, -1);
        }

        for (int i = 0; i < totalLitter; i++) {
            litterId[litter.get(i)[0]][litter.get(i)[1]] = i;
        }

        int allCollected = (1 << totalLitter) - 1;

        /*
         * State:
         * [row, col, energy, mask]
         */
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{
            startR,
            startC,
            energy,
            0
        });

        /*
         * visited[row][col][mask]
         * stores the maximum energy with which
         * we have reached this state.
         */
        int[][][] best =
            new int[m][n][1 << totalLitter];

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                Arrays.fill(best[r][c], -1);
            }
        }

        best[startR][startC][0] = energy;

        int[][] dirs = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
        };

        int moves = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();

            while (size-- > 0) {

                int[] state = queue.poll();

                int r = state[0];
                int c = state[1];
                int e = state[2];
                int mask = state[3];

                if (mask == allCollected) {
                    return moves;
                }

                for (int[] dir : dirs) {

                    int nr = r + dir[0];
                    int nc = c + dir[1];

                    // Outside grid
                    if (nr < 0 || nr >= m ||
                        nc < 0 || nc >= n) {
                        continue;
                    }

                    // Wall
                    if (classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }

                    // Need energy to move
                    if (e == 0) {
                        continue;
                    }

                    int newEnergy = e - 1;

                    int newMask = mask;

                    char cell = classroom[nr].charAt(nc);

                    // Collect litter
                    if (cell == 'L') {

                        int id = litterId[nr][nc];

                        newMask |= (1 << id);
                    }

                    // Recharge at 'R'
                    if (cell == 'R') {
                        newEnergy = energy;
                    }

                    /*
                     * If we have already reached this state
                     * with >= energy, this state is useless.
                     */
                    if (best[nr][nc][newMask] >= newEnergy) {
                        continue;
                    }

                    best[nr][nc][newMask] = newEnergy;

                    queue.offer(new int[]{
                        nr,
                        nc,
                        newEnergy,
                        newMask
                    });
                }
            }

            moves++;
        }

        return -1;
    }
}