import java.util.*;

class Solution {

    class MedianFinder {

        // Smaller half
        // MAX HEAP
        PriorityQueue<Integer> small =
                new PriorityQueue<>(Collections.reverseOrder());

        // Larger half
        // MIN HEAP
        PriorityQueue<Integer> large =
                new PriorityQueue<>();

        // Numbers waiting to be removed
        Map<Integer, Integer> delayed =
                new HashMap<>();

        // Actual sizes, ignoring delayed elements
        int smallSize = 0;
        int largeSize = 0;

        // Add a number
        void addNum(int num) {

            if (small.isEmpty() || num <= small.peek()) {
                small.offer(num);
                smallSize++;
            } else {
                large.offer(num);
                largeSize++;
            }

            rebalance();
        }

        // Remove a number lazily
        void removeNum(int num) {

            delayed.put(
                num,
                delayed.getOrDefault(num, 0) + 1
            );

            if (num <= small.peek()) {
                smallSize--;
            } else {
                largeSize--;
            }

            prune(small);
            prune(large);

            rebalance();
        }

        // Remove elements from heap top
        // if they are waiting for deletion
        void prune(PriorityQueue<Integer> heap) {

            while (!heap.isEmpty()) {

                int num = heap.peek();

                if (!delayed.containsKey(num)) {
                    break;
                }

                int count = delayed.get(num);

                if (count == 1) {
                    delayed.remove(num);
                } else {
                    delayed.put(num, count - 1);
                }

                heap.poll();
            }
        }

        // Keep:
        // smallSize == largeSize
        // OR
        // smallSize == largeSize + 1
        void rebalance() {

            if (smallSize > largeSize + 1) {

                int num = small.poll();

                smallSize--;
                largeSize++;

                large.offer(num);

                prune(small);

            } else if (smallSize < largeSize) {

                int num = large.poll();

                largeSize--;
                smallSize++;

                small.offer(num);

                prune(large);
            }
        }

        double getMedian() {

            if ((smallSize + largeSize) % 2 == 1) {
                return small.peek();
            }

            return ((double) small.peek()
                    + (double) large.peek()) / 2.0;
        }
    }

    public double[] medianSlidingWindow(int[] nums, int k) {

        int n = nums.length;

        double[] answer = new double[n - k + 1];

        MedianFinder finder = new MedianFinder();

        // First window
        for (int i = 0; i < k; i++) {
            finder.addNum(nums[i]);
        }

        answer[0] = finder.getMedian();

        // Slide window
        for (int i = k; i < n; i++) {

            // Add new element
            finder.addNum(nums[i]);

            // Remove old element
            finder.removeNum(nums[i - k]);

            answer[i - k + 1] =
                    finder.getMedian();
        }

        return answer;
    }
}