import java.util.*;

class MKAverage {

    private int m;
    private int k;

    private Queue<Integer> queue = new ArrayDeque<>();

    private TreeMap<Integer, Integer> low = new TreeMap<>();
    private TreeMap<Integer, Integer> mid = new TreeMap<>();
    private TreeMap<Integer, Integer> high = new TreeMap<>();

    // Number of elements in each group
    private int lowSize = 0;
    private int midSize = 0;
    private int highSize = 0;

    // Sum of elements in MID
    private long midSum = 0;

    public MKAverage(int m, int k) {
        this.m = m;
        this.k = k;
    }

    public void addElement(int num) {

        // --------------------------------
        // 1. Remove oldest element FIRST
        // --------------------------------
        if (queue.size() == m) {

            int old = queue.poll();

            if (low.containsKey(old)) {
                remove(low, old);
                lowSize--;
            } 
            else if (mid.containsKey(old)) {
                remove(mid, old);
                midSize--;
                midSum -= old;
            } 
            else {
                remove(high, old);
                highSize--;
            }

            // Refill LOW if necessary
            while (lowSize < k && midSize > 0) {
                int x = mid.firstKey();

                remove(mid, x);
                midSize--;
                midSum -= x;

                add(low, x);
                lowSize++;
            }

            // Refill MID from HIGH if necessary
            while (midSize < m - 2 * k && highSize > 0) {
                int x = high.firstKey();

                remove(high, x);
                highSize--;

                add(mid, x);
                midSize++;
                midSum += x;
            }
        }

        // --------------------------------
        // 2. Add new element to queue
        // --------------------------------
        queue.offer(num);

        // --------------------------------
        // 3. Put new element into a group
        // --------------------------------

        if (lowSize < k ||
            num <= low.lastKey()) {

            add(low, num);
            lowSize++;

        } 
        else if (midSize < m - 2 * k ||
                 num <= mid.lastKey()) {

            add(mid, num);
            midSize++;
            midSum += num;

        } 
        else {

            add(high, num);
            highSize++;
        }

        // --------------------------------
        // 4. Fix LOW if too large
        // --------------------------------
        while (lowSize > k) {

            int x = low.lastKey();

            remove(low, x);
            lowSize--;

            add(mid, x);
            midSize++;
            midSum += x;
        }

        // --------------------------------
        // 5. Fix MID if too large
        // --------------------------------
        while (midSize > m - 2 * k) {

            int x = mid.lastKey();

            remove(mid, x);
            midSize--;
            midSum -= x;

            add(high, x);
            highSize++;
        }
    }

    public int calculateMKAverage() {

        if (queue.size() < m) {
            return -1;
        }

        return (int) (midSum / (m - 2L * k));
    }

    // Add one occurrence
    private void add(TreeMap<Integer, Integer> map, int x) {
        map.put(x, map.getOrDefault(x, 0) + 1);
    }

    // Remove one occurrence
    private void remove(TreeMap<Integer, Integer> map, int x) {

        int count = map.get(x);

        if (count == 1) {
            map.remove(x);
        } else {
            map.put(x, count - 1);
        }
    }
}