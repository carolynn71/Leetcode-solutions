class Solution {

    static final long MOD = 1_000_000_007L;

    // Matrix multiplication
    private long[][] multiply(long[][] A, long[][] B) {

        int n = A.length;
        long[][] C = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {

                if (A[i][k] == 0)
                    continue;

                for (int j = 0; j < n; j++) {

                    if (B[k][j] == 0)
                        continue;

                    C[i][j] =
                        (C[i][j] + A[i][k] * B[k][j]) % MOD;
                }
            }
        }

        return C;
    }

    // Matrix exponentiation
    private long[][] power(long[][] A, long exp) {

        int n = A.length;

        long[][] result = new long[n][n];

        // Identity matrix
        for (int i = 0; i < n; i++) {
            result[i][i] = 1;
        }

        while (exp > 0) {

            if ((exp & 1) == 1) {
                result = multiply(result, A);
            }

            A = multiply(A, A);
            exp >>= 1;
        }

        return result;
    }

    public int zigZagArrays(int n, int l, int r) {

        int m = r - l + 1;

        /*
         * Matrix:
         *
         * M[i][j] = 1 if i + j < m - 1
         *
         * This represents two alternating
         * UP/DOWN transitions.
         */
        long[][] M = new long[m][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {

                if (i + j < m - 1) {
                    M[i][j] = 1;
                }
            }
        }

        // We already account for the first element.
        long[][] result = power(M, n - 1);

        long sum = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                sum = (sum + result[i][j]) % MOD;
            }
        }

        // Symmetry: increasing-first and decreasing-first
        sum = (sum * 2) % MOD;

        return (int) sum;
    }
}