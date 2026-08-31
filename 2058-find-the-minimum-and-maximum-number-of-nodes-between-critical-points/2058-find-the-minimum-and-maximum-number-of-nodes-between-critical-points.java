class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {

        int first = -1;
        int last = -1;

        int minDistance = Integer.MAX_VALUE;

        int index = 1;

        ListNode prev = head;
        ListNode curr = head.next;

        while (curr != null && curr.next != null) {

            int prevValue = prev.val;
            int currValue = curr.val;
            int nextValue = curr.next.val;

            // Check if current node is a critical point
            if ((currValue > prevValue && currValue > nextValue) ||
                (currValue < prevValue && currValue < nextValue)) {

                // First critical point
                if (first == -1) {
                    first = index;
                }

                // Distance from previous critical point
                if (last != -1) {
                    minDistance =
                        Math.min(minDistance, index - last);
                }

                last = index;
            }

            prev = curr;
            curr = curr.next;
            index++;
        }

        // Fewer than 2 critical points
        if (first == -1 || first == last) {
            return new int[]{-1, -1};
        }

        int maxDistance = last - first;

        return new int[]{minDistance, maxDistance};
    }
}