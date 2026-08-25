import java.util.*;

class Solution {
    public int minOperations(int[][] grid, int x) {

        int m = grid.length;
        int n = grid[0].length;

        int[] nums = new int[m * n];

        int index = 0;

        // Flatten grid
        for (int[] row : grid) {
            for (int val : row) {
                nums[index++] = val;
            }
        }

        // Check whether transformation is possible
        int remainder = nums[0] % x;

        for (int num : nums) {
            if (num % x != remainder) {
                return -1;
            }
        }

        // Sort to find median
        Arrays.sort(nums);

        int median = nums[nums.length / 2];

        int operations = 0;

        for (int num : nums) {
            operations += Math.abs(num - median) / x;
        }

        return operations;
    }
}