class Solution {
    public int maxNumberOfBalloons(String text) {

        int[] count = new int[26];

        // Count every character
        for (char c : text.toCharArray()) {
            count[c - 'a']++;
        }

        // "balloon" needs 2 l's and 2 o's
        count['l' - 'a'] /= 2;
        count['o' - 'a'] /= 2;

        // Find the limiting character
        return Math.min(
            Math.min(count['b' - 'a'], count['a' - 'a']),
            Math.min(
                Math.min(count['l' - 'a'], count['o' - 'a']),
                count['n' - 'a']
            )
        );
    }
}