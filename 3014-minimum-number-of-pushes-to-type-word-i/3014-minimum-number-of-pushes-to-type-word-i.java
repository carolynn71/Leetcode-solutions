class Solution {
    public int minimumPushes(String word) {

        int n = word.length();

        int answer = 0;
        int pushes = 1;

        for (int i = 0; i < n; i++) {

            // Every 8 letters, we need one more push
            if (i > 0 && i % 8 == 0) {
                pushes++;
            }

            answer += pushes;
        }

        return answer;
    }
}