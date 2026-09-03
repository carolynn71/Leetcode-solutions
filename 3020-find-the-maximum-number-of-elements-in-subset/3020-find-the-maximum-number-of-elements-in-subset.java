import java.util.*;

class Solution {
    public int maximumLength(int[] nums) {

        Map<Long, Integer> count = new HashMap<>();

        // Count frequency of every number
        for (int num : nums) {
            count.put((long) num,
                      count.getOrDefault((long) num, 0) + 1);
        }

        // Handle 1 separately
        int ones = count.getOrDefault(1L, 0);

        int answer = 0;

        if (ones > 0) {
            answer = (ones % 2 == 1) ? ones : ones - 1;
        }

        // Try every number as the starting value
        for (long x : count.keySet()) {

            if (x == 1) {
                continue;
            }

            int length = 0;

            while (count.getOrDefault(x, 0) >= 2) {

                // We use two copies:
                // one on the left and one on the right
                length += 2;

                // Move to x²
                x = x * x;
            }

            // x can become the center if it exists
            if (count.containsKey(x)) {
                length++;
            } else {
                // No center available,
                // remove one pair from the end
                length--;
            }

            answer = Math.max(answer, length);
        }

        return answer;
    }
}