class Solution {
    public String shortestBeautifulSubstring(String s, int k) {

        int left = 0;
        int ones = 0;

        String ans = "";
        int minLength = Integer.MAX_VALUE;

        for (int right = 0; right < s.length(); right++) {

            if (s.charAt(right) == '1') {
                ones++;
            }

            // Too many 1s
            while (ones > k) {
                if (s.charAt(left) == '1') {
                    ones--;
                }
                left++;
            }

            // Remove unnecessary leading zeros
            while (ones == k &&
                   left < right &&
                   s.charAt(left) == '0') {
                left++;
            }

            // Exactly k ones
            if (ones == k) {

                String current = s.substring(left, right + 1);

                if (current.length() < minLength ||
                    (current.length() == minLength &&
                     current.compareTo(ans) < 0)) {

                    ans = current;
                    minLength = current.length();
                }
            }
        }

        return ans;
    }
}