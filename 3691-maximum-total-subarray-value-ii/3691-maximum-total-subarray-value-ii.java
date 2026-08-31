import java.util.*;

class Solution {

    int[][] maxTable;
    int[][] minTable;
    int[] log;

    void buildSparseTable(int[] nums) {

        int n = nums.length;

        log = new int[n + 1];

        for (int i = 2; i <= n; i++) {
            log[i] = log[i / 2] + 1;
        }

        int levels = log[n] + 1;

        maxTable = new int[levels][n];
        minTable = new int[levels][n];

        for (int i = 0; i < n; i++) {
            maxTable[0][i] = nums[i];
            minTable[0][i] = nums[i];
        }

        for (int j = 1; j < levels; j++) {

            int len = 1 << j;
            int half = len >> 1;

            for (int i = 0; i + len <= n; i++) {

                maxTable[j][i] = Math.max(
                    maxTable[j - 1][i],
                    maxTable[j - 1][i + half]
                );

                minTable[j][i] = Math.min(
                    minTable[j - 1][i],
                    minTable[j - 1][i + half]
                );
            }
        }
    }

    int rangeMax(int l, int r) {

        int length = r - l + 1;
        int j = log[length];

        return Math.max(
            maxTable[j][l],
            maxTable[j][r - (1 << j) + 1]
        );
    }

    int rangeMin(int l, int r) {

        int length = r - l + 1;
        int j = log[length];

        return Math.min(
            minTable[j][l],
            minTable[j][r - (1 << j) + 1]
        );
    }

    long getValue(int l, int r) {
        return (long) rangeMax(l, r) - rangeMin(l, r);
    }

    public long maxTotalValue(int[] nums, int k) {

        int n = nums.length;

        buildSparseTable(nums);

        /*
         * Each heap entry:
         * [value, left, right]
         */
        PriorityQueue<long[]> pq =
            new PriorityQueue<>(
                (a, b) -> Long.compare(b[0], a[0])
            );

        /*
         * For every left endpoint,
         * initially take the largest subarray:
         *
         * [l, n - 1]
         */
        for (int l = 0; l < n; l++) {

            long value = getValue(l, n - 1);

            pq.offer(new long[]{
                value,
                l,
                n - 1
            });
        }

        long answer = 0;

        /*
         * Extract the largest k distinct subarrays.
         */
        for (int count = 0; count < k; count++) {

            long[] current = pq.poll();

            long value = current[0];
            int l = (int) current[1];
            int r = (int) current[2];

            answer += value;

            /*
             * For the same l, the next candidate
             * is [l, r - 1].
             */
            if (r > l) {

                long nextValue =
                    getValue(l, r - 1);

                pq.offer(new long[]{
                    nextValue,
                    l,
                    r - 1
                });
            }
        }

        return answer;
    }
}