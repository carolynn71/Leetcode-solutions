import java.util.*;

class Solution {

    private long[] nums;
    private int side;
    private int k;
    private int n;

    public int maxDistance(int side, int[][] points, int k) {

        this.side = side;
        this.k = k;
        this.n = points.length;

        nums = new long[n];

        // Map every boundary point to a position
        // on the perimeter.
        for (int i = 0; i < n; i++) {

            int x = points[i][0];
            int y = points[i][1];

            if (x == 0) {
                nums[i] = y;
            }
            else if (y == side) {
                nums[i] = (long) side + x;
            }
            else if (x == side) {
                nums[i] = (long) 3 * side - y;
            }
            else {
                nums[i] = (long) 4 * side - x;
            }
        }

        Arrays.sort(nums);

        int low = 1;
        int high = side;

        while (low < high) {

            int mid = low + (high - low + 1) / 2;

            if (check(mid)) {
                low = mid;
            }
            else {
                high = mid - 1;
            }
        }

        return low;
    }

    private boolean check(int distance) {

        long perimeter = 4L * side;

        for (int start = 0; start < n; start++) {

            long first = nums[start];

            // Last selected point must leave at least
            // 'distance' for the circular return.
            long limit = first + perimeter - distance;

            long current = first;

            boolean possible = true;

            for (int count = 1; count < k; count++) {

                int next = lowerBound(current + distance);

                if (next >= n) {
                    possible = false;
                    break;
                }

                current = nums[next];

                if (current > limit) {
                    possible = false;
                    break;
                }
            }

            if (possible) {
                return true;
            }
        }

        return false;
    }

    // First index whose value >= target
    private int lowerBound(long target) {

        int left = 0;
        int right = n;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (nums[mid] >= target) {
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }

        return left;
    }
}