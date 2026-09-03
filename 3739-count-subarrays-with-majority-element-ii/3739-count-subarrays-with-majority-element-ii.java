class Solution {

    class FenwickTree {
        int[] tree;

        FenwickTree(int n) {
            tree = new int[n + 1];
        }

        void update(int index, int value) {
            while (index < tree.length) {
                tree[index] += value;
                index += index & -index;
            }
        }

        int query(int index) {
            int sum = 0;

            while (index > 0) {
                sum += tree[index];
                index -= index & -index;
            }

            return sum;
        }
    }

    public long countMajoritySubarrays(int[] nums, int target) {

        int n = nums.length;

        // Prefix sums can range from -n to +n.
        // Shift by n so all indices become positive.
        int OFFSET = n + 1;

        FenwickTree bit = new FenwickTree(2 * n + 3);

        long answer = 0;

        int prefix = 0;

        // Prefix sum 0 exists before the array starts.
        bit.update(OFFSET, 1);

        for (int x : nums) {

            // Convert to +1 / -1
            if (x == target) {
                prefix++;
            } else {
                prefix--;
            }

            /*
             * We need previous prefix sums
             * that are strictly smaller than current prefix.
             */
            int index = prefix + OFFSET;

            answer += bit.query(index - 1);

            // Store current prefix sum
            bit.update(index, 1);
        }

        return answer;
    }
}