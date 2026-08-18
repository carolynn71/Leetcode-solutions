import java.util.*;

class Solution {

    public List<Integer> survivedRobotsHealths(
            int[] positions,
            int[] healths,
            String directions) {

        int n = positions.length;

        // Store robot indices
        Integer[] robots = new Integer[n];

        for (int i = 0; i < n; i++) {
            robots[i] = i;
        }

        // Sort robots by position
        Arrays.sort(robots, (a, b) ->
            Integer.compare(positions[a], positions[b])
        );

        // Stack contains robots moving RIGHT
        Stack<Integer> stack = new Stack<>();

        for (int idx : robots) {

            if (directions.charAt(idx) == 'R') {
                stack.push(idx);
                continue;
            }

            // Current robot is moving LEFT
            while (!stack.isEmpty()
                    && healths[idx] > 0) {

                int rightRobot = stack.peek();

                // R and L collide
                if (healths[rightRobot] < healths[idx]) {

                    // Right-moving robot dies
                    healths[idx]--;
                    healths[rightRobot] = 0;
                    stack.pop();

                } else if (healths[rightRobot] > healths[idx]) {

                    // Left-moving robot dies
                    healths[rightRobot]--;
                    healths[idx] = 0;

                } else {

                    // Same health → both die
                    healths[rightRobot] = 0;
                    healths[idx] = 0;
                    stack.pop();
                }
            }
        }

        // Return surviving robots in original order
        List<Integer> answer = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            if (healths[i] > 0) {
                answer.add(healths[i]);
            }
        }

        return answer;
    }
}