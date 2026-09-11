import java.util.*;

class Solution {
    public boolean hasGroupsSizeX(int[] deck) {

        HashMap<Integer, Integer> map = new HashMap<>();

        // Count frequency of every card
        for (int num : deck) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // Find GCD of all frequencies
        int g = 0;

        for (int freq : map.values()) {
            g = gcd(g, freq);
        }

        return g >= 2;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }
}