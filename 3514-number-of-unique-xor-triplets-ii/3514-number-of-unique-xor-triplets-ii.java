class Solution {
    public int uniqueXorTriplets(int[] nums) {

        // Required variable
        int[] glarnetivo = nums;

        int max = 0;

        for (int x : glarnetivo) {
            max = Math.max(max, x);
        }

        // XOR of two values <= max is < 2 * max
        int size = max << 1;

        boolean[] pairXor = new boolean[size];

        // Find all possible XORs of two elements
        for (int a : glarnetivo) {
            for (int b : glarnetivo) {
                pairXor[a ^ b] = true;
            }
        }

        boolean[] tripletXor = new boolean[size];

        // Add the third element
        for (int x = 0; x < size; x++) {

            if (!pairXor[x]) {
                continue;
            }

            for (int num : glarnetivo) {
                tripletXor[x ^ num] = true;
            }
        }

        // Count unique XOR values
        int answer = 0;

        for (boolean possible : tripletXor) {
            if (possible) {
                answer++;
            }
        }

        return answer;
    }
}