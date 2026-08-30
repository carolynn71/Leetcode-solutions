class Solution {
    public int totalWaviness(int num1, int num2) {

        int total = 0;

        for (int num = num1; num <= num2; num++) {

            String s = String.valueOf(num);

            for (int i = 1; i < s.length() - 1; i++) {

                int left = s.charAt(i - 1) - '0';
                int current = s.charAt(i) - '0';
                int right = s.charAt(i + 1) - '0';

                // Peak
                if (current > left && current > right) {
                    total++;
                }

                // Valley
                else if (current < left && current < right) {
                    total++;
                }
            }
        }

        return total;
    }
}