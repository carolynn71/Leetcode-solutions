class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;

        int[] ans = new int[n];
        int[] count = new int[n + 1];

        int common = 0;

        for (int i = 0; i < n; i++) {

            // Add A[i]
            count[A[i]]++;
            if (count[A[i]] == 2) {
                common++;
            }

            // Add B[i]
            count[B[i]]++;
            if (count[B[i]] == 2) {
                common++;
            }

            ans[i] = common;
        }

        return ans;
    }
}