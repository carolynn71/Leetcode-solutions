class NumArray {

    private int[] tree;
    private int[] nums;

    public NumArray(int[] nums) {
        this.nums = nums.clone();

        int n = nums.length;
        tree = new int[n + 1];

        // Build Fenwick Tree
        for (int i = 0; i < n; i++) {
            add(i + 1, nums[i]);
        }
    }

    // Add delta to Fenwick Tree
    private void add(int index, int delta) {
        while (index < tree.length) {
            tree[index] += delta;
            index += index & -index;
        }
    }

    // Prefix sum from 1 to index
    private int prefixSum(int index) {
        int sum = 0;

        while (index > 0) {
            sum += tree[index];
            index -= index & -index;
        }

        return sum;
    }

    public void update(int index, int val) {

        // Difference between new and old value
        int delta = val - nums[index];

        // Update original array
        nums[index] = val;

        // Fenwick Tree uses 1-based indexing
        add(index + 1, delta);
    }

    public int sumRange(int left, int right) {

        // prefix(right) - prefix(left - 1)
        return prefixSum(right + 1) - prefixSum(left);
    }
}