class Solution {
    public long sumAndMultiply(int n) {
        long x = 0;
        long sum = 0;

        for (char c : String.valueOf(n).toCharArray()) {
            int digit = c - '0';

            if (digit == 0) {
                continue;
            }

            // Append digit to x
            x = x * 10 + digit;

            // Add digit to sum
            sum += digit;
        }

        return x * sum;
    }
}