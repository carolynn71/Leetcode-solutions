class Solution {
    public int rotatedDigits(int n) {

        int count = 0;

        for (int num = 1; num <= n; num++) {

            int x = num;
            boolean valid = true;
            boolean different = false;

            while (x > 0) {

                int digit = x % 10;

                if (digit == 3 || digit == 4 || digit == 7) {
                    valid = false;
                    break;
                }

                if (digit == 2 || digit == 5 ||
                    digit == 6 || digit == 9) {
                    different = true;
                }

                x /= 10;
            }

            if (valid && different) {
                count++;
            }
        }

        return count;
    }
}